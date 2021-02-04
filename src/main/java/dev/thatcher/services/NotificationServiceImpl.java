package dev.thatcher.services;

import java.util.List;

import dev.thatcher.models.Notifications;
import dev.thatcher.repositories.NotificationRepository;
import dev.thatcher.repositories.NotificationRepositoryImpl;

public class NotificationServiceImpl implements NotificationService{
	NotificationRepository nr = new NotificationRepositoryImpl();

	@Override
	public Notifications createNotification(Notifications n) {
		return nr.createNotification(n);
	}

	@Override
	public Notifications getNotificationById(int id) {
		return nr.getNotificationById(id);
	}

	@Override
	public List<Notifications> getAllNotifications() {
		return nr.getAllNotifications();
	}

	@Override
	public boolean updateNotification(Notifications n) {
		return nr.updateNotification(n);
	}

	@Override
	public boolean deleteNotification(Notifications n) {
		return nr.deleteNotification(n);
	}
}
