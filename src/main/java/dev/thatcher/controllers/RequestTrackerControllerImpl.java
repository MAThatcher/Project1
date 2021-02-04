package dev.thatcher.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.thatcher.models.Employee;
import dev.thatcher.models.Event;
import dev.thatcher.models.EventType;
import dev.thatcher.models.GradingFormat;
import dev.thatcher.models.Notifications;
import dev.thatcher.models.Request;
import dev.thatcher.models.RequestNote;
import dev.thatcher.models.RequestTracker;
import dev.thatcher.services.EmployeeServices;
import dev.thatcher.services.EmployeeServicesImpl;
import dev.thatcher.services.EventService;
import dev.thatcher.services.EventServiceImpl;
import dev.thatcher.services.EventTypeService;
import dev.thatcher.services.EventTypeServiceImpl;
import dev.thatcher.services.GradingFormatService;
import dev.thatcher.services.GradingFormatServiceImpl;
import dev.thatcher.services.NotificationService;
import dev.thatcher.services.NotificationServiceImpl;
import dev.thatcher.services.RequestNoteService;
import dev.thatcher.services.RequestNoteServiceImpl;
import dev.thatcher.services.RequestService;
import dev.thatcher.services.RequestServiceImpl;
import dev.thatcher.services.RequestTrackerService;
import dev.thatcher.services.RequestTrackerServiceImpl;
import dev.thatcher.servlets.RequestHelper;

public class RequestTrackerControllerImpl implements RequestTrackerController {
	public static RequestTrackerService rts = new RequestTrackerServiceImpl();
	public static RequestService rs = new RequestServiceImpl();
	public static EventService es = new EventServiceImpl();
	public static EventTypeService ets = new EventTypeServiceImpl();
	public static EmployeeServices empS = new EmployeeServicesImpl();
	public static NotificationService ns = new NotificationServiceImpl();
	public static RequestNoteService rns = new RequestNoteServiceImpl();
	public static GradingFormatService gfs = new GradingFormatServiceImpl();
	@Override

	public void viewRequests(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// our logged in user
		Employee emp = RequestHelper.gson.fromJson(RequestHelper.sess.getAttribute("loggedInEmployee").toString(),
				Employee.class);

		List<RequestTracker> allRT = new ArrayList<RequestTracker>();
		List<Employee> allEmployee = new ArrayList<Employee>();
		List<Event> allEvent = new ArrayList<Event>();
		List<EventType> allEventType = new ArrayList<EventType>();
		List<Request> allRequest = new ArrayList<Request>();
		List<RequestNote> requestNotes = rns.getAllRequestNotes();
		for (RequestTracker rt : rts.getAllRequestTrackers()) {
			if ((emp.getId() == rt.getSupId() && rt.getState() == 1)
					|| (emp.getId() == rt.getDepId() && rt.getState() == 2)
					|| (emp.getId() == rt.getBencoId() && rt.getState() == 3)
							&& (rt.getState() != 4 && rt.getState() != 5 && rt.getState() != 6)) {
				Request newRequest = rs.getRequestById(rt.getRequestId());
				Event getEvent = es.getEventById(newRequest.getEventId());
				Employee getEmp = empS.getEmployeeById(newRequest.getEmpId());
				EventType getEventType = ets.getEventTypeById(getEvent.getEventTypeId());
				allRT.add(rt);
				allEmployee.add(getEmp);
				allEvent.add(getEvent);
				allEventType.add(getEventType);
				allRequest.add(newRequest);
			}
		}
		String output = "[";

		for (int i = 0; i < allRT.size() - 1; i++) {

			List<RequestNote> temp = new ArrayList<RequestNote>();
			StringBuilder notes = new StringBuilder("\"requestNotes\": [");
			for (int j = 0; j < requestNotes.size(); j++) {
				RequestNote rn = requestNotes.get(j);
				if (rn.getRequestId() == allRequest.get(i).getId()) {
					notes.append("\"" + rn.getBody() + "\",");
					temp.add(rn);
				}
			}

			notes.deleteCharAt(notes.length() - 1);
			notes.append("],");
			if (requestNotes == null || temp.size() == 0) {
				notes.delete(0, notes.length());
				notes.append("\"requestNotes\": [],");
			}

			String empName = "{ \"employeeName\":\"" + allEmployee.get(i).getFname() + " "
					+ allEmployee.get(i).getLname() + "\",";
			String idOfRt = "\"idOfRt\":\"" + allRT.get(i).getId() + "\",";
			String dateOfRequest = "\"dateOfRequest\":\"" + allRequest.get(i).getDate() + "\",";
			String dateOfEvent = "\"dateOfEvent\":\"" + allEvent.get(i).getDate() + "\",";
			String reimbursementAmount = "\"reimbursementAmount\": " + allRequest.get(i).getAmount() + ",";
			String location = "\"location\":\"" + allEvent.get(i).getLocation() + "\",";
			String eventType = "\"eventType\":\"" + allEventType.get(i).getName() + "\",";
			String justification = "\"justification\":\"" + allRequest.get(i).getJustfication() + "\",";
			String relatedAttachments = "\"relatedAttachments\":\"" + allEvent.get(i).getRelatedAttachment() + "\",";
			String attachedApproval = "\"attachedApproval\":\"" + allRequest.get(i).getAttachedApproval() + "\",";
			String state = "\"state\":\"" + allRT.get(i).getState() + "\",";
			String workMissed = "\"workMissed\":" + allRequest.get(i).getTimeMissed() + "},";
			output += empName + dateOfRequest + dateOfEvent + reimbursementAmount + location + eventType + justification
					+ relatedAttachments + attachedApproval + state + idOfRt + notes.toString() + workMissed;
		}
		int i = (allRT.size() - 1);

		List<RequestNote> temp = new ArrayList<RequestNote>();
		StringBuilder notes = new StringBuilder("\"requestNotes\": [");
		for (int j = 0; j < requestNotes.size(); j++) {
			RequestNote rn = requestNotes.get(j);
			if (rn.getRequestId() == allRequest.get(i).getId()) {
				notes.append("\"" + rn.getBody() + "\",");
				temp.add(rn);
			}
		}

		notes.deleteCharAt(notes.length() - 1);
		notes.append("],");
		if (requestNotes == null || temp.size() == 0) {
			notes.delete(0, notes.length());
			notes.append("\"requestNotes\": [],");
		}

		String empName = "{ \"employeeName\":\"" + allEmployee.get(i).getFname() + " " + allEmployee.get(i).getLname()
				+ "\",";
		String idOfRt = "\"idOfRt\":\"" + allRT.get(i).getId() + "\",";
		String dateOfRequest = "\"dateOfRequest\":\"" + allRequest.get(i).getDate() + "\",";
		String dateOfEvent = "\"dateOfEvent\":\"" + allEvent.get(i).getDate() + "\",";
		String reimbursementAmount = "\"reimbursementAmount\": " + allRequest.get(i).getAmount() + ",";
		String location = "\"location\":\"" + allEvent.get(i).getLocation() + "\",";
		String eventType = "\"eventType\":\"" + allEventType.get(i).getName() + "\",";
		String justification = "\"justification\":\"" + allRequest.get(i).getJustfication() + "\",";
		String relatedAttachments = "\"relatedAttachments\":\"" + allEvent.get(i).getRelatedAttachment() + "\",";
		String attachedApproval = "\"attachedApproval\":\"" + allRequest.get(i).getAttachedApproval() + "\",";
		String state = "\"state\":\"" + allRT.get(i).getState() + "\",";
		String workMissed = "\"workMissed\":" + allRequest.get(i).getTimeMissed() + "}";
		output += empName + dateOfRequest + dateOfEvent + reimbursementAmount + location + eventType + justification
				+ relatedAttachments + attachedApproval + idOfRt + state + notes.toString() + workMissed + "]";
		response.getWriter().append(output);
	}

	@Override
	public void deny(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RequestTracker requestT = RequestHelper.gson.fromJson(request.getReader(), RequestTracker.class);
		System.out.println(requestT);
		String message = requestT.getDate();
		requestT = rts.getRequestTrackerById(requestT.getId());
		requestT.setState(5);
		rts.updateRequestTracker(requestT);

		// used to get the employee id out of this table
		Request r = rs.getRequestById(requestT.getRequestId());

		// give our employee his funds back
		Employee emp = empS.getEmployeeById(r.getEmpId());
		emp.setFunds(emp.getFunds() + r.getAmount());
		empS.updateEmployee(emp);

		// append a note to our request with the reason for denying the reimbursement
		RequestNote rn = new RequestNote();
		rn.setRequestId(r.getId());
		rn.setBody(message);
		rns.createRequestNote(rn);

		// send out notification that his request has been denied
		Notifications n = new Notifications();
		n.setEmpId(emp.getId());
		n.setBody(
				"A reimbursement request filed by you has been denied. Click \"View Reimbursements\" for more information");
		n.setSeen(false);
		ns.createNotification(n);
	}

	@Override
	public void approve(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Request Tracker id comes from front end, get rest of the information for the
		// request tracker from the back end
		RequestTracker requestT = RequestHelper.gson.fromJson(request.getReader(), RequestTracker.class);
		requestT = rts.getRequestTrackerById(requestT.getId());
		Request req = rs.getRequestById(requestT.getRequestId());

		// preparing a notification to be sent
		Notifications empN = new Notifications();
		empN.setEmpId(req.getEmpId());
		empN.setBody(
				"A reimbursement request filed by you has been approved. Click \"View Reimbursements\" for more information");
		empN.setSeen(false);
		switch (requestT.getState()) {
		case 1:// supervisor just approved the form
			Employee supervisor = empS.getEmployeeById(requestT.getSupId());
			if (supervisor.isDepHead()) { // check to see if the supervisor is also a department head, if he is then we
											// can skip a step
				requestT.setState(3);
				Notifications note = new Notifications();
				note.setEmpId(requestT.getBencoId());
				note.setBody(
						"You have recieved a pending request to approve or dissaprove. Please click on \"View Incoming Reimbursement Requests\" to see the request");
				note.setSeen(false);
				ns.createNotification(note);
			} else {
				requestT.setState(2);
				Notifications note = new Notifications();
				note.setEmpId(requestT.getDepId());
				note.setBody(
						"You have recieved a pending request to approve or dissaprove. Please click on \"View Incoming Reimbursement Requests\" to see the request");
				note.setSeen(false);
				ns.createNotification(note);
			}
			empN.setBody(
					"A reimbursement request filed by you has been approved by your supervisor. Click \"View Reimbursements\" for more information");
			ns.createNotification(empN);
			rts.updateRequestTracker(requestT);
			break;
		case 2:// dephead just approved the form
			requestT.setState(3);
			Notifications note = new Notifications();
			note.setEmpId(requestT.getBencoId());
			note.setBody(
					"You have recieved a pending request to approve or dissaprove. Please click on \"View Incoming Reimbursement Requests\" to see the request");
			note.setSeen(false);
			ns.createNotification(note);
			empN.setBody(
					"A reimbursement request filed by you has been approved by your department head. Click \"View Reimbursements\" for more information");
			ns.createNotification(empN);
			rts.updateRequestTracker(requestT);
			break;
		case 3:// benco just approved the form
			requestT.setState(7);
			rts.updateRequestTracker(requestT);
			empN.setBody(
					"A reimbursement request filed by you has been approved pending a passing grade. Click \"View Reimbursements\" for more information");
			ns.createNotification(empN);
			break;
		default:
			response.sendError(418,
					"Something went wrong with the switch statement in RequestTrackerController.approve()");
			break;
		}
	}

	@Override
	public void viewMyRequests(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee emp = RequestHelper.gson.fromJson(RequestHelper.sess.getAttribute("loggedInEmployee").toString(),
				Employee.class);

		List<RequestTracker> allRT = new ArrayList<RequestTracker>();
		List<Employee> allEmployee = new ArrayList<Employee>();
		List<Event> allEvent = new ArrayList<Event>();
		List<EventType> allEventType = new ArrayList<EventType>();
		List<Request> allRequest = new ArrayList<Request>();
		List<RequestNote> requestNotes = rns.getAllRequestNotes();
		for (RequestTracker rt : rts.getAllRequestTrackers()) {
			if (rt.getSupId() == emp.getSupId()) {
				Request newRequest = rs.getRequestById(rt.getRequestId());
				Event getEvent = es.getEventById(newRequest.getEventId());
				Employee getEmp = empS.getEmployeeById(newRequest.getEmpId());
				EventType getEventType = ets.getEventTypeById(getEvent.getEventTypeId());
				allRT.add(rt);
				allEmployee.add(getEmp);
				allEvent.add(getEvent);
				allEventType.add(getEventType);
				allRequest.add(newRequest);
			}
		}
		String output = "[";

		for (int i = 0; i < allRT.size() - 1; i++) {

			List<RequestNote> temp = new ArrayList<RequestNote>();
			StringBuilder notes = new StringBuilder("\"requestNotes\": [");
			for (int j = 0; j < requestNotes.size(); j++) {
				RequestNote rn = requestNotes.get(j);
				if (rn.getRequestId() == allRequest.get(i).getId()) {
					notes.append("\"" + rn.getBody() + "\",");
					temp.add(rn);
				}
			}

			notes.deleteCharAt(notes.length() - 1);
			notes.append("],");
			if (requestNotes == null || temp.size() == 0) {
				notes.delete(0, notes.length());
				notes.append("\"requestNotes\": [],");
			}

			String empName = "{ \"employeeName\":\"" + allEmployee.get(i).getFname() + " "
					+ allEmployee.get(i).getLname() + "\",";
			String idOfRt = "\"idOfRt\":\"" + allRT.get(i).getId() + "\",";
			String dateOfRequest = "\"dateOfRequest\":\"" + allRequest.get(i).getDate() + "\",";
			String dateOfEvent = "\"dateOfEvent\":\"" + allEvent.get(i).getDate() + "\",";
			String reimbursementAmount = "\"reimbursementAmount\": " + allRequest.get(i).getAmount() + ",";
			String location = "\"location\":\"" + allEvent.get(i).getLocation() + "\",";
			String eventType = "\"eventType\":\"" + allEventType.get(i).getName() + "\",";
			String justification = "\"justification\":\"" + allRequest.get(i).getJustfication() + "\",";
			String relatedAttachments = "\"relatedAttachments\":\"" + allEvent.get(i).getRelatedAttachment() + "\",";
			String attachedApproval = "\"attachedApproval\":\"" + allRequest.get(i).getAttachedApproval() + "\",";
			String state = "\"state\":\"" + allRT.get(i).getState() + "\",";
			String workMissed = "\"workMissed\":" + allRequest.get(i).getTimeMissed() + "},";
			output += empName + dateOfRequest + dateOfEvent + reimbursementAmount + location + eventType + justification
					+ relatedAttachments + attachedApproval + state + idOfRt + notes.toString() + workMissed;
		}
		int i = (allRT.size() - 1);

		List<RequestNote> temp = new ArrayList<RequestNote>();
		StringBuilder notes = new StringBuilder("\"requestNotes\": [");
		for (int j = 0; j < requestNotes.size(); j++) {
			RequestNote rn = requestNotes.get(j);
			if (rn.getRequestId() == allRequest.get(i).getId()) {
				notes.append("\"" + rn.getBody() + "\",");
				temp.add(rn);
			}
		}

		notes.deleteCharAt(notes.length() - 1);
		notes.append("],");
		if (requestNotes == null || temp.size() == 0) {
			notes.delete(0, notes.length());
			notes.append("\"requestNotes\": [],");
		}

		String empName = "{ \"employeeName\":\"" + allEmployee.get(i).getFname() + " " + allEmployee.get(i).getLname()
				+ "\",";
		String idOfRt = "\"idOfRt\":\"" + allRT.get(i).getId() + "\",";
		String dateOfRequest = "\"dateOfRequest\":\"" + allRequest.get(i).getDate() + "\",";
		String dateOfEvent = "\"dateOfEvent\":\"" + allEvent.get(i).getDate() + "\",";
		String reimbursementAmount = "\"reimbursementAmount\": " + allRequest.get(i).getAmount() + ",";
		String location = "\"location\":\"" + allEvent.get(i).getLocation() + "\",";
		String eventType = "\"eventType\":\"" + allEventType.get(i).getName() + "\",";
		String justification = "\"justification\":\"" + allRequest.get(i).getJustfication() + "\",";
		String relatedAttachments = "\"relatedAttachments\":\"" + allEvent.get(i).getRelatedAttachment() + "\",";
		String attachedApproval = "\"attachedApproval\":\"" + allRequest.get(i).getAttachedApproval() + "\",";
		String state = "\"state\":\"" + allRT.get(i).getState() + "\",";
		String workMissed = "\"workMissed\":" + allRequest.get(i).getTimeMissed() + "}";
		output += empName + dateOfRequest + dateOfEvent + reimbursementAmount + location + eventType + justification
				+ relatedAttachments + attachedApproval + idOfRt + state + notes.toString() + workMissed + "]";
		response.getWriter().append(output);

	}

	@Override
	public void cancel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RequestTracker requestT = RequestHelper.gson.fromJson(request.getReader(), RequestTracker.class);
		requestT = rts.getRequestTrackerById(requestT.getRequestId());

		// let who ever was in line to approve this request know it had been canceled
		Notifications n = new Notifications();
		n.setBody("A reimbursement request pending your approval has been canceled by the owner.");
		n.setSeen(false);

		switch (requestT.getState()) {
		case 1:
			n.setEmpId(requestT.getSupId());
			break;
		case 2:
			n.setEmpId(requestT.getDepId());
			break;
		case 3:
			n.setEmpId(requestT.getBencoId());
			break;
		default:
			n.setEmpId(0);
			break;
		}

		ns.createNotification(n);

		// now set state to be canceled by owner and update the request tracker
		requestT.setState(6);
		rts.updateRequestTracker(requestT);

		// used to get the employee id out of this table
		Request r = rs.getRequestById(requestT.getRequestId());

		// give our employee his funds back
		Employee emp = empS.getEmployeeById(r.getEmpId());
		emp.setFunds(emp.getFunds() + r.getAmount());
		empS.updateEmployee(emp);
		RequestHelper.sess.setAttribute("loggedInEmployee", RequestHelper.gson.toJson(emp));
		// append a note to our request
		RequestNote rn = new RequestNote();
		rn.setRequestId(r.getId());
		rn.setBody("Request canceled by owner");
		rns.createRequestNote(rn);
	}

	@Override
	public void submitGrade(HttpServletRequest request, HttpServletResponse response) throws IOException {
		GradingFormat gf = RequestHelper.gson.fromJson(request.getReader(), GradingFormat.class);
		gf = gfs.getGradingFormatById(gf.getId());
		Request r = RequestHelper.gson.fromJson(RequestHelper.sess.getAttribute("request").toString(), Request.class);
		RequestTracker rt = rts.getRequestTrackerById(r.getId());
		rt.setGrade(gf.getId());
		
		// if our person failed they do not get reimbursed
		if (!gf.isPass()) {
			Employee e = empS.getEmployeeById(r.getEmpId());
			e.setFunds(e.getFunds()+r.getAmount());
			empS.updateEmployee(e);
			RequestHelper.sess.setAttribute("loggedInEmployee", RequestHelper.gson.toJson(e));
			
			//create a notification that they did not pass
			Notifications n = new Notifications();
			n.setEmpId(e.getId());
			n.setBody("Due to not receiving a passing grade on your event. Your request for reimbursement has been denied");
			ns.createNotification(n);
			
			//Append a request note for why they did not pass
			RequestNote rn = new RequestNote();
			rn.setRequestId(r.getId());
			rn.setBody("Due to not receiving a passing grade on your event. Your request for reimbursement has been denied");
			rns.createRequestNote(rn);
			
			//update request tracker to denied state
			rt.setState(5);
			rts.updateRequestTracker(rt);
		}else {//our employee passed
			RequestNote rn = new RequestNote();
			rn.setRequestId(r.getId());
			rn.setBody("You have passed your event and it has been approved. Funds have been transfered");
			rns.createRequestNote(rn);
			
			//update request tracker to approved state
			rt.setState(4);
			rts.updateRequestTracker(rt);
			
		}

	}

}