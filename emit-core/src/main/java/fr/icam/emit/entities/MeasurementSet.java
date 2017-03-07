package fr.icam.emit.entities;

import java.sql.Timestamp;

//teste commit

public class MeasurementSet {
	
	private long id;
	private String data;
	private Timestamp achieved;
	private String observer;
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
	public Timestamp getAchieved() {
		return achieved;
	}
	public void setAchieved(Timestamp longtime) {
		this.achieved = longtime;
	}
	public String getObserver() {
		return observer;
	}
	public void setObserver(String observer) {
		this.observer = observer;
	}
	public long getExperiment() {
		return experiment;
	}
	public void setExperiment(long experimentId) {
		this.experiment = experimentId;
	}
	
	public  MeasurementSet(long id,String data,Timestamp achieved,String observer,long ExperimentId){
	//public  MeasurementSet(long id,String data,String observer,long ExperimentId){
		this.setId(id);
		this.setData(data);
		this.setAchieved(achieved);
		this.setObserver(observer);
		this.setExperiment(ExperimentId);
		
	}

}
