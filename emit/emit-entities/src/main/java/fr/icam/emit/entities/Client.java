package fr.icam.emit.entities;

public class Client {

	private String uuid;

	private String broker;

	public String getUuid() {
		return uuid;
	}

	public String getBroker() {
		return broker;
	}

	public Client(String uuid, String broker) {
		super();
		this.uuid = uuid;
		this.broker = broker;
	}
	
}
