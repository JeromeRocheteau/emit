package fr.icam.emit.entities;

public class Account {

	private String username;
	
	private String password;
	
	private Long deleted;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public Long getDeleted() {
		return deleted;
	}

	public Account(String username, String password, Long deleted) {
		this.username = username;
		this.password = password;
		this.deleted = deleted;
	}
	
}
