package dev.thatcher.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dev.thatcher.controllers.EmployeeController;
import dev.thatcher.controllers.EmployeeControllerImpl;
import dev.thatcher.controllers.EventController;
import dev.thatcher.controllers.EventControllerImpl;
import dev.thatcher.controllers.EventTypeController;
import dev.thatcher.controllers.EventTypeControllerImpl;
import dev.thatcher.controllers.GradingFormatController;
import dev.thatcher.controllers.GradingFormatControllerImpl;
import dev.thatcher.controllers.NotificationController;
import dev.thatcher.controllers.NotificationControllerImpl;
import dev.thatcher.controllers.RequestController;
import dev.thatcher.controllers.RequestControllerImpl;
import dev.thatcher.controllers.RequestNoteController;
import dev.thatcher.controllers.RequestNoteControllerImpl;
import dev.thatcher.controllers.RequestTrackerController;
import dev.thatcher.controllers.RequestTrackerControllerImpl;

public class RequestHelper {
	RequestHelper() {
		super();
	}

	/**
	 * This method will delegate other methods on the controller layer of our
	 * application to process the request
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static Gson gson = new Gson();
	public static EmployeeController ec = new EmployeeControllerImpl();
	public static EventTypeController etc = new EventTypeControllerImpl();
	public static RequestController rc = new RequestControllerImpl();
	public static EventController eventC = new EventControllerImpl();
	public static NotificationController noteC = new NotificationControllerImpl();
	public static RequestTrackerController rtc = new RequestTrackerControllerImpl();
	public static RequestNoteController rnc = new RequestNoteControllerImpl();
	public static GradingFormatController gfc = new GradingFormatControllerImpl();
	public static HttpSession sess = null;
	public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uri = request.getRequestURI();
		switch (uri) {
		case "/Project1/test.do":
			response.getWriter().append("TestWorked!");
			break;
		case "/Project1/getEmployee.do":
			ec.getEmployee(request, response);
			break;
		case "/Project1/create.do":
			ec.create(request, response);
			break;
		case "/Project1/login.do":
			ec.login(request, response);
			break;
		case "/Project1/getLoggedInEmployee.do":
			ec.getLoggedInEmployee(request, response);
			break;
		case "/Project1/logOut.do":
			ec.logOut(request, response);
			break;
		case "/Project1/getEventType.do":
			etc.getEventTypes(request, response);
			break;
		case "/Project1/submitRequest.do":
			rc.submitRequest(request, response);
			break;
		case "/Project1/createEvent.do":
			eventC.createEvent(request, response);
			break;
		case "/Project1/getNotifications.do":
			noteC.getNotification(request, response);
			break;
		case "/Project1/viewRequests.do":
			rtc.viewRequests(request, response);
			break;
		case "/Project1/viewMyRequests.do":
			rtc.viewMyRequests(request, response);
			break;
		case "/Project1/deny.do":
			rtc.deny(request, response);
			break;
		case "/Project1/approve.do":
			rtc.approve(request, response);
			break;
		case "/Project1/cancel.do":
			rtc.cancel(request, response);
			break;
		case "/Project1/addNote.do":
			rnc.addNote(request, response);
			break;
		case "/Project1/goToGrade.do":
			ec.goToGrade(request, response);
			break;
		case "/Project1/getGradingFormats.do":
			gfc.getGradingFormats(request, response);
			break;
		case "/Project1/submitGrade.do":
			rtc.submitGrade(request, response);
			break;
		default:
			response.sendError(418, "Default case hit. Cannot brew coffee, is teapot");
			break;
		}
	}
}
