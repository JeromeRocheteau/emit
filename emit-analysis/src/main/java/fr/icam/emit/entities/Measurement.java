package fr.icam.emit.entities;

public class Measurement {
	
private long id;
	
	private long measurementSet;

	private String data;
	
	private String measure;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}
	
	public long getMeasurementSet() {
		return measurementSet;
	}

	public void setMeasurementSet(long measurementSet) {
		this.measurementSet = measurementSet;
	}
	
	public Measurement(long id,long measurementSet, String data, String measure) {
		this.setId(id);
		this.setData(data);
		this.setMeasure(measure);
		this.setMeasurementSet(measurementSet);
	}
	

}
