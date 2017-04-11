package fr.icam.emit.entities;

public class Serie {

	private double timestamp;
	private double value;
	
	public double getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(double timestamp) {
		this.timestamp = timestamp;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public Serie(double timestamp, double uri) {
		this.setTimestamp(timestamp);
		this.setValue(value);
	}
}
