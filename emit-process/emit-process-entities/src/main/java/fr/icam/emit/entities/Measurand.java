package fr.icam.emit.entities;

public class Measurand extends Item {

	private String name;
	
	public String getName() {
		return name;
	}

	public Measurand(Long id, String name, Long deleted) {
		super(id, deleted);
		this.name = name;
	}

}
