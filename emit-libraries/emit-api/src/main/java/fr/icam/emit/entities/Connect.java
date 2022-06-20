package fr.icam.emit.entities;

public class Connect {

	private Long id;
	
	private Long started;
	
	private Long stopped;
	
	private Client client;
	
	private String user;
	
	private String username;
	
	private String password;

	public Long getId() {
		return id;
	}

	public Long getStarted() {
		return started;
	}

	public Long getStopped() {
		return stopped;
	}

	public Client getClient() {
		return client;
	}

	public String getUser() {
		return user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Connect(Long id, Long started, Long stopped, String user, String username, String password, Client client) {
		super();
		this.id = id;
		this.started = started;
		this.stopped = stopped;
		this.user = user;
		this.username = username;
		this.password = password;
		this.client = client;
	}
	
}
