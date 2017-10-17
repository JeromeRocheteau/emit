package fr.icam.emit.entities;

public class Connect {

	private Long id;
	
	private Long started;
	
	private Long stopped;
	
	private Client client;
	
	private String user;

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

	public Connect(Long id, Long started, Long stopped, String user, Client client) {
		super();
		this.id = id;
		this.started = started;
		this.stopped = stopped;
		this.user = user;
		this.client = client;
	}
	
}
