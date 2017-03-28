package fr.icam.emit.entities;

public class Instrument {

	private String uri;
	private String name;
	private Boolean deleted;

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Instrument(String uri, String name, Boolean deleted) {
		this.setUri(uri);
		this.setName(name);
		this.setDeleted(deleted);
	}
}
