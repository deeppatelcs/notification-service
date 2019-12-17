package gov.nih.niehs.notification.interfaces;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import gov.nih.niehs.notification.model.Notification;
import gov.nih.niehs.notification.model.NotificationArray;

public interface NotificationRepository extends CrudRepository<Notification, String> {

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO public.notification (sender_id, recipient_id, subject, message, severity_level, notification_type, data_location_url) values(:sender_id, :recipient_id, :subject, :message, :severity_level, :notification_type, :data_location_url)", nativeQuery = true)
	public Integer addNotification(@Param("sender_id") String sender_id, @Param("recipient_id") String recipient_id,
			@Param("subject") String subject, @Param("message") String message,
			@Param("severity_level") Integer severity_level, @Param("notification_type") String notification_type,
			@Param("data_location_url") String data_location_url);

	@Query(value = "SELECT n.* FROM public.notification as n WHERE n.recipient_id= :recipient_id", nativeQuery = true)
	public NotificationArray getAllNotification(@Param("recipient_id") String recipient_id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE public.notification SET seen= :seenFlag WHERE notification_id= :notification_id and recipient_id= :recipient_id", nativeQuery = true)
	public Integer markSeen(@Param("recipient_id") String recipient_id, @Param("notification_id") UUID notification_id,
			@Param("seenFlag") Boolean seenFlag);

	@Query(value = "SELECT n.* FROM public.notification as n WHERE n.recipient_id= :recipient_id and n.seen= false", nativeQuery = true)
	public NotificationArray getUnseenNotifications(@Param("recipient_id") String recipient_id);

	@Query(value = "SELECT COUNT(n) FROM public.notification as n WHERE n.recipient_id= :recipient_id and n.seen= false", nativeQuery = true)
	public Integer getUnseenNotificationsCount(@Param("recipient_id") String recipient_id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE public.notification SET deleted= :deletedFlag WHERE notification_id= :notification_id and recipient_id= :recipient_id", nativeQuery = true)
	public Integer deleteNotifications(@Param("recipient_id") String recipient_id,
			@Param("notification_id") UUID notification_id, @Param("deletedFlag") Boolean deletedFlag);
}
