package fr.icam.emit.entities;

import java.sql.Timestamp;

public class Experiment {
	//comment
	private long id;
	private Timestamp started;
	private Timestamp stopped;
	private String measurand;
	private String observee;	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Timestamp getStarted() {
		return started;
	}
	public void setStarted(Timestamp started) {
		this.started = started;
	}
	public Timestamp getStopped() {
		return stopped;
	}
	public void setStopped(Timestamp stopped) {
		this.stopped = stopped;
	}
	public String getMeasurand() {
		return measurand;
	}
	public void setMeasurand(String measurand) {
		this.measurand = measurand;
	}
	public String getObservee() {
		return observee;
	}
	public void setObservee(String observee_uri) {
		this.observee = observee_uri;
	}
	
	public Experiment(long id, Timestamp started,Timestamp stopped,String measurand,String observee_uri){
		this.setId(id);
		this.setStarted(started);
		this.setStopped(stopped);
		this.setMeasurand(measurand);
		this.setObservee(observee_uri);
	}
	

}
