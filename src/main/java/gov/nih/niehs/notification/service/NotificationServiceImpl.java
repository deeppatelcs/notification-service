package gov.nih.niehs.notification.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.nih.niehs.notification.interfaces.NotificationRepository;
import gov.nih.niehs.notification.interfaces.NotificationService;
import gov.nih.niehs.notification.model.BulkNotificationOptStatus;
import gov.nih.niehs.notification.model.BulkNotificationOptStatusInner;
import gov.nih.niehs.notification.model.NotificationArray;
import gov.nih.niehs.notification.model.UuidList;

@Service
public class NotificationServiceImpl implements NotificationService {
	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public Integer addNotification(String senderId, String recipientId, String subject, String message,
			Integer severityLevel, String notificationType, String dataLocationUrl) {
		logger.info("Initiating addNotification()");
		logger.info("Querying database");
		Integer affectedRows = notificationRepository.addNotification(senderId, recipientId, subject, message,
				severityLevel, notificationType, dataLocationUrl);
		logger.info("Number of rows affected: {}", affectedRows);
		return affectedRows;
	}

	@Override
	public NotificationArray getAllNotifications(String userId) {
		logger.info("Initiating getAllNotifications()");
		logger.info("Querying database");
		NotificationArray notifications = notificationRepository.getAllNotification(userId);
		return notifications;
	}

	@Override
	public BulkNotificationOptStatus markSeen(String userId, UuidList uuids) {
		logger.info("Initiating markSeen()");
		BulkNotificationOptStatus updateStatus = new BulkNotificationOptStatus();
		Boolean seenFlag = true;
		logger.info("Querying database");
		for (UUID uuid : uuids) {
			logger.info("Marking notifications: " + uuid + "as seen: " + seenFlag);
			Integer status = notificationRepository.markSeen(userId, uuid, seenFlag);
			if (status == 1) {
				updateStatus.add(new BulkNotificationOptStatusInner(uuid, true));
			} else {
				logger.error("Error updating notification with uuid: {}", uuid);
				updateStatus.add(new BulkNotificationOptStatusInner(uuid, false));
			}
		}
		return updateStatus;
	}

	@Override
	public NotificationArray getUnseenNotifications(String userId) {
		logger.info("Initiating getUnseenNotifications()");
		NotificationArray notifications = notificationRepository.getUnseenNotifications(userId);
		return notifications;
	}

	@Override
	public Integer getUnseenNotificationsCount(String userId) {
		logger.info("Initiating getUnseenNotificationsCount()");
		Integer result = notificationRepository.getUnseenNotificationsCount(userId);
		return result;
	}

	@Override
	public BulkNotificationOptStatus deleteNotifications(String userId, UuidList uuids) {
		logger.info("Initiating markSeen()");
		BulkNotificationOptStatus updateStatus = new BulkNotificationOptStatus();
		Boolean deletedFlag = true;
		logger.info("Querying database");
		for (UUID uuid : uuids) {
			logger.info("Marking notifications: " + uuid + "as deleted: " + deletedFlag);
			Integer status = notificationRepository.deleteNotifications(userId, uuid, deletedFlag);
			if (status == 1) {
				updateStatus.add(new BulkNotificationOptStatusInner(uuid, true));
			} else {
				logger.error("Error updating notification with uuid: {}", uuid);
				updateStatus.add(new BulkNotificationOptStatusInner(uuid, false));
			}
		}
		return updateStatus;
	}
}
