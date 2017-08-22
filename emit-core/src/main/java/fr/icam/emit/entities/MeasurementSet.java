package fr.icam.emit.entities;

public class MeasurementSet extends Item {
	
	private Long achieved;
	
	private String content;

	private Experiment experiment;

	public Long getAchieved() {
		return achieved;
	}

	public String getContent() {
		return content;
	}

	public Experiment getExperiment() {
		return experiment;
	}

	public MeasurementSet(Long id, Long deleted, Long achieved, String content, Experiment experiment) {
		super(id, deleted);
		this.achieved = achieved;
		this.content = content;
		this.experiment = experiment;
	}

}
