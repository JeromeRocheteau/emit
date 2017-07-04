package fr.icam.emit.entities;

public class Result {
	
	private Long id;
	private String analysis;
	private String context;
	private String measure;
	private double value;
	private String condition;
	private int features;
	private String measurand;
	private String environment;
	
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double result) {
		this.value = result;
	}		
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}	
	public int getFeatures() {
		return features;
	}
	public void setFeatures(int features) {
		this.features = features;
	}
	public String getMeasurand() {
		return measurand;
	}
	public void setMeasurand(String measurand) {
		this.measurand = measurand;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public Result(Long id,String analysis,String context,String measure,double result,String condition){
		this.setId(id);
		this.setAnalysis(analysis);
		this.setContext(context);
		this.setMeasure(measure);
		this.setValue(result);		
	}
	
	public Result(Long id,String analysis,String context,String measure,double result,String condition,String measurand,String environment,int feature){
		this.setId(id);
		this.setAnalysis(analysis);
		this.setContext(context);
		this.setMeasure(measure);
		this.setValue(result);
		this.setMeasurand(measurand);
		this.setFeatures(feature);
		this.setEnvironment(environment);		
	}

}
