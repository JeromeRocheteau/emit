package fr.icam.emit.entities;



public class Experiment {
	//comment
	private long id;
	private Long started;
	private Long stopped;
	private Measurand measurand;
	private String environment;	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getStarted() {
		return started;
	}
	public void setStarted(Long started) {
		this.started = started;
	}
	public Long getStopped() {
		return stopped;
	}
	public void setStopped(Long stopped) {
		this.stopped = stopped;
	}
	public Measurand getMeasurand() {
		return measurand;
	}
	public void setMeasurand(Measurand measurand) {
		this.measurand = measurand;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment_uri) {
		this.environment = environment_uri;
	}
	
	public Experiment(long id, Long started,Long stopped,Measurand measurand,String environment_uri){
		this.setId(id);
		this.setStarted(started);
		this.setStopped(stopped);
		this.setMeasurand(measurand);
		this.setEnvironment(environment_uri);
	}
	

}
