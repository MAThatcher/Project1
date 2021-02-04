package dev.thatcher.repositories;

import java.util.List;

import dev.thatcher.models.Event;

public interface EventRepository {
	public Event createEvent(Event e);

	public Event getEventById(int id);

	public List<Event> getAllEvents();

	public boolean updateEvent(Event e);

	public boolean deleteEvent(Event e);
}
