package fr.icam.emit.api;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Meter extends Instrument {

	public Meter(URI uri) {
		super(uri);
	}

	public void doStart() throws Exception {
		this.doRun(true);
	}

	public void doStop() throws Exception {
		this.doRun(false);
	}
	
	private void doRun(boolean start) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			HttpGet request = new HttpGet(this.uri.toString() + (start ? "start" : "stop"));
			CloseableHttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			try {
				InputStream stream = entity.getContent();
				IOUtils.copy(stream, System.out);
				EntityUtils.consume(entity);	
			} finally {
				response.close();
			}
		} finally {
			client.close();
		}
	}
	
	public void doRetrieve(OutputStream out) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			HttpGet request = new HttpGet(this.uri.toString() + "data");
			CloseableHttpResponse response = client.execute(request);
			try {
				InputStream in = response.getEntity().getContent();
				IOUtils.copy(in, out);
			} finally {
				response.close();
			}
		} finally {
			client.close();
		}
	}

	public List<Feature> getFeatures() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			HttpGet request = new HttpGet(this.uri.toString() + "meta");
			CloseableHttpResponse response = client.execute(request);
			try {
				return this.getList(Feature.class, response);
			} finally {
				response.close();
			}
		} finally {
			client.close();
		}
	}
	
}
