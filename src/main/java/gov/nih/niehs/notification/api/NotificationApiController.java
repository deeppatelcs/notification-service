package gov.nih.niehs.notification.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import gov.nih.niehs.notification.model.AddNotification;
import gov.nih.niehs.notification.model.BulkNotificationOptStatus;
import gov.nih.niehs.notification.model.BulkNotificationOptStatusInner;
import gov.nih.niehs.notification.model.NotificationArray;
import gov.nih.niehs.notification.model.UuidList;
import gov.nih.niehs.notification.security.UserDetails;
import gov.nih.niehs.notification.service.NotificationServiceImpl;
import io.swagger.annotations.ApiParam;

@Controller
public class NotificationApiController implements NotificationApi {

	private static final Logger logger = LoggerFactory.getLogger(NotificationApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	public NotificationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Autowired
	private NotificationServiceImpl notificationService;

	@Autowired
	private UserDetails userDetails;

	public ResponseEntity<Void> addNotification(
			@ApiParam(value = "Schema to add new notification") @Valid @RequestBody AddNotification body) {
		logger.info("controller addNotification()");
		String sessionUser = userDetails.getSessionUserId();
		logger.info("sessionUser: {}", sessionUser);
		logger.info("add notification: {}", body);
		Integer status = notificationService.addNotification(userDetails.getSessionUserId(), body.getRecipientId(),
				body.getSubject(), body.getMessage(), body.getSeverityLevel(), body.getNotificationType(),
				body.getDataLocationUrl());
		if (status > 0) {
			logger.info("Notification added successfully");
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<NotificationArray> getNotifications() {
		logger.info("controller getNotifications()");
		String sessionUser = userDetails.getSessionUserId();
		logger.info("Retrieve notifications for session user: {}", sessionUser);
		NotificationArray notifications = notificationService.getAllNotifications(sessionUser);
		return new ResponseEntity<NotificationArray>(notifications, HttpStatus.OK);
	}

	public ResponseEntity<BulkNotificationOptStatus> markSeen(
			@ApiParam(value = "List of uuids one for each notification") @Valid @RequestBody UuidList body) {
		logger.info("contoller markseen()");
		String sessionUser = userDetails.getSessionUserId();
		logger.info("User to validate ownership. userId: {}", sessionUser);
		logger.info("markSeen notification uuidList: {}", body);
		BulkNotificationOptStatus status = notificationService.markSeen(sessionUser, body);
		logger.info("request status: {}", status);

		// validating update status
		Integer updateCount = 0;
		for (BulkNotificationOptStatusInner entry : status) {
			if (entry.isOperationStatus()) {
				updateCount++;
			}
		}
		if (updateCount == body.size()) {
			return new ResponseEntity<BulkNotificationOptStatus>(status, HttpStatus.OK);
		} else {
			return new ResponseEntity<BulkNotificationOptStatus>(status, HttpStatus.CONFLICT);
		}

	}

	public ResponseEntity<NotificationArray> getUnseenNotifications() {
		logger.info("controller getUnseenNotifications()");
		String sessionUser = userDetails.getSessionUserId();
		logger.info("Reterieve unseen notifications for session user: {}", sessionUser);
		NotificationArray notifications = notificationService.getUnseenNotifications(sessionUser);
		return new ResponseEntity<NotificationArray>(notifications, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getUnseenNotificationsCount() {
		logger.info("controller getUnseenNotificationsCount()");
		String sessionUser = userDetails.getSessionUserId();
		logger.info("Reterieve unseen notifications count for session user: {}", sessionUser);
		Integer count = notificationService.getUnseenNotificationsCount(sessionUser);
		return new ResponseEntity<Integer>(count, HttpStatus.OK);
	}

	public ResponseEntity<BulkNotificationOptStatus> deleteNotifications(
			@ApiParam(value = "List of uuids one for each notification") @Valid @RequestBody UuidList body) {
		logger.info("controller deleteNotifications()");
		String sessionUser = userDetails.getSessionUserId();
		logger.info("User to validate ownership. userId: {}", sessionUser);
		logger.info("markSeen notification uuidList: {}", body);
		BulkNotificationOptStatus status = notificationService.deleteNotifications(sessionUser, body);
		logger.info("request status: {}", status);

		// validating update status
		Integer updateCount = 0;
		for (BulkNotificationOptStatusInner entry : status) {
			if (entry.isOperationStatus()) {
				updateCount++;
			}
		}
		if (updateCount == body.size()) {
			return new ResponseEntity<BulkNotificationOptStatus>(status, HttpStatus.OK);
		} else {
			return new ResponseEntity<BulkNotificationOptStatus>(status, HttpStatus.CONFLICT);
		}
	}
}
