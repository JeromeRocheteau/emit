package fr.icam.emit.clients;

import com.google.gson.Gson;

public abstract class Client {
	
	protected Gson gson;
	
	protected String url;

	public Client(String url) {
		this.gson = new Gson();
		this.url = url.endsWith("/") ? url : url + "/";
	}
	
	protected abstract String getPath();

}
