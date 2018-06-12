package fr.icam.emit.entities;

public class Type {

	private String name;
	
	private String category;
	
	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public Type(String name, String category) {
		super();
		this.name = name;
		this.category = category;
	}
	
}
