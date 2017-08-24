package fr.icam.emit.entities;

public class Account {

	private String username;
	
	private String rolename;
	
	private String password;
	
	private Long deleted;

	public String getUsername() {
		return username;
	}

	public String getRolename() {
		return rolename;
	}

	public String getPassword() {
		return password;
	}
	
	public Long getDeleted() {
		return deleted;
	}

	public Account(String username, String rolename, String password, Long deleted) {
		this.username = username;
		this.rolename = rolename;
		this.password = password;
		this.deleted = deleted;
	}
	
}
