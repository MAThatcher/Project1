package dev.thatcher.services;

import java.util.List;

import dev.thatcher.models.RequestTracker;
import dev.thatcher.repositories.RequestTrackerRepository;
import dev.thatcher.repositories.RequestTrackerRepositoryImpl;

public class RequestTrackerServiceImpl implements RequestTrackerService{
	RequestTrackerRepository rtr = new RequestTrackerRepositoryImpl();
	@Override
	public RequestTracker createRequestTracker(RequestTracker rt) {
		return rtr.createRequestTracker(rt);
	}

	@Override
	public RequestTracker getRequestTrackerById(int id) {
		return rtr.getRequestTrackerById(id);
	}

	@Override
	public List<RequestTracker> getAllRequestTrackers() {
		return rtr.getAllRequestTrackers();
	}

	@Override
	public boolean updateRequestTracker(RequestTracker rt) {
		return rtr.updateRequestTracker(rt);
	}

	@Override
	public boolean deleteRequestTracker(RequestTracker rt) {
		return rtr.deleteRequestTracker(rt);
	}

}
