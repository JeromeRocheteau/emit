package fr.icam.emit.entities;

public class Feature {
	long id;
	String measure;
	String instrument;
	long order;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	public long getOrder() {
		return order;
	}
	public void setOrder(long order) {
		this.order = order;
	}
	
	public Feature(long id,String measure,String instrument,long order){
		this.setId(id);
		this.setInstrument(instrument);
		this.setMeasure(measure);
		this.setOrder(order);
	}
	
}
