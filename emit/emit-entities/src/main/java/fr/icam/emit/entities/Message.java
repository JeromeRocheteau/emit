package fr.icam.emit.entities;

public class Message {
	
	private String id;
	
	private Long arrived;

	private String topic;
	
	private int qos;
	
	private boolean retained;
	
	private boolean duplicated;
	
	private byte[] payload;

	public String getId() {
		return id;
	}

	public Long getArrived() {
		return arrived;
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

	public boolean isDuplicated() {
		return duplicated;
	}

	public byte[] getPayload() {
		return payload;
	}

	public Message(String id, Long arrived, String topic, int qos, boolean retained, boolean duplicated, byte[] payload) {
		super();
		this.id = id;
		this.arrived = arrived;
		this.topic = topic;
		this.qos = qos;
		this.retained = retained;
		this.duplicated = duplicated;
		this.payload = payload;
	}
	
}
