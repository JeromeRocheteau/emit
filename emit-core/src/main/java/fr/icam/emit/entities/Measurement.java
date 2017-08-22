package fr.icam.emit.entities;

public class Measurement extends Item {

	private Long achieved;

	private String content;
	
	private Feature feature;

	private MeasurementSet measurementSet;

	public Long getAchieved() {
		return achieved;
	}

	public String getContent() {
		return content;
	}

	public Feature getFeature() {
		return feature;
	}

	public MeasurementSet getMeasurementSet() {
		return measurementSet;
	}

	public Measurement(Long id, Long deleted, Long achieved, String content, Feature feature, MeasurementSet measurementSet) {
		super(id, deleted);
		this.achieved = achieved;
		this.content = content;
		this.feature = feature;
		this.measurementSet = measurementSet;
	}

}
