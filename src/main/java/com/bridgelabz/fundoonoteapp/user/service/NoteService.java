package com.bridgelabz.fundoonoteapp.user.service;

import java.util.List;

import com.bridgelabz.fundoonoteapp.user.model.Lable;
import com.bridgelabz.fundoonoteapp.user.model.Notes;

public interface NoteService {

	Notes updateNote(String header, Notes note);

	Notes createNote(String header, Notes note);

	boolean deleteNote(String token, Notes note);

	List<Notes> fetchNote(String header);

	Lable createLable(String header, Lable lable);

	Lable updateLable(String token, Lable lable);

	boolean deleteLable(String token, Lable lable);

	List<Lable> fetchLable(String token);

}