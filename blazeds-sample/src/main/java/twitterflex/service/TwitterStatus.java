package twitterflex.service;

import java.io.Serializable;
import java.util.Date;

public class TwitterStatus implements Serializable {

	private Date createdAt;
	private String name;
	private String status;

	public TwitterStatus() {
	}

	public TwitterStatus(Date createdAt, String name, String status) {
		this.createdAt = createdAt;
		this.name = name;
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

}
