package fr.icam.emit.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class Launcher extends Instrument {

	public Launcher(URI uri) {
		super(uri);
	}

	public Environment getEnvironment() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			HttpGet request = new HttpGet(this.uri.toString() + "info");
			CloseableHttpResponse response = client.execute(request);
			try {
				return this.getItem(Environment.class, response);
			} finally {
				response.close();
			}
		} finally {
			client.close();
		}
	}

	public Boolean canLaunch(String command) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			HttpPost request = new HttpPost(this.uri.toString() + "check");
			List<NameValuePair> parameters = new ArrayList<NameValuePair>(1);
		    parameters.add(new BasicNameValuePair("command", command));
		    request.setEntity(new UrlEncodedFormEntity(parameters));
			CloseableHttpResponse response = client.execute(request);
			try {
				return this.getItem(Boolean.class, response);
			} finally {
				response.close();
			}
		} finally {
			client.close();
		}
	}

	public Long doLaunch(Long timeout, String command, String... arguments) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			HttpPost request = new HttpPost(this.uri.toString() + "launch");
			List<NameValuePair> parameters = new ArrayList<NameValuePair>(arguments.length + 2);
		    parameters.add(new BasicNameValuePair("timeout", timeout.toString()));
		    parameters.add(new BasicNameValuePair("command", command));
		    for (String argument : arguments) {
			    parameters.add(new BasicNameValuePair("arguments", argument));		    	
		    }
		    request.setEntity(new UrlEncodedFormEntity(parameters));
			CloseableHttpResponse response = client.execute(request);
			try {
				return this.getItem(Long.class, response);
			} finally {
				response.close();
			}
		} finally {
			client.close();
		}
	}
	
}
