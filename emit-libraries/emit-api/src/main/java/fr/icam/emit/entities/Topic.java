package fr.icam.emit.entities;

public class Topic {

	private String name;
	
	private String prefix;
	
	private String suffix;
	
	private Boolean leaf;

	public String getName() {
		return name;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public Topic(String name, String prefix, String suffix, Boolean leaf) {
		super();
		this.name = name;
		this.prefix = prefix;
		this.suffix = suffix;
		this.leaf = leaf;
	}
	
}
