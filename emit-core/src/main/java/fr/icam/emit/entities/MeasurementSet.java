package fr.icam.emit.entities;

public class MeasurementSet extends Item {
	
	private String path;

	private Experiment experiment;

	public String getPath() {
		return path;
	}

	public Experiment getExperiment() {
		return experiment;
	}

	public MeasurementSet(Long id, String path, Experiment experiment, Long deleted) {
		super(id, deleted);
		this.path = path;
		this.experiment = experiment;
	}

}
