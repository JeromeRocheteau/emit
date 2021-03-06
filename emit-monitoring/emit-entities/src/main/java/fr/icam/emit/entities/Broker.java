package fr.icam.emit.entities;

public class Broker {

	private String uri;
	
	private String name;
	
	private String user;
	
	private String username;
	
	private String password;

	public String getUri() {
		return uri;
	}

	public String getName() {
		return name;
	}

	public String getUser() {
		return user;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Broker(String uri, String name, String user, String username, String password) {
		super();
		this.uri = uri;
		this.name = name;
		this.user = user;
		this.username = username;
		this.password = password;
	} 
	
}
