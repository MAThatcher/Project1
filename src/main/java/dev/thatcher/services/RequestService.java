package dev.thatcher.services;

import java.util.List;

import dev.thatcher.models.Request;

public interface RequestService {
	public Request createRequest(Request r);

	public Request getRequestById(int id);

	public List<Request> getAllRequests();

	public boolean updateRequest(Request r);

	public boolean deleteRequest(Request r);
}
