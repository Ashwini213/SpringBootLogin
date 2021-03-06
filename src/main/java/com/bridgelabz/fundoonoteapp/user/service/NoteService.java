package com.bridgelabz.fundoonoteapp.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.fundoonoteapp.user.model.Label;
import com.bridgelabz.fundoonoteapp.user.model.Note;

public interface NoteService {

	Note updateNote(String header, Note note);

	Note createNote(String header, Note note);

	// public String deleteNote(int noteId,String token);

	// List<Note> getNotes(String header);

	Label createLabel(String header, Label lable);

	Label updateLabel(String token, Label lable) throws CustomException;

	boolean deleteLabel(String token, Label lable);

	List<Label> getLabels(String token);

	String deleteNote(int noteId);

	// List<Note> retrieveNote(String token, HttpServletRequest request);

	List<Note> getNotes(String token, HttpServletRequest request);

	// boolean deleteNote(String token, Note note);

}