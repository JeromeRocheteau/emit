package fr.icam.emit.entities;

public class Measurand {

	private String procces;
	
	private String name;

	public String getProcces() {
		return procces;
	}

	public void setProcces(String procces) {
		this.procces = procces;
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
