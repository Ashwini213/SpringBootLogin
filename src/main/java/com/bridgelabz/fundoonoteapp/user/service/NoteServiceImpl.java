package com.bridgelabz.fundoonoteapp.user.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonoteapp.user.model.Lable;
import com.bridgelabz.fundoonoteapp.user.model.Notes;
import com.bridgelabz.fundoonoteapp.user.repository.LableRepository;
import com.bridgelabz.fundoonoteapp.user.repository.NoteRepository;
import com.bridgelabz.fundoonoteapp.util.JsonToken;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	public NoteRepository noteRep;
	@Autowired
	public LableRepository lableRepository;
	@Autowired
	public JsonToken jsonToken;

	@Override
	public Notes createNote(String token, Notes note) {
		int varifiedUserId = jsonToken.tokenVerification(token);
		System.out.println("note creation :" + varifiedUserId);
		note.setUserId(varifiedUserId);
		return noteRep.save(note);
	}

	@Override
	public Notes updateNote(String token, Notes note) {
		int varifiedUserId = jsonToken.tokenVerification(token);
		System.out.println("varifiedUserId :" + varifiedUserId);
		Optional<Notes> maybeNote = noteRep.findByUserIdAndNoteId(varifiedUserId, note.getNoteId());
		System.out.println("maybeNote :" + maybeNote);
		Notes presentNote = maybeNote.map(existingNote -> {
			System.out.println("noteee here");
			existingNote.setDiscription(
					note.getDiscription() != null ? note.getDiscription() : maybeNote.get().getDiscription());
			existingNote.setTitle(note.getTitle() != null ? note.getTitle() : maybeNote.get().getTitle());
			return existingNote;
		}).orElseThrow(() -> new RuntimeException("Note Not Found"));

		return noteRep.save(presentNote);
	}

	@Override
	public boolean deleteNote(String token, Notes note) {
		int varifiedUserId = jsonToken.tokenVerification(token);
		noteRep.deleteByUserIdAndNoteId(varifiedUserId, note.getNoteId());
		return true;
	}

	@Override
	public List<Notes> fetchNote(String token) {
		int varifiedUserId = jsonToken.tokenVerification(token);
		System.out.println("i m in fetch :" + varifiedUserId);

		List<Notes> notes = (List<Notes>) noteRep.findByUserId(varifiedUserId);

		return notes;
	}

	@Override
	public Lable createLable(String token, Lable lable) {
		int varifiedUserId = jsonToken.tokenVerification(token);
		System.out.println("lable creation :" + varifiedUserId);
		lable.setUserId(varifiedUserId);
		return lableRepository.save(lable);
	}

	@Override
	public Lable updateLable(String token, Lable lable) {
		int varifiedUserId = jsonToken.tokenVerification(token);
		System.out.println("varifiedUserId :" + varifiedUserId);
		Optional<Lable> maybeLable = lableRepository.findByUserIdAndLableId(varifiedUserId, lable.getLableId());
		System.out.println("maybeLable :" + maybeLable);
		Lable presentNote = maybeLable.map(existingNote -> {
			System.out.println("noteee here");
			existingNote.setLableName(
					lable.getLableName() != null ? lable.getLableName() : maybeLable.get().getLableName());
			return existingNote;
		}).orElseThrow(() -> new RuntimeException("Note Not Found"));

		return lableRepository.save(presentNote);
	}

	@Override
	public boolean deleteLable(String token, Lable lable) {
		int varifiedUserId = jsonToken.tokenVerification(token);
		lableRepository.deleteByUserIdAndLableId(varifiedUserId, lable.getLableId());
		return true;
	}

	@Override
	public List<Lable> fetchLable(String token) {
		int varifiedUserId = jsonToken.tokenVerification(token);
		System.out.println("i m in fetch :" + varifiedUserId);

		List<Lable> lable = (List<Lable>) lableRepository.findByUserId(varifiedUserId);

		return lable;
	}

}