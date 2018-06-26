package org.tio.showcase.websocket.server;

public class Mine {
	private String avatar;
	private String id;
	private String content;
	private String username;

	public Mine(String avatar, String id, String content, String username) {
		super();
		this.avatar = avatar;
		this.id = id;
		this.content = content;
		this.username = username;
	}

	public Mine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
