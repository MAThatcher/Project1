package dev.thatcher.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.thatcher.models.Employee;
import dev.thatcher.models.Notifications;
import dev.thatcher.models.Request;
import dev.thatcher.models.RequestNote;
import dev.thatcher.services.NotificationService;
import dev.thatcher.services.NotificationServiceImpl;
import dev.thatcher.services.RequestNoteService;
import dev.thatcher.services.RequestNoteServiceImpl;
import dev.thatcher.services.RequestService;
import dev.thatcher.services.RequestServiceImpl;
import dev.thatcher.servlets.RequestHelper;

public class RequestNoteControllerImpl implements RequestNoteController {
	RequestNoteService rns = new RequestNoteServiceImpl();
	NotificationService ns = new NotificationServiceImpl();
	RequestService rs = new RequestServiceImpl();

	@Override
	public void addNote(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RequestNote rn = RequestHelper.gson.fromJson(request.getReader(), RequestNote.class);
		Employee loggedIn = RequestHelper.gson.fromJson(RequestHelper.sess.getAttribute("loggedInEmployee").toString(),
				Employee.class);
		rns.createRequestNote(rn);
		Request r = rs.getRequestById(rn.getRequestId());
		if (r.getEmpId() == loggedIn.getId()) {
			Notifications n = new Notifications();
			n.setEmpId(r.getEmpId());
			n.setBody(
					"More information is needed on your submitted request form. Click \"View Reimbursements\n to find out");
			n.setSeen(false);
			ns.createNotification(n);
		}

	}

}
