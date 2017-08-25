package fr.icam.emit.entities;

import java.util.List;

public class Instrument extends Item {

	private String uri;
	
	private String name;
	
	private List<Feature> features;

	public String getUri() {
		return uri;
	}

	public String getName() {
		return name;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public Instrument(Long id, String uri, String name, Long deleted) {
		super(id, deleted);
		this.uri = uri;
		this.name = name;
	}

}
