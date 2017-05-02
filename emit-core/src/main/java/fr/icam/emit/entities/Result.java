package fr.icam.emit.entities;



public class Result {

	private long id;
	private String analysis;
	private Long measurement;
	private String measure;
	private double value;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public Long getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Long measurement) {
		this.measurement = measurement;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Result(long id, String analysis, long measurement, String measure, Double value){
		this.setId(id);
		this.setAnalysis(analysis);
		this.setMeasurement(measurement);
		this.setMeasure(measure);
		this.setValue(value);
	}
	

}
