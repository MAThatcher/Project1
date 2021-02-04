package dev.thatcher.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.thatcher.models.EventType;
import dev.thatcher.services.EventTypeService;
import dev.thatcher.services.EventTypeServiceImpl;
import dev.thatcher.servlets.RequestHelper;

public class EventTypeControllerImpl implements EventTypeController {
	public static EventTypeService ets= new EventTypeServiceImpl();
	
	@Override
	public void getEventTypes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<EventType> allEventTypes = ets.getAllEventTypes();		
		response.getWriter().append((!allEventTypes.isEmpty()) ? RequestHelper.gson.toJson(allEventTypes) : "{}");
	}

}
