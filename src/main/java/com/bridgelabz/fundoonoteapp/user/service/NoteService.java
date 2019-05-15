package com.bridgelabz.fundoonoteapp.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.fundoonoteapp.user.model.Notes;

public interface NoteService {


	public Notes noteCreate(Notes note, HttpServletRequest request);

	public List<Notes> findById(int userId);

	public Notes updateNote(Notes note,int noteId);

	public String deleteNote(int noteId);

	public Notes getNoteInfo(int noteId);

	public List<Notes> getAllNotes();
}
