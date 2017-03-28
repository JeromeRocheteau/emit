package fr.icam.emit.entities;

public class Measurand {

	private String process;
	private String name;
	private Boolean deleted;

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

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

	public Measurand(String process, String name, Boolean deleted) {
		this.setProcces(process);
		this.setName(name);
		this.setDeleted(deleted);
	}

}
