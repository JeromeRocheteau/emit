package fr.icam.emit.entities;

public class Measurement {

	private long id;
	
	private long measurementSetId;

	private String data;
	
	private String measure_name;

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

	public String getmeasure_name() {
		return measure_name;
	}

	public void setmeasure_name(String measure_name) {
		this.measure_name = measure_name;
	}
	
	public long getMeasurementSetId() {
		return measurementSetId;
	}

	public void setMeasurementSetId(long measurementSetId) {
		this.measurementSetId = measurementSetId;
	}
	
	public Measurement(long id,long measurementSetId, String data, String measure_name) {
		this.setId(id);
		this.setData(data);
		this.setmeasure_name(measure_name);
		this.setMeasurementSetId(measurementSetId);
	}
	
}
