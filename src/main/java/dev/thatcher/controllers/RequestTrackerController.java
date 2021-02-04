package dev.thatcher.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestTrackerController {
	public void viewRequests(HttpServletRequest request, HttpServletResponse response)throws IOException;
	public void deny(HttpServletRequest request, HttpServletResponse response)throws IOException;
	public void approve(HttpServletRequest request, HttpServletResponse response) throws IOException;
	public void viewMyRequests(HttpServletRequest request, HttpServletResponse response) throws IOException;
	public void cancel(HttpServletRequest request, HttpServletResponse response) throws IOException;
	public void submitGrade(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
