package dev.thatcher.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.thatcher.models.Employee;
import dev.thatcher.models.Event;
import dev.thatcher.services.EventService;
import dev.thatcher.services.EventServiceImpl;
import dev.thatcher.servlets.RequestHelper;

public class EventControllerImpl implements EventController {
	EventService es = new EventServiceImpl();

	@Override
	public void createEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee e = RequestHelper.gson.fromJson(RequestHelper.sess.getAttribute("loggedInEmployee").toString(),
				Employee.class);
		Event event = RequestHelper.gson.fromJson(request.getReader(), Event.class);

		event.setEmpId(e.getId());
		event = es.createEvent(event);
		String out = String.valueOf(event.getId());
		response.getWriter().append(out);

	}

}
