package fr.icam.emit.entities;

public class MeasurementSet {
	
	private int id;
	private String data;
	private long achieved;
	private String uri;
	private int experimentId;
	private int measurementId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public void setAchieved(long datetime) {
		this.achieved = datetime;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public int getExperimentId() {
		return experimentId;
	}
	public void setExperimentId(int experimentId) {
		this.experimentId = experimentId;
	}
	public int getMeasurementId() {
		return measurementId;
	}
	public void setMeasurementId(int measurementId) {
		this.measurementId = measurementId;
	}
	
	public  MeasurementSet(int id,String data,Long achieved,String uri,int ExperimentId,int MeasurementId){
		this.setId(id);
		this.setData(data);
		this.setAchieved(achieved);
		this.setUri(uri);
		this.setExperimentId(ExperimentId);
		this.setMeasurementId(MeasurementId);
	}
	
}
