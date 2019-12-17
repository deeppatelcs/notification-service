package gov.nih.niehs.notification.interfaces;

import gov.nih.niehs.notification.model.BulkNotificationOptStatus;
import gov.nih.niehs.notification.model.NotificationArray;
import gov.nih.niehs.notification.model.UuidList;

public interface NotificationService {
	public Integer addNotification(String senderId, String recipientId, String subject, String message,
			Integer severityLevel, String notificationType, String dataLocationUrl);

	public NotificationArray getAllNotifications(String userId);

	public BulkNotificationOptStatus markSeen(String userId, UuidList uuid);
	
	public NotificationArray getUnseenNotifications(String userId);
	
	public Integer getUnseenNotificationsCount(String userId);
	
	public BulkNotificationOptStatus deleteNotifications(String userId, UuidList uuid);
}
