package dev.thatcher.repositories;

import java.util.List;

import dev.thatcher.models.EventType;

public interface EventTypeRepository {

	public EventType createEventType(EventType e);

	public EventType getEventTypeById(int id);

	public List<EventType> getAllEventTypes();

	public boolean updateEventType(EventType e);

	public boolean deleteEventType(EventType e);
}
