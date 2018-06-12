package fr.icam.emit.entities;

public class Measurand extends Item {

	private String process;
	
	public String getProcess() {
		return process;
	}

	public Measurand(Long id, Long deleted, String process) {
		super(id, deleted);
		this.process = process;
	}

}
