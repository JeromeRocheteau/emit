package fr.icam.emit.clients;

import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import fr.icam.emit.entities.Measurement;

public class MeasurementFind extends Client {

	private static final String path = "/measurements/find";

	@Override
	protected String getPath() {
		return path;
	}

	public MeasurementFind(String url) {
		super(url);
	}

	public List<Measurement> doGet(String measure, Date started, Date stopped) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			String address = url + path + "?measure=" + measure + "&started=" + started.getTime() + "&stopped=" + stopped.getTime();
			HttpGet request = new HttpGet(address);
			CloseableHttpResponse response = client.execute(request);
			try {
				HttpEntity entity = response.getEntity();
				try {
					Type type = new TypeToken<LinkedList<Measurement>>(){}.getType();
					InputStreamReader reader = new InputStreamReader(entity.getContent());
					return gson.fromJson(reader, type);
				} catch (Exception e) {
					throw e;
				} finally {
					EntityUtils.consume(entity);
				}
			} finally {
				response.close();
			}
		} finally {
			client.close();	
		}
	}

}
