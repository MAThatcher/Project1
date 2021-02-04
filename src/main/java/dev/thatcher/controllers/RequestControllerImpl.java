package dev.thatcher.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.thatcher.models.Employee;
import dev.thatcher.models.Request;
import dev.thatcher.services.RequestService;
import dev.thatcher.services.RequestServiceImpl;
import dev.thatcher.servlets.RequestHelper;

public class RequestControllerImpl implements RequestController {
	public static RequestService rs = new RequestServiceImpl();

	@Override
	public void submitRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee e = RequestHelper.gson.fromJson(RequestHelper.sess.getAttribute("loggedInEmployee").toString(),Employee.class);
		Request r = RequestHelper.gson.fromJson(request.getReader(), Request.class);
		r.setEmpId(e.getId());
		System.out.println(r);
		rs.createRequest(r);
	}
}
