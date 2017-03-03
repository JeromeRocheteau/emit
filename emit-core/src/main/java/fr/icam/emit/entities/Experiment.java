package fr.icam.emit.entities;



public class Experiment {
	
	private long id;
	private long started;
	private long stopped;
	private String process;
	private String observee_uri;	
	
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
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getobservee_uri() {
		return observee_uri;
	}
	public void setobservee_uri(String observee_uri) {
		this.observee_uri = observee_uri;
	}
	
	public Experiment(long id, long started,long stopped,String process,String observee_uri){
		this.setId(id);
		this.setStarted(started);
		this.setStopped(stopped);
		this.setProcess(process);
		this.setobservee_uri(observee_uri);
	}
	

}
