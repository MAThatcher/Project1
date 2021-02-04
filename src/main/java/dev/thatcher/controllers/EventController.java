package dev.thatcher.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface EventController {
	public void createEvent(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
