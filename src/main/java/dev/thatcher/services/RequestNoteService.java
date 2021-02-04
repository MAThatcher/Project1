package dev.thatcher.services;

import java.util.List;

import dev.thatcher.models.RequestNote;

public interface RequestNoteService {

	public RequestNote createRequestNote(RequestNote rn);

	public RequestNote getRequestNoteById(int id);

	public List<RequestNote> getAllRequestNotes();

	public boolean updateRequestNote(RequestNote rn);

	public boolean deleteRequestNote(RequestNote rn);
}
