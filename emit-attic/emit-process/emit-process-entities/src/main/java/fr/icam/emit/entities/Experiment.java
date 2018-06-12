package fr.icam.emit.entities;

public class Experiment extends Item {

	private Long started;

	private Long stopped;
	
	private Measurand measurand;
	
	private Environment environment;
	
	private Experiment experiment;

	public Long getStarted() {
		return started;
	}

	public Long getStopped() {
		return stopped;
	}

	public Measurand getMeasurand() {
		return measurand;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public Experiment getExperiment() {
		return experiment;
	}

	public void setExperiment(Experiment experiment) {
		this.experiment = experiment;
	}

	public Experiment(Long id, Long deleted, Long started, Long stopped, Measurand measurand, Environment environment) {
		super(id, deleted);
		this.started = started;
		this.stopped = stopped;
		this.measurand = measurand;
		this.environment = environment;
	}

}
