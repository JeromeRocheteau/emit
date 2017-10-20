package fr.icam.emit.entities;

public class Message {
	
	private Long issued;

	private String type;
	
	private String topic;
	
	private int qos;
	
	private boolean retained;
	
	private byte[] payload;

	public Long getIssued() {
		return issued;
	}

	public String getType() {
		return type;
	}

	public String getTopic() {
		return topic;
	}

	public int getQos() {
		return qos;
	}

	public boolean isRetained() {
		return retained;
	}

	public byte[] getPayload() {
		return payload;
	}

	public Message(Long issued, String type, String topic, int qos, boolean retained, byte[] payload) {
		super();
		this.issued = issued;
		this.topic = topic;
		this.type = type;
		this.qos = qos;
		this.retained = retained;
		this.payload = payload;
	}
	
}
