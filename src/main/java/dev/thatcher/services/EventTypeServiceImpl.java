package dev.thatcher.services;

import java.util.List;

import dev.thatcher.models.EventType;
import dev.thatcher.repositories.EventTypeRepository;
import dev.thatcher.repositories.EventTypeRepositoryImpl;

public class EventTypeServiceImpl implements EventTypeService{
	public EventTypeRepository etr = new EventTypeRepositoryImpl();

	@Override
	public EventType createEventType(EventType e) {
		return etr.createEventType(e);
	}

	@Override
	public EventType getEventTypeById(int id) {
		return etr.getEventTypeById(id);
	}

	@Override
	public List<EventType> getAllEventTypes() {
		return etr.getAllEventTypes();
	}

	@Override
	public boolean updateEventType(EventType e) {
		return etr.updateEventType(e);
	}

	@Override
	public boolean deleteEventType(EventType e) {
		return etr.deleteEventType(e);
	}
}
