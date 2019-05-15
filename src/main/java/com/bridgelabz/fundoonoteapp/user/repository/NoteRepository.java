package com.bridgelabz.fundoonoteapp.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonoteapp.user.model.Notes;

@Repository
public interface NoteRepository extends JpaRepository<Notes, Integer> {

	Optional<Notes> findByUserIdAndNoteId(int varifiedUserId, int noteId);

	void deleteByUserIdAndNoteId(int varifiedUserId, int noteId);

	List<Notes> findByUserId(int varifiedUserId);

//	public List<Notes> findByUserId(int varifiedUserId);
//
////	public Optional<Notes> findByUserIdAndNoteId(int varifiedUserId, int noteid);
//
//	public void deleteByUserIdAndNoteId(int varifiedUserId, int noteId);
//
//	public List<Notes> fetchByUserIdAndNoteId(int varifiedUserId, int noteId);
//
//	public Optional<Notes> findByUserIdAndNoteId(int varifiedUserId, int noteId);
//
//	public List<Notes> findAll();

}
