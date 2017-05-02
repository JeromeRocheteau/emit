package fr.icam.emit.entities;

public class MeasurementSet_plan {
	private long id;
	private String data;
	private Long achieved;
	private long experiment;
	private Instrument instrument;
	private Feature feature;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Long getAchieved() {
		return achieved;
	}
	public void setAchieved(Long achieved) {
		this.achieved = achieved;
	}
	public Long getExperiment() {
		return experiment;
	}
	public void setExperiment(long experiment) {
		this.experiment = experiment;
	}
	public Instrument getInstrument() {
		return instrument;
	}
	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	
	public MeasurementSet_plan(long id,String data,Long achieved,long experiment,Instrument instrument, Feature feature){
		this.setId(id);
		this.setAchieved(achieved);
		this.setData(data);
		this.setExperiment(experiment);
		this.setInstrument(instrument);
		this.setFeature(feature);		
		
	}
	
	
}
