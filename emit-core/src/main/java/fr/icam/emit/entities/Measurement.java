package fr.icam.emit.entities;

public class Measurement extends Item {

	private String identifier;
	
	private Feature feature;

	private MeasurementSet measurementSet;

	public String getIdentifier() {
		return identifier;
	}

	public Feature getFeature() {
		return feature;
	}

	public MeasurementSet getMeasurementSet() {
		return measurementSet;
	}

	public Measurement(Long id, String identifier, Feature feature, MeasurementSet measurementSet, Long deleted) {
		super(id, deleted);
		this.identifier = identifier;
		this.feature = feature;
		this.measurementSet = measurementSet;
	}

}
