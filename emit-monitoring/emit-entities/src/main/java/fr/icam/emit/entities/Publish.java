package fr.icam.emit.entities;

public class Publish {

	private Long id;
	
	private Long issued;
	
	private String topic;
	
	private Client client;

	public Long getId() {
		return id;
	}

	public Long getIssued() {
		return issued;
	}

	public String getTopic() {
		return topic;
	}

	public Client getClient() {
		return client;
	}

	public Publish(Long id, Long issued, String topic, Client client) {
		super();
		this.id = id;
		this.issued = issued;
		this.topic = topic;
		this.client = client;
	}
	
}
