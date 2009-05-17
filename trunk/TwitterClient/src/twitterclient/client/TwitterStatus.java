package twitterclient.client;

import java.io.Serializable;

public class TwitterStatus implements Serializable {

	private String name;
	private String status;

	public TwitterStatus() {
	}

	public TwitterStatus(String name, String status) {
		this.name = name;
		this.status = status;
	}

	public String getName() {
		return this.name;
	}

	public String getStatus() {
		return this.status;
	}

}
