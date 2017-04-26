package fr.icam.emit.entities;

public class Feature {
	long id;
	String measure;
	String instrument;
	String unit;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Feature(long id,String measure,String instrument,String unit){
		this.setId(id);
		this.setInstrument(instrument);
		this.setMeasure(measure);
		this.setUnit(unit);
	}
	
}
