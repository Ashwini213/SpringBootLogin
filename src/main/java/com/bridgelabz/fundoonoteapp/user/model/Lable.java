package com.bridgelabz.fundoonoteapp.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lable")
public class Lable {
	@Id

	private int lableId;
	private String lableName;

	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLableId() {
		return lableId;
	}

	public void setLableId(int lableId) {
		this.lableId = lableId;
	}

	public String getLableName() {
		return lableName;
	}

	public void setLableName(String lableName) {
		this.lableName = lableName;
	}

	@Override
	public String toString() {
		return "Lable [userId=" + userId + ", lableId=" + lableId + ", lableName=" + lableName + "]";
	}

}
