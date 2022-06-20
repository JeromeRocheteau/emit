package fr.icam.emit.entities;

public class User {

	private String username;
	
	private String password;
	
	private String rolename;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRolename() {
		return rolename;
	}

	public User(String username, String password, String rolename) {
		super();
		this.username = username;
		this.password = password;
		this.rolename = rolename;
	}
	
}
