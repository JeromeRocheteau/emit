package fr.icam.emit.entities;

public class Measure extends Item {
	
	private String name;
	
	private String unit;

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}

	public Measure(Long id, Long deleted, String name, String unit) {
		super(id, deleted);
		this.name = name;
		this.unit = unit;
	}

}
