package dev.thatcher.repositories;

import java.util.List;

import dev.thatcher.models.Notifications;


public interface NotificationRepository {
	public Notifications createNotification(Notifications n);

	public Notifications getNotificationById(int id);

	public List<Notifications> getAllNotifications();

	public boolean updateNotification(Notifications n);

	public boolean deleteNotification(Notifications n);

}
