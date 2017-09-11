package fr.icam.emit.entities;

public class Token extends Item {

	private String uuid;
	
	private String username;
	
	private Feature feature;

	public String getUuid() {
		return uuid;
	}

	public String getUsername() {
		return username;
	}

	public Feature getFeature() {
		return feature;
	}

	public Token(Long id, Long deleted, String uuid, String username, Feature feature) {
		super(id, deleted);
		this.uuid = uuid;
		this.username = username;
		this.feature = feature;
	}
	
}
