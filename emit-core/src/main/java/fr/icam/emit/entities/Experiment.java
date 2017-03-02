package fr.icam.emit.entities;

public class Experiment {
	
	private int id;
	private long started;
	private long stopped;
	private String process;
	private String uri;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public Experiment(int id, long started,long stopped,String process,String uri){
		this.setId(id);
		this.setStarted(started);
		this.setStopped(stopped);
		this.setProcess(process);
		this.setUri(uri);
	}
	

}
