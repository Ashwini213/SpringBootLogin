package com.bridgelabz.fundoonoteapp.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonoteapp.user.model.Label;
import com.bridgelabz.fundoonoteapp.user.model.Note;
import com.bridgelabz.fundoonoteapp.user.repository.LabelRepository;
import com.bridgelabz.fundoonoteapp.user.repository.NoteRepository;
import com.bridgelabz.fundoonoteapp.util.Util;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	public NoteRepository noteRep;
	@Autowired
	public LabelRepository labelRepository;

	@Override
	public Note createNote(String token, Note note) {
		// int verifiedUserId = JsonUtil.tokenVerification(token);
		System.out.println("note creation :" + Util.tokenVerification(token));

		LocalDateTime creadtedTime = LocalDateTime.now();
		note.setCreadtedTime(creadtedTime);
		note.setUserId(Util.tokenVerification(token));
		return noteRep.save(note);
	}

	@Override
	public Note updateNote(String token, Note note) {
		// int verifiedUserId = JsonUtil.tokenVerification(token);
		System.out.println("varifiedUserId :" + Util.tokenVerification(token));
		Optional<Note> maybeNote = noteRep.findByUserIdAndNoteId(Util.tokenVerification(token), note.getNoteId());
		System.out.println("maybeNote :" + maybeNote);
		Note presentNote = maybeNote.map(existingNote -> {
			System.out.println("noteee here");
			existingNote.setDiscription(
					note.getDiscription() != null ? note.getDiscription() : maybeNote.get().getDiscription());
			existingNote.setTitle(note.getTitle() != null ? note.getTitle() : maybeNote.get().getTitle());
			return existingNote;
		}).orElseThrow(() -> new RuntimeException("Note Not Found"));

		return noteRep.save(presentNote);
	}

	@Override
	public boolean deleteNote(String token, Note note) {
		// int verifiedUserId = JsonUtil.tokenVerification(token);
		noteRep.deleteByUserIdAndNoteId(Util.tokenVerification(token), note.getNoteId());
		return true;
	}

	@Override
	public List<Note> getNotes(String token) {

		return noteRep.findByUserId(Util.tokenVerification(token));

	}

	@Override
	public Label createLabel(String token, Label label) {
		// int verifiedUserId = JsonUtil.tokenVerification(token);
		System.out.println("lable creation :" + Util.tokenVerification(token));
		label.setUserId(Util.tokenVerification(token));
		return labelRepository.save(label);
	}

	@Override
	public Label updateLabel(String token, Label label) throws CustomException {
		// int verifiedUserId = JsonUtil.tokenVerification(token);
		System.out.println("varifiedUserId :" + Util.tokenVerification(token));
		Optional<Label> maybeLable = labelRepository.findByUserIdAndLableId(Util.tokenVerification(token),
				label.getLableId());
		System.out.println("maybeLable :" + maybeLable);
		Label presentNote = maybeLable.map(existingNote -> {
			System.out.println("noteee here");
			existingNote.setLableName(
					label.getLableName() != null ? label.getLableName() : maybeLable.get().getLableName());
			return existingNote;
		}).orElseThrow(() -> new CustomException("Note Not Found"));

		return labelRepository.save(presentNote);
	}

	@Override
	public boolean deleteLabel(String token, Label lable) {
		// int verifiedUserId = JsonUtil.tokenVerification(token);
		labelRepository.deleteByUserIdAndLableId(Util.tokenVerification(token), lable.getLableId());
		return true;
	}

	@Override
	public List<Label> getLabels(String token) {
		// int verifiedUserId = JsonUtil.tokenVerification(token);
		System.out.println("i m in fetch :" + Util.tokenVerification(token));

		List<Label> lable = labelRepository.findByUserId(Util.tokenVerification(token));

		return lable;
	}

}