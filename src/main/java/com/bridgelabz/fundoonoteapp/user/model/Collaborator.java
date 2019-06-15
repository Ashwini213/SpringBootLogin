package com.bridgelabz.fundoonoteapp.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Collaborator")
public class Collaborator {

	@Override
	public String toString() {
		return "Collaborator [collId=" + collId + ", noteId=" + noteId + ", ownerId=" + ownerId + ", allocatedId="
				+ allocatedId + "]";
	}

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "collId")
	private int collId;

	@Column(name = "noteId")
	private int noteId;

	@Column(name = "ownerId")
	private int ownerId;

	@Column(name = "allocatedId")
	private int allocatedId;

	public int getAllocatedId() {
		return allocatedId;
	}

	public void setAllocatedId(int allocatedId) {
		this.allocatedId = allocatedId;
	}

	public int getCollId() {
		return collId;
	}

	public void setCollId(int collId) {
		this.collId = collId;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

}
