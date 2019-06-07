package com.bridgelabz.fundoonoteapp.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonoteapp.user.model.Note;
import com.bridgelabz.fundoonoteapp.user.service.NoteService;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class NoteController {

	@Autowired
	private NoteService noteService;

	// Create
	@PostMapping(value = "/note/{token}")
	// @RequestMapping(value = "/createNote", method = RequestMethod.POST)
	public Note createNote(@PathVariable String token, @RequestBody Note note, HttpServletRequest request) {

		return noteService.createNote(token, note);// request.getHeader("token"), note);
	}

	// update
	@PutMapping(value = "/note/{token}")
	// @RequestMapping(value = "/updateNote", method = RequestMethod.PUT)
	public Note updateNote(@PathVariable String token, @RequestBody Note note, HttpServletRequest request) {

		return noteService.updateNote(token, note);     //request.getHeader("token"), note);
	}

	// delete
	@DeleteMapping(value = "/note/{token}")
	// @RequestMapping(value = "/deleteNote", method = RequestMethod.DELETE)
	public String deleteNote(@PathVariable String token, @RequestBody Note note, HttpServletRequest request) {
		System.out.println("I am token at delete method :" + request.getHeader("token"));
		// boolean deleteNote = noteService.deleteNote(request.getHeader("token"),
		return noteService.deleteNote(token, note);
		// note);

	}
	/*@DeleteMapping(value = "/delete/{noteId}")
	public String noteDelete(@PathVariable int noteId, HttpServletRequest request) {
		String token = request.getHeader("token");
		return noteService.deleteNote(noteId,token);

	}
*/
	// fetch
	@GetMapping(value = "/notes/{token}")
	// @RequestMapping(value = "/fetchNote", method = RequestMethod.GET)
	public List<Note> getNotes(@PathVariable String token, HttpServletRequest request) {
		System.out.println("I am token at get method :" + request.getHeader("token"));
		return noteService.getNotes(token);  //request.getHeader("token"));

	}

	@Cacheable(value = "users", key = "#userId")
	@GetMapping("/testRedis/{userId}")
	// @ApiResponse(response = String.class, message = "Test Redis ", code = 200)
	public String testRedis(@ApiParam("userId") @PathVariable String userId) {
		return "Success" + userId;
	}

	// @CachePut(key = "test")
	@Cacheable(value = "users", key = "#userId")
	@PostMapping("/testRedis/{userId}")
	@ApiResponse(response = String.class, message = "Test Redis post", code = 200)
	public String postRedis(@ApiParam("userId") @PathVariable String userId) {
		return "{}";
	}

}