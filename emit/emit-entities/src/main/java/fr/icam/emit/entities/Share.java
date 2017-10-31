package fr.icam.emit.entities;

public class Share extends Client {

	private Boolean control;
	
	public Boolean getContol() {
		return control;
	}
	
	public Share(String uuid, String broker, String user, Boolean open, Boolean control) {
		super(uuid, broker, user, open);
		this.control = control;
	}

}
