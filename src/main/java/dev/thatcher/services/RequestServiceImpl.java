package dev.thatcher.services;

import java.util.List;

import dev.thatcher.models.Employee;
import dev.thatcher.models.Notifications;
import dev.thatcher.models.Request;
import dev.thatcher.models.RequestTracker;
import dev.thatcher.repositories.RequestRepository;
import dev.thatcher.repositories.RequestRepositoryImpl;
import dev.thatcher.servlets.RequestHelper;

public class RequestServiceImpl implements RequestService {
	public static RequestRepository rr = new RequestRepositoryImpl();
	public static RequestTrackerService rts = new RequestTrackerServiceImpl();
	public static EmployeeServices e = new EmployeeServicesImpl();
	public static NotificationService n = new NotificationServiceImpl();
	
	@Override
	public Request createRequest(Request r) {
		Employee emp = e.getEmployeeById(r.getEmpId());
		emp.setFunds(emp.getFunds()-r.getAmount());
		e.updateEmployee(emp);
		RequestHelper.sess.setAttribute("loggedInEmployee", RequestHelper.gson.toJson(emp));
		Request req = rr.createRequest(r);
		RequestTracker reqT = new RequestTracker();
		reqT.setSupId(emp.getSupId());
		reqT.setRequestId(req.getId());

		// get dep head for reqt
		emp = e.getEmployeeById(emp.getSupId());
		if (emp.isDepHead()) {// check if the supervisor is all ready a department head
			reqT.setDepId(emp.getId());
		} else {
			while (!emp.isDepHead()) {
				emp = e.getEmployeeById(emp.getSupId());
			}
			reqT.setDepId(emp.getId());
		} // at this point, the employee we are looking at is a department head also
		// select random benco to this request ticket
		int randNum = (int) (Math.random() * 4) + 12;
		reqT.setBencoId(randNum);
		rts.createRequestTracker(reqT);
		Notifications newN = new Notifications();
		newN.setBody("You have recieved a pending request to approve or dissaprove. Please click on \"View Incoming Reimbursement Requests\" to see the request");
		newN.setEmpId(reqT.getSupId());
		n.createNotification(newN);
		return req;
	}

	@Override
	public Request getRequestById(int id) {
		return rr.getRequestById(id);
	}

	@Override
	public List<Request> getAllRequests() {
		return rr.getAllRequests();
	}

	@Override
	public boolean updateRequest(Request r) {
		return rr.updateRequest(r);
	}

	@Override
	public boolean deleteRequest(Request r) {
		return rr.deleteRequest(r);
	}
}
