package fr.icam.emit.entities;



public class Experiment {
	//comment
	private long id;
	private long started;
	private long stopped;
	private String measurand;
	private String environment;	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStarted() {
		return started;
	}
	public void setStarted(long started) {
		this.started = started;
	}
	public long getStopped() {
		return stopped;
	}
	public void setStopped(long stopped) {
		this.stopped = stopped;
	}
	public String getMeasurand() {
		return measurand;
	}
	public void setMeasurand(String measurand) {
		this.measurand = measurand;
	}
	public String getObservee() {
		return environment;
	}
	public void setObservee(String observee_uri) {
		this.environment = observee_uri;
	}
	
	public Experiment(long id, long started,long stopped,String measurand,String observee_uri){
		this.setId(id);
		this.setStarted(started);
		this.setStopped(stopped);
		this.setMeasurand(measurand);
		this.setObservee(observee_uri);
	}
	

}
