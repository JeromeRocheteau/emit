package fr.icam.emit.entities;

public class Subscribe {

	private Long id;
	
	private Long started;
	
	private Long stopped;
	
	private String topic;
	
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

	public String getTopic() {
		return topic;
	}

	public Client getClient() {
		return client;
	}

	public Subscribe(Long id, Long started, Long stopped, String topic, Client client) {
		super();
		this.id = id;
		this.started = started;
		this.stopped = stopped;
		this.topic = topic;
		this.client = client;
	}
	
}
