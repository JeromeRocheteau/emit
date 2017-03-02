package fr.icam.emit.entities;

public class Observer {
	
	private String uri;
	
	private String name;
	
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
	
	public Observer(String uri, String name) {
		this.setUri(uri);
		this.setName(name);
	}
}
