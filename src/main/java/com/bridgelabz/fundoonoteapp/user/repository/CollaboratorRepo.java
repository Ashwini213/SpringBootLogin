package com.bridgelabz.fundoonoteapp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonoteapp.user.model.Collaborator;

@Repository
public interface CollaboratorRepo extends JpaRepository<Collaborator, Integer> {

	List<Collaborator> findByNoteId(int noteId);
//	@Query()
	List<Collaborator> findAllByOwnerId(int userId);

	@Query("select u.id from User u where u.email = :email")
	int findIdByEmail(String email);
	
//	 @Query("select c.allocated_id from collaborator c where c.owner_id = ?1")
//	 List<Collaborator> findAllByOwnerId(int ownerid);

}