package com.bridgelabz.fundoonoteapp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonoteapp.user.model.Notes;
@Repository
public interface NoteReposirory extends JpaRepository<Notes, Long> {
	public List<Notes> findById(int id);

	public List<Notes> findByNoteid(int noteid);
}