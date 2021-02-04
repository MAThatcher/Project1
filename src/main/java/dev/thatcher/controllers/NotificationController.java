package dev.thatcher.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface NotificationController {
	public void getNotification(HttpServletRequest request, HttpServletResponse response)throws IOException;
}
