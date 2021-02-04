package dev.thatcher.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.thatcher.models.Employee;
import dev.thatcher.models.Request;
import dev.thatcher.models.RequestTracker;
import dev.thatcher.services.EmployeeServices;
import dev.thatcher.services.EmployeeServicesImpl;
import dev.thatcher.services.RequestService;
import dev.thatcher.services.RequestServiceImpl;
import dev.thatcher.servlets.RequestHelper;

public class EmployeeControllerImpl implements EmployeeController {
	public static EmployeeServices es = new EmployeeServicesImpl();
	public static RequestService rs = new RequestServiceImpl();

	
	public void getEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			id = -1;
			response.sendError(400, "ID parameter badly formatted, Not a number");
			// e.printStackTrace();
			return;
		}

		Employee emp = es.getEmployeeById(id);
		if (emp != null) {
			emp.setPassword(null);
		}
		response.getWriter().append((emp != null) ? RequestHelper.gson.toJson(emp) : "{}");
	}

	@Override
	public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee e = RequestHelper.gson.fromJson(request.getReader(), Employee.class);

		// call appropriate service for adding employee
		es.createEmployee(e);
		response.getWriter().append(RequestHelper.gson.toJson(e));
	}

	@Override
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee e = RequestHelper.gson.fromJson(request.getReader(), Employee.class);

		RequestHelper.sess = request.getSession();

		RequestHelper.sess.setMaxInactiveInterval(50000);

		String email = e.getEmail();
		String password = e.getPassword();
		e = es.login(email, password);

		if (e != null) {
			e.setPassword(null);

			RequestHelper.sess.setAttribute("loggedInEmployee", RequestHelper.gson.toJson(e));
			response.getWriter().append(RequestHelper.gson.toJson(e));
			//response.sendRedirect("http://localhost:8080/Project1/HTML/homePage.html");
		}else {
			response.getWriter().append(null);
		}
	}

	@Override
	public void getLoggedInEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			response.getWriter().append(RequestHelper.sess.getAttribute("loggedInEmployee").toString());
		} catch (IllegalStateException e) {
			// e.printStackTrace();
		} catch (NullPointerException e) {
			// e.printStackTrace();
		}
	}

	@Override
	public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			if (RequestHelper.sess != null) {
				RequestHelper.sess.invalidate();
				response.getWriter().append("true");
			}
		} catch (IllegalStateException e) {
			// e.printStackTrace();
		}
	}

	@Override
	public void goToGrade(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RequestTracker requestT = RequestHelper.gson.fromJson(request.getReader(), RequestTracker.class);
		Request r = rs.getRequestById(requestT.getRequestId());
		System.out.println(r);
		RequestHelper.sess.setAttribute("request", RequestHelper.gson.toJson(r));
		
	}
}
