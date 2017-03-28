package fr.icam.emit.entities;

public class Measure {
	
	private String name;
	private String unit;
	private Boolean deleted;

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

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
	
	public Measure(String name, String unit, Boolean deleted) {
		this.setName(name);
		this.setUnit(unit);
		this.setDeleted(deleted);
	}
}
