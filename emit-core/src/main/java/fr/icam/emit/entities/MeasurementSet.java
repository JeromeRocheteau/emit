package fr.icam.emit.entities;



//teste commit

public class MeasurementSet {
	
	private long id;
	private String data;
	private long achieved;
	private String instrument;
	private long experiment;

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
	public long getAchieved() {
		return achieved;
	}
	public void setAchieved(long longtime) {
		this.achieved = longtime;
	}
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	public long getExperiment() {
		return experiment;
	}
	public void setExperiment(long experimentId) {
		this.experiment = experimentId;
	}
	
	public  MeasurementSet(long id,String data,long achieved,String instrument,long ExperimentId){
		this.setId(id);
		this.setData(data);
		this.setAchieved(achieved);
		this.setInstrument(instrument);
		this.setExperiment(ExperimentId);
		
	}

}
