package com.bridgelabz.fundoonoteapp.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonoteapp.user.model.Note;
import com.bridgelabz.fundoonoteapp.user.service.NoteService;

@RestController
public class NoteController {

	@Autowired
	private NoteService noteService;

	// Create
	@PostMapping(value = "/note")
	// @RequestMapping(value = "/createNote", method = RequestMethod.POST)
	public Note createNote(@RequestBody Note note, HttpServletRequest request) {

		return noteService.createNote(request.getHeader("token"), note);
	}

	// update
	@PutMapping(value = "/note")
	// @RequestMapping(value = "/updateNote", method = RequestMethod.PUT)
	public Note updateNote(@RequestBody Note note, HttpServletRequest request) {

		return noteService.updateNote(request.getHeader("token"), note);
	}

	// delete
	@DeleteMapping(value = "/note")
	// @RequestMapping(value = "/deleteNote", method = RequestMethod.DELETE)
	public void deleteNote(@RequestBody Note note, HttpServletRequest request) {
		System.out.println("I am token at delete method :" + request.getHeader("token"));
	//	boolean deleteNote = noteService.deleteNote(request.getHeader("token"), note);

	}

	// fetch
	@GetMapping(value = "/notes")
	// @RequestMapping(value = "/fetchNote", method = RequestMethod.GET)
	public List<Note> getNotes(HttpServletRequest request) {
		System.out.println("I am token at get method :" + request.getHeader("token"));
		return noteService.getNotes(request.getHeader("token"));

	}

}