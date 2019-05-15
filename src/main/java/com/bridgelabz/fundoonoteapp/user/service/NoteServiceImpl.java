package com.bridgelabz.fundoonoteapp.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonoteapp.user.model.Notes;
import com.bridgelabz.fundoonoteapp.user.repository.NoteReposirory;
import com.bridgelabz.fundoonoteapp.util.NoteUtil;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	public NoteReposirory noteRep;

	@Override
	public Notes noteCreate(Notes note, HttpServletRequest request) {
		String token1 = request.getHeader("token");
		int id = NoteUtil.tokenVerification(token1);
		note.setId(id);
		return noteRep.save(note);
	}

	@Override
	public List<Notes> findById(int userid) {
		List<Notes> noteInfo = noteRep.findByNoteid(userid);
		return noteInfo;
	}

	@Override
	public Notes updateNote(Notes note, int id) {
		List<Notes> noteInfo = noteRep.findById(id);
		noteInfo.forEach(existingUser -> {
			existingUser.setCreatedTime(
					note.getCreatedTime() != null ? note.getCreatedTime() : noteInfo.get(0).getCreatedTime());
			existingUser.setDescription(
					note.getDescription() != null ? note.getDescription() : noteInfo.get(0).getDescription());
			existingUser.setTitle(note.getTitle() != null ? note.getTitle() : noteInfo.get(0).getTitle());
			existingUser.setUpdateTime(
					note.getUpdateTime() != null ? note.getUpdateTime() : noteInfo.get(0).getUpdateTime());
		});
		return noteRep.save(noteInfo.get(0));

	}

	@Override
	public String deleteNote(int id) {
		List<Notes> noteInfo = noteRep.findById(id);
		noteRep.delete(noteInfo.get(0));
		return "Deleted";
	}

	@Override
	public Notes getNoteInfo(int id) {
		List<Notes> noteInfo = noteRep.findById(id);
		return noteInfo.get(0);
	}

	@Override
	public List<Notes> getAllNotes() {

		return noteRep.findAll();
	}

}
