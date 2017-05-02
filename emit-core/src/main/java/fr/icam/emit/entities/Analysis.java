package fr.icam.emit.entities;



public class Analysis {

	private String path;	
	private String name;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Analysis(String path, String name){
		this.setPath(path);
		this.setName(name);
	}
	

}
