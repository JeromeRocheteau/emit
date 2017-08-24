package fr.icam.emit.entities;

public class Environment extends Item {

	private String uri;
	
	private String name;
	
	private String arch;
	
	private String os;
	
	private String version;
	
	public String getUri() {
		return uri;
	}
	
	public String getName() {
		return name;
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

	public Environment(Long id, String uri, String name, String arch, String os, String version, Long deleted) {
		super(id, deleted);
		this.uri = uri;
		this.name = name;
		this.arch = arch;
		this.os = os;
		this.version = version;
	}

}
