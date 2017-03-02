package fr.icam.emit.entities;

public class Measurement {

	private String id;
	
	private String data;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Measurement(String id, String data, String name) {
		this.setId(id);
		this.setData(data);
		this.setName(name);
	}
	
}
