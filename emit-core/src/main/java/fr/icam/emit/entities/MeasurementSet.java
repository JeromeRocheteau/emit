package fr.icam.emit.entities;

//teste commit

public class MeasurementSet {
	
	private long id;
	private String data;
	private long achieved;
	private String uri;
	private long experimentId;

	
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
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public long getExperimentId() {
		return experimentId;
	}
	public void setExperimentId(long experimentId) {
		this.experimentId = experimentId;
	}
	
	public  MeasurementSet(long id,String data,long achieved,String uri,long ExperimentId){
	//public  MeasurementSet(long id,String data,String uri,long ExperimentId){
		this.setId(id);
		this.setData(data);
		this.setAchieved(achieved);
		this.setUri(uri);
		this.setExperimentId(ExperimentId);
		
	}

}
