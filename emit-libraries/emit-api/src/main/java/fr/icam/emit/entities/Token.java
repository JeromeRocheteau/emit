package fr.icam.emit.entities;

public class Token {

	private String uuid;
	
	private String user;
	
	public String getUuid() {
		return uuid;
	}

	public String getUser() {
		return user;
	}

	public Token(String uuid, String user) {
		this.uuid = uuid;
		this.user = user;
	}
	
}
