package fr.icam.emit.entities;

public class Measurement extends Item {

	private Long started;
	
	private Long stopped;

	private String uuid;
	
	private Feature feature;

	public Long getStarted() {
		return started;
	}

	public Long getStopped() {
		return stopped;
	}

	public String getUuid() {
		return uuid;
	}

	public Feature getFeature() {
		return feature;
	}

	public Measurement(Long id, Long deleted, Long started, Long stopped, String uuid, Feature feature) {
		super(id, deleted);
		this.started = started;
		this.stopped = stopped;
		this.uuid = uuid;
		this.feature = feature;
	}

}
