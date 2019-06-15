package com.bridgelabz.fundoonoteapp.user.service;

import com.bridgelabz.fundoonoteapp.user.model.Collaborator;

public interface CollaboratorServiceInf {

	boolean addCollaborator(String token, Collaborator collaborator);

	boolean removeCollaborator(String token, Collaborator colUser);

}
