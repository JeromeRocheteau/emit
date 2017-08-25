package fr.icam.emit.entities;

public class Measurement extends Item {

	private Long achieved;

	private String uuid;
	
	private Feature feature;

	private Experiment experiment;

	public Long getAchieved() {
		return achieved;
	}

	public String getUuid() {
		return uuid;
	}

	public Feature getFeature() {
		return feature;
	}

	public Experiment getExperiment() {
		return experiment;
	}

	public Measurement(Long id, Long deleted, Long achieved, String uuid, Feature feature, Experiment experiment) {
		super(id, deleted);
		this.achieved = achieved;
		this.uuid = uuid;
		this.feature = feature;
		this.experiment = experiment;
	}

}
