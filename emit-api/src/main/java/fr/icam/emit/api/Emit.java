package fr.icam.emit.api;

public class Emit {
	
	public static final Measure TIME = measure("time","second");
	public static final Measure POWER = measure("power","watt");
	public static final Measure INTENSITY = measure("intensity","watt");
	public static final Measure FACTOR = measure("factor","");
	public static final Measure VOLTAGE = measure("voltage","volt");
	public static final Measure CPU = measure("cpu","%");
	public static final Measure RAM = measure("ram","octet");

	public static final Measure[] MEASURES = new Measure[]{Emit.TIME, Emit.POWER, Emit.VOLTAGE, Emit.INTENSITY, Emit.FACTOR, Emit.CPU, Emit.RAM};
	
	private static Measure measure(String name, String unit) {
		Measure measure = new Measure();
		measure.setName(name);
		measure.setUnit(unit);
		return measure;
	}
	
	public static Feature feature(String name, float factor, Measure measure) {
		Feature feature = new Feature();
		feature.setName(name);
		feature.setFactor(factor);
		feature.setMeasure(measure);
		return feature;
	}
	
}
