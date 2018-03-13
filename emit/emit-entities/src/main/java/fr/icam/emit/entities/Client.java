package fr.icam.emit.entities;

public class Client {

	private String uuid;

	private String name;
	
	private String broker;

	private String user;
	
	private Boolean open;
	
	public String getUuid() {
		return uuid;
	}
	
	public String getName() {
		return name;
	}

	public String getBroker() {
		return broker;
	}

	public String getUser() {
		return user;
	}

	public Boolean getOpen() {
		return open;
	}

	public Client(String uuid, String name, String broker, String user, Boolean open) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.broker = broker;
		this.user = user;
		this.open = open;
	}
	
}
