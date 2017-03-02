package fr.icam.emit.entities;

public class Measure {
	
	private String name;
	
	private String unit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Measure(String name, String unit) {
		this.setName(name);
		this.setUnit(unit);
	}
}
