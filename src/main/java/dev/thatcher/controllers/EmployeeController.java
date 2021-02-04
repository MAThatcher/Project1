package dev.thatcher.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface EmployeeController {

	public void getEmployee(HttpServletRequest request, HttpServletResponse response)throws IOException;
	public void create(HttpServletRequest request, HttpServletResponse response)throws IOException;
	public void login(HttpServletRequest request, HttpServletResponse response)throws IOException;
	public void getLoggedInEmployee(HttpServletRequest request, HttpServletResponse response)throws IOException;
	public void logOut(HttpServletRequest request, HttpServletResponse response)throws IOException;
	public void goToGrade(HttpServletRequest request, HttpServletResponse response)throws IOException;
}
