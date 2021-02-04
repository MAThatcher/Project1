package dev.thatcher.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.thatcher.models.GradingFormat;
import dev.thatcher.services.GradingFormatService;
import dev.thatcher.services.GradingFormatServiceImpl;
import dev.thatcher.servlets.RequestHelper;

public class GradingFormatControllerImpl implements GradingFormatController {
	GradingFormatService gs = new GradingFormatServiceImpl();
	@Override
	public void getGradingFormats(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<GradingFormat> gf = gs.getAllGradingFormat();
		response.getWriter().append((!gf.isEmpty()) ? RequestHelper.gson.toJson(gf) : "{}");

	}

}
