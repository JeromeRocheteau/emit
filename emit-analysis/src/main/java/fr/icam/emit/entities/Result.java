package fr.icam.emit.entities;

public class Result {
	
	private Long id;
	private String analysis;
	private String context;
	private String measure;
	private double result;
	private String condition;
	private String features;
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
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}		
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}	
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
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
		this.setResult(result);		
	}
	
	public Result(Long id,String analysis,String context,String measure,double result,String condition,String measurand,String environment,String feature){
		this.setId(id);
		this.setAnalysis(analysis);
		this.setContext(context);
		this.setMeasure(measure);
		this.setResult(result);
		this.setMeasurand(measurand);
		this.setFeatures(feature);
		this.setEnvironment(environment);		
	}

}
