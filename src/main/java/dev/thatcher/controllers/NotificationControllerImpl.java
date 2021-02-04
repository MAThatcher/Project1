package dev.thatcher.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.thatcher.models.Employee;
import dev.thatcher.models.Notifications;
import dev.thatcher.services.NotificationService;
import dev.thatcher.services.NotificationServiceImpl;
import dev.thatcher.servlets.RequestHelper;

public class NotificationControllerImpl implements NotificationController {
	public static NotificationService ns = new NotificationServiceImpl();
	@Override
	public void getNotification(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee e = RequestHelper.gson.fromJson(RequestHelper.sess.getAttribute("loggedInEmployee").toString(),
				Employee.class);
		List<Notifications> myList = new ArrayList<Notifications>();
		List<Notifications> dbList = ns.getAllNotifications();
		if (dbList == null || dbList.isEmpty()) {
			return;
		}
		for (Notifications n: dbList) {
			if (n.getEmpId() == e.getId() && !n.isSeen()) {
				myList.add(n);
				n.setSeen(true);
				ns.updateNotification(n);
			}
		}
		response.getWriter().append((!myList.isEmpty()) ? RequestHelper.gson.toJson(myList) : "{}");
	}

}
