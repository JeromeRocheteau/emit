package fr.icam.emit.entities;

public class Measurand {

	private String process;
	
	private String name;

	public String getProcess() {
		return process;
	}

	public void setProcces(String process) {
		this.process = process;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Measurand(String process, String name) {
		this.setProcces(process);
		this.setName(name);
	}
	
}
