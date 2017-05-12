package fr.icam.emit.entities;

public class Analysis {

	private String url;	
	private String name;
	private Boolean deleted;
	
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Analysis(String url, String name, Boolean deleted){
		this.setUrl(url);
		this.setName(name);
		this.setDeleted(deleted);
	}
	

}