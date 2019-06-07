package com.bridgelabz.fundoonoteapp.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonoteapp.user.model.Note;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

	Optional<Note> findByUserIdAndNoteId(int varifiedUserId, int noteid);
	public List<Note> findByNoteIdAndUserId(int noteId,int userId);


	void deleteByUserIdAndNoteId(int varifiedUserId, int noteId);

	List<Note> findByUserId(int varifiedUserId);

}
