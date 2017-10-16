package fr.icam.emit.entities;

public class Connect {

	private Long id;
	
	private Long started;
	
	private Long stopped;
	
	private Client client;

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

	public Connect(Long id, Long started, Long stopped, Client client) {
		super();
		this.id = id;
		this.started = started;
		this.stopped = stopped;
		this.client = client;
	}
	
}
