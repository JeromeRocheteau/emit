package fr.icam.emit.entities;

public class Instrument extends Item {

	private String uri;
	
	private String name;

	public String getUri() {
		return uri;
	}

	public String getName() {
		return name;
	}

	public Instrument(Long id, String uri, String name, Long deleted) {
		super(id, deleted);
		this.uri = uri;
		this.name = name;
	}

}
