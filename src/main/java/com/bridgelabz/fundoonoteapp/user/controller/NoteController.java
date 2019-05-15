package com.bridgelabz.fundoonoteapp.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonoteapp.user.model.Notes;
import com.bridgelabz.fundoonoteapp.user.service.NoteService;

@RestController
public class NoteController {

	@Autowired
	NoteService noteService;

	// Create
	@RequestMapping(value = "/createNote", method = RequestMethod.POST)
	public Notes createNote(@RequestBody Notes note, HttpServletRequest request) {

		return noteService.createNote(request.getHeader("token"), note);
	}

	// update

	@RequestMapping(value = "/updateNote", method = RequestMethod.PUT)
	public Notes updateNote(@RequestBody Notes note, HttpServletRequest request) {

		return noteService.updateNote(request.getHeader("token"), note);
	}

	// delete

	@RequestMapping(value = "/deleteNote", method = RequestMethod.DELETE)
	public void deleteNote(@RequestBody Notes note, HttpServletRequest request) {
		System.out.println("I am token at delete method :" + request.getHeader("token"));
		boolean deleteNote = noteService.deleteNote(request.getHeader("token"), note);

	}

	// fetch

	@RequestMapping(value = "/fetchNote", method = RequestMethod.GET)
	public List<Notes> fetchNote(HttpServletRequest request) {
		System.out.println("I am token at get method :" + request.getHeader("token"));
		return noteService.fetchNote(request.getHeader("token"));

	}

}