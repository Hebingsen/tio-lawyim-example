package org.tio.showcase.websocket.server;

public class To {

	private String avatar;
	private String id;
	private String name;
	private String sign;
	private String status;
	private String type;
	private String username;

	public To() {
		super();
		// TODO Auto-generated constructor stub
	}

	public To(String avatar, String id, String name, String sign, String status, String type, String username) {
		super();
		this.avatar = avatar;
		this.id = id;
		this.name = name;
		this.sign = sign;
		this.status = status;
		this.type = type;
		this.username = username;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
