package com.bridgelabz.fundoonoteapp.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonoteapp.user.model.Notes;
import com.bridgelabz.fundoonoteapp.user.service.NoteService;

@RestController
public class NoteController {
	@Autowired
	private NoteService noteService;

	@RequestMapping(value = "/createnote", method = RequestMethod.POST)
	public Notes createUser(@RequestBody Notes note, HttpServletRequest request) {

		return noteService.noteCreate(note, request);

	}

	@RequestMapping(value = "/noteupdate/{noteId}", method = RequestMethod.PUT)
	public Notes noteUpdate(@RequestBody Notes note, @PathVariable int noteId) {

		return noteService.updateNote(note, noteId);

	}

	@RequestMapping(value = "/notedelete/{noteId}", method = RequestMethod.DELETE)
	public String noteDelete(@PathVariable int noteId) {

		return noteService.deleteNote(noteId);

	}

	@RequestMapping(value = "/note/{noteId}", method = RequestMethod.GET)
	public Notes noteInfo(@PathVariable int noteId) {
		return noteService.getNoteInfo(noteId);

	}

	@RequestMapping(value = "/notes", method = RequestMethod.GET)
	public List<Notes> noteList() {
		return noteService.getAllNotes();
	}

}
