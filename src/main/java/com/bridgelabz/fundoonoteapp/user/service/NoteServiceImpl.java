package com.bridgelabz.fundoonoteapp.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonoteapp.user.model.Collaborator;
import com.bridgelabz.fundoonoteapp.user.model.Label;
import com.bridgelabz.fundoonoteapp.user.model.Note;
import com.bridgelabz.fundoonoteapp.user.repository.CollaboratorRepo;
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
	@Autowired
	public CollaboratorRepo collaboratorRepo;

	@Override
	public Note createNote(String token, Note note) {
		// System.out.println("note creation :" + Util.tokenVerification(token));
		//
		// LocalDateTime creadtedTime = LocalDateTime.now();
		// note.setCreadtedTime(creadtedTime);
		// note.setUserId(Util.tokenVerification(token));
		// return noteRep.save(note);
		int verifiedUserId = Util.tokenVerification(token);
		System.out.println("note creation :" + verifiedUserId);
		note.setUserId(verifiedUserId);
		return noteRep.save(note);

	}

	@Override
	public Note updateNote(String token, Note note) {
		// int verifiedUserId = JsonUtil.tokenVerification(token);
		System.out.println(note);
		System.out.println("varifiedUserId :" + Util.tokenVerification(token));
		Optional<Note> maybeNote = noteRep.findByUserIdAndNoteId(Util.tokenVerification(token), note.getNoteId());
		System.out.println("maybeNote :" + maybeNote);
		Note presentNote = maybeNote.map(existingNote -> {
			System.out.println("noteee here");
			existingNote.setDiscription(
					note.getDiscription() != null ? note.getDiscription() : maybeNote.get().getDiscription());
			existingNote.setTitle(note.getTitle() != null ? note.getTitle() : maybeNote.get().getTitle());
			existingNote.setIntrash(note.isIntrash());
			existingNote.setIsarchive(note.isIsarchive());
			existingNote.setIspinned(note.isIspinned());

			return existingNote;
		}).orElseThrow(() -> new RuntimeException("Note Not Found"));
		System.out.println(presentNote);

		return noteRep.save(presentNote);
	}

	@Override
	public String deleteNote(int noteId) {
		// int verifiedUserId = JsonUtil.tokenVerification(token);
		// noteRep.deleteByUserIdAndNoteId(Util.tokenVerification(token), noteId);
		noteRep.deleteByNoteId(noteId);
		return "Deleted";
	}

	/*
	 * @Override public String deleteNote(int noteId, String token) { int userId =
	 * Util.tokenVerification(token); List<Note> noteInfo =
	 * noteRep.findByNoteIdAndUserId(noteId, userId);
	 * noteRep.delete(noteInfo.get(0)); return "Deleted"; }
	 */
	/*
	 * @Override public List<Note> getNotes(String token) {
	 * 
	 * return noteRep.findByUserId(Util.tokenVerification(token));
	 * 
	 * }
	 */
	@Override
	public List<Note> getNotes(String token, HttpServletRequest request) {
		int userId = Util.tokenVerification(token);
		List<Note> notes = new ArrayList<Note>();
		List<Collaborator> collaborators = collaboratorRepo.findAllByOwnerId(userId);
		collaborators.forEach(collaborator -> {

			// notes.add(noteRep.findByUserId(collaborator.getNoteId().get();
			notes.add(noteRep.findByNoteId(collaborator.getNoteId()).get());
		});
		List<Note> newNotes = noteRep.findAllNoteByUserId(userId);

		notes.addAll(newNotes);
		return notes;
	}

	@Override
	public Label createLabel(String token, Label label) {
		// int verifiedUserId = JsonUtil.tokenVerification(token);
		System.out.println("lable creation :" + Util.tokenVerification(token));
		label.setUserId(Util.tokenVerification(token));
		return labelRepository.save(label);
	}

	@Override
	public Label updateLabel(String token, Label label) {
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
		}).orElseThrow(() -> new RuntimeException("Note Not Found"));

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