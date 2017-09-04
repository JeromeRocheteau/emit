package fr.icam.emit.entities;

public class Environment extends Item {

	private String uri;
	
	private String arch;
	
	private String os;
	
	private String version;
	
	public String getUri() {
		return uri;
	}
	
	public String getArch() {
		return arch;
	}

	public String getOs() {
		return os;
	}

	public String getVersion() {
		return version;
	}

	public Environment(Long id, Long deleted, String uri, String arch, String os, String version) {
		super(id, deleted);
		this.uri = uri;
		this.arch = arch;
		this.os = os;
		this.version = version;
	}

}
