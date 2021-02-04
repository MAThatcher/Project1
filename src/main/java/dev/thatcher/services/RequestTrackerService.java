package dev.thatcher.services;

import java.util.List;

import dev.thatcher.models.RequestTracker;

public interface RequestTrackerService {
	public RequestTracker createRequestTracker(RequestTracker rt);

	public RequestTracker getRequestTrackerById(int id);

	public List<RequestTracker> getAllRequestTrackers();

	public boolean updateRequestTracker(RequestTracker rt);

	public boolean deleteRequestTracker(RequestTracker rt);
}
