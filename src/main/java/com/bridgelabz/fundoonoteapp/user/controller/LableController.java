package com.bridgelabz.fundoonoteapp.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonoteapp.user.model.Lable;
import com.bridgelabz.fundoonoteapp.user.service.NoteService;

@RestController
public class LableController {
	@Autowired
	NoteService noteService;

	// Create
	@RequestMapping(value = "/createlable", method = RequestMethod.POST)
	public Lable createlable(@RequestBody Lable lable, HttpServletRequest request) {

		return noteService.createLable(request.getHeader("token"), lable);
	}

	// update

	@RequestMapping(value = "/updateLable", method = RequestMethod.PUT)
	public Lable updateLable(@RequestBody Lable lable, HttpServletRequest request) {

		return noteService.updateLable(request.getHeader("token"), lable);
	}

	// delete

	@RequestMapping(value = "/deletelable", method = RequestMethod.DELETE)
	public void deletelable(@RequestBody Lable lable, HttpServletRequest request) {
		System.out.println("I am token at delete method :" + request.getHeader("token"));
		boolean deletelable = noteService.deleteLable(request.getHeader("token"), lable);

	}

	// fetch

	@RequestMapping(value = "/fetchlable", method = RequestMethod.GET)
	public List<Lable> fetchlable(HttpServletRequest request) {
		System.out.println("I am token at get method :" + request.getHeader("token"));
		return noteService.fetchLable(request.getHeader("token"));

	}

}
