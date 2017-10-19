package fr.icam.emit.entities;

public class Action {

	private Long id;
	
	private Long started;
	
	private Long stopped;
	
	private String client;
	
	private String user;
	
	private String type;

	public Long getId() {
		return id;
	}

	public Long getStarted() {
		return started;
	}

	public Long getStopped() {
		return stopped;
	}

	public String getClient() {
		return client;
	}

	public String getUser() {
		return user;
	}

	public String getType() {
		return type;
	}

	public Action(Long id, Long started, Long stopped, String user, String client, String type) {
		super();
		this.id = id;
		this.started = started;
		this.stopped = stopped;
		this.user = user;
		this.client = client;
		this.type = type;
	}
	
}
