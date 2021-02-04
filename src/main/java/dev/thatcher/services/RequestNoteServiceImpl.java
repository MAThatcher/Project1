package dev.thatcher.services;

import java.util.List;

import dev.thatcher.models.RequestNote;
import dev.thatcher.repositories.RequestNoteRepository;
import dev.thatcher.repositories.RequestNoteRepositoryImpl;

public class RequestNoteServiceImpl implements RequestNoteService{
	RequestNoteRepository rnr = new RequestNoteRepositoryImpl();

	@Override
	public RequestNote createRequestNote(RequestNote rn) {
		return rnr.createRequestNote(rn);
	}

	@Override
	public RequestNote getRequestNoteById(int id) {
		return rnr.getRequestNoteById(id);
	}

	@Override
	public List<RequestNote> getAllRequestNotes() {
		return rnr.getAllRequestNotes();
	}

	@Override
	public boolean updateRequestNote(RequestNote rn) {
		return rnr.updateRequestNote(rn);
	}

	@Override
	public boolean deleteRequestNote(RequestNote rn) {
		return rnr.deleteRequestNote(rn);
	}
}
