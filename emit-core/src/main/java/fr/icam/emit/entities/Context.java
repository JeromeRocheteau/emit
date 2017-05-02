package fr.icam.emit.entities;



public class Context {

	private long id;
	private String analysis;
	private Long measurement;
	
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
	
	public Context(long id, String analysis, long measurement){
		this.setId(id);
		this.setAnalysis(analysis);
		this.setMeasurement(measurement);
	}
	

}
