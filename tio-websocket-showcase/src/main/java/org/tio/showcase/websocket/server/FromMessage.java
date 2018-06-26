package org.tio.showcase.websocket.server;

public class FromMessage {

	private Mine mine;
	private To to;

	public FromMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FromMessage(Mine mine, To to) {
		super();
		this.mine = mine;
		this.to = to;
	}

	public Mine getMine() {
		return mine;
	}

	public void setMine(Mine mine) {
		this.mine = mine;
	}

	public To getTo() {
		return to;
	}

	public void setTo(To to) {
		this.to = to;
	}

}
