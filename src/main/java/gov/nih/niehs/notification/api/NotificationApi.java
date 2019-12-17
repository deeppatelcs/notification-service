package gov.nih.niehs.notification.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.nih.niehs.notification.model.AddNotification;
import gov.nih.niehs.notification.model.BulkNotificationOptStatus;
import gov.nih.niehs.notification.model.NotificationArray;
import gov.nih.niehs.notification.model.UuidList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Api(value = "notification", description = "the notification API")
public interface NotificationApi {

	@ApiOperation(value = "Add new notification", nickname = "addNotification", notes = "Query database to insert new notification", authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "Notification", })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "notification added successfully"),
			@ApiResponse(code = 400, message = "bad request") })
	@RequestMapping(value = "/addNotification", consumes = { "application/json" }, method = RequestMethod.POST)
	ResponseEntity<Void> addNotification(
			@ApiParam(value = "Schema to add new notification") @Valid @RequestBody AddNotification body);

	@ApiOperation(value = "Reterieve notifications", nickname = "getNotifications", notes = "Query database to get all the notifications for session user", response = NotificationArray.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "Notification", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of all notifications", response = NotificationArray.class) })
	@RequestMapping(value = "/getNotification", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<NotificationArray> getNotifications();

	@ApiOperation(value = "Update notification as seen", nickname = "markSeen", notes = "Query database to mark of notifications as seen once user is validated of ownership", response = BulkNotificationOptStatus.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "Notification", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "notifications marked seen successfully", response = BulkNotificationOptStatus.class),
			@ApiResponse(code = 409, message = "conflict in update", response = BulkNotificationOptStatus.class) })
	@RequestMapping(value = "/markSeen", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<BulkNotificationOptStatus> markSeen(
			@ApiParam(value = "List of uuids one for each notification") @Valid @RequestBody UuidList body);

	@ApiOperation(value = "Reterieve all unseen notifications", nickname = "getUnseenNotifications", notes = "Query database to get all unseen notifications for session user", response = NotificationArray.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "Notification", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of all notifications", response = NotificationArray.class) })
	@RequestMapping(value = "/getUnseenNotifications", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<NotificationArray> getUnseenNotifications();

	@ApiOperation(value = "Reterieve all unseen notifications", nickname = "getUnseenNotificationsCount", notes = "Query database to get all unseen notifications for session user", response = Integer.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "Notification", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "count reterieved sucessfully", response = Integer.class) })
	@RequestMapping(value = "/getUnseenNotificationsCount", produces = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<Integer> getUnseenNotificationsCount();

	@ApiOperation(value = "Delete notifications", nickname = "deleteNotifications", notes = "Query database to delete notifications once user is validated of ownership", response = BulkNotificationOptStatus.class, authorizations = {
			@Authorization(value = "BearerAuth") }, tags = { "Notification", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "notifications marked seen successfully", response = BulkNotificationOptStatus.class),
			@ApiResponse(code = 409, message = "conflict in update", response = BulkNotificationOptStatus.class) })
	@RequestMapping(value = "/deleteNotifications", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<BulkNotificationOptStatus> deleteNotifications(
			@ApiParam(value = "List of uuids one for each notification") @Valid @RequestBody UuidList body);

}