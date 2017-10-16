package fr.icam.emit.entities;

public class Token {

	private String uuid;
	
	private String username;
	
	public String getUuid() {
		return uuid;
	}

	public String getUsername() {
		return username;
	}

	public Token(String uuid, String username) {
		this.uuid = uuid;
		this.username = username;
	}
	
}
