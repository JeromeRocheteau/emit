package fr.icam.emit.entities;

public class Client {

	private String uuid;

	private String broker;

	private String user;
	
	public String getUuid() {
		return uuid;
	}

	public String getBroker() {
		return broker;
	}

	public String getUser() {
		return user;
	}

	public Client(String uuid, String broker, String user) {
		super();
		this.uuid = uuid;
		this.broker = broker;
		this.user = user;
	}
	
}
