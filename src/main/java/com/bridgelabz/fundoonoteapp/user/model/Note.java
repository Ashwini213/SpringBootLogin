package com.bridgelabz.fundoonoteapp.user.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "note")
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int noteId;
	private String title;
	private String discription;
	private LocalDateTime creadtedTime;
	private LocalDateTime updateTime;
	private boolean isarchive;
	private boolean ispinned;
	private boolean intrash;
	@Column(name = "userId")
	private int userId;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public LocalDateTime getCreadtedTime() {
		return creadtedTime;
	}

	public void setCreadtedTime(LocalDateTime creadtedTime) {
		this.creadtedTime = creadtedTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isIsarchive() {
		return isarchive;
	}

	public void setIsarchive(boolean isarchive) {
		this.isarchive = isarchive;
	}

	public boolean isIspinned() {
		return ispinned;
	}

	public void setIspinned(boolean ispinned) {
		this.ispinned = ispinned;
	}

	public boolean isIntrash() {
		return intrash;
	}

	public void setIntrash(boolean intrash) {
		this.intrash = intrash;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", title=" + title + ", discription=" + discription + ", creadtedTime="
				+ creadtedTime + ", updateTime=" + updateTime + ", isarchive=" + isarchive + ", ispinned=" + ispinned
				+ ", intrash=" + intrash + ", userId=" + userId + "]";
	}

}
