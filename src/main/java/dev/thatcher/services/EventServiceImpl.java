package dev.thatcher.services;

import java.util.List;

import dev.thatcher.models.Event;
import dev.thatcher.repositories.EventRepository;
import dev.thatcher.repositories.EventRepositoryImpl;

public class EventServiceImpl implements EventService{
	public EventRepository er = new EventRepositoryImpl();

	@Override
	public Event createEvent(Event e) {
		return er.createEvent(e);
	}

	@Override
	public Event getEventById(int id) {
		return er.getEventById(id);
	}

	@Override
	public List<Event> getAllEvents() {
		return er.getAllEvents();
	}

	@Override
	public boolean updateEvent(Event e) {
		return er.updateEvent(e);
	}

	@Override
	public boolean deleteEvent(Event e) {
		return er.deleteEvent(e);
	}
}
