package fr.icam.emit.api;

public class Feature {

	private String name;

	private Float factor;
	
	private Measure measure;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getFactor() {
		return factor;
	}

	public void setFactor(Float factor) {
		this.factor = factor;
	}

	public Measure getMeasure() {
		return measure;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}
	
}
