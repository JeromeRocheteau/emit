package fr.icam.emit.entities;

public class Feature extends Item {

	private String name;
	
	private Integer factor;

	private Measure measure;
	
	private Instrument instrument;

	public String getName() {
		return name;
	}

	public Integer getFactor() {
		return factor;
	}

	public Measure getMeasure() {
		return measure;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public Feature(Long id, String name, Integer factor, Measure measure, Instrument instrument, Long deleted) {
		super(id, deleted);
		this.name = name;
		this.factor = factor;
		this.measure = measure;
		this.instrument = instrument;
	}
	
}
