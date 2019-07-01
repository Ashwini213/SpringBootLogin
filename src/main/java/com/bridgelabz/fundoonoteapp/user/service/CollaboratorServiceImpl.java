package com.bridgelabz.fundoonoteapp.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonoteapp.user.model.Collaborator;
import com.bridgelabz.fundoonoteapp.user.repository.CollaboratorRepo;
import com.bridgelabz.fundoonoteapp.util.Util;

@Service
public class CollaboratorServiceImpl implements CollaboratorServiceInf {

	@Autowired
	private CollaboratorRepo collaboratorRepo;

	public boolean addCollaborator(String token, Collaborator collaborator) {
		System.out.println("collaborator:==>" + collaborator);
		// collaborator.getAllocatedId();
		// int id = findIdByEmail(collaborator.getEmail());
		// collaborator.setOwnerId(collaborator.getOwnerId());
		// collaborator.setCollId(id);
		// collaborator.setNoteId(14);
		// collaborator.setAllocatedId(29);
		// collaborator.setAllocatedId(0);
		try {
			collaboratorRepo.save(collaborator);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean removeCollaborator(String token, Collaborator colaborater) {
		// tokenGenerator.authenticateToken(token);

		Util.tokenVerification(token);
		try {
			collaboratorRepo.deleteById(colaborater.getCollId());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}