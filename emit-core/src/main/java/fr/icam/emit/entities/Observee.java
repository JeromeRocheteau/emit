package fr.icam.emit.entities;

public class Observee {
	
	private String name;
	private String uri;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public Observee(String name, String uri){
		this.setName(name);
		this.setUri(uri);
	}

}
