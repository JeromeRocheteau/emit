package fr.icam.emit.entities;

public class Experiment_plan {

	private Experiment experiment;
	private MeasurementSet measurementSet;
	private Environment environment;
	
	public Experiment getExperiment() {
		return experiment;
	}
	public void setExperiment(Experiment experiment) {
		this.experiment = experiment;
	}
	public MeasurementSet getMeasurementSet() {
		return measurementSet;
	}
	public void setMeasurementSet(MeasurementSet measurementSet) {
		this.measurementSet = measurementSet;
	}
	public Environment getEnvironment() {
		return environment;
	}
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	
	public Experiment_plan(Experiment experiment,MeasurementSet measurementSet,Environment environment){
		this.setExperiment(experiment);
		this.setMeasurementSet(measurementSet);
		this.setEnvironment(environment);
	}
}
