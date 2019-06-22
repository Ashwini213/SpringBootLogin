package com.bridgelabz.fundoonoteapp.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonoteapp.user.model.Label;
import com.bridgelabz.fundoonoteapp.user.service.CustomException;
import com.bridgelabz.fundoonoteapp.user.service.NoteService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LableController {
	
	@Autowired
	private NoteService noteService;

	// @RequestMapping(value = "/createlable", method = RequestMethod.POST)
	@PostMapping(value = "/label")
	public Label createLabel(@RequestBody Label label, HttpServletRequest request) {

		return noteService.createLabel(request.getHeader("token"), label);
	}

	// update
	@PutMapping(value = "/label")
	// @RequestMapping(value = "/updateLable", method = RequestMethod.PUT)
	public Label updateLabel(@RequestBody Label label, HttpServletRequest request) throws CustomException {

		return noteService.updateLabel(request.getHeader("token"), label);
	}

	// delete

	// @RequestMapping(value = "/deletelable", method = RequestMethod.DELETE)
	@DeleteMapping(value = "/label")
	public void deleteLabel(@RequestBody Label label, HttpServletRequest request) {
		System.out.println("I am token at delete method :" + request.getHeader("token"));

	}

	// fetch
	@GetMapping(value = "/label")
	// @RequestMapping(value = "/fetchlable", method = RequestMethod.GET)
	public List<Label> getLables(HttpServletRequest request) {
		System.out.println("I am token at get method :" + request.getHeader("token"));
		return noteService.getLabels(request.getHeader("token"));

	}

}
