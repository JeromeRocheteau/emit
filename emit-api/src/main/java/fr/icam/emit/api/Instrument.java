package fr.icam.emit.api;

import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;

public abstract class Instrument {

	protected ObjectMapper mapper;

	protected URI uri;

	public Instrument(URI uri) {
		mapper = new ObjectMapper();
		this.uri = uri;
	}
	
	protected <T> T getItem(Class<T> type, CloseableHttpResponse response) throws Exception {
		HttpEntity entity = response.getEntity();
		InputStream stream = entity.getContent();
		try {
			return mapper.readValue(stream, type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			EntityUtils.consume(entity);
		}
	}

	protected <T> List<T> getList(Class<T> type, CloseableHttpResponse response) throws Exception {
		HttpEntity entity = response.getEntity();
		try {
			InputStream stream = entity.getContent();
			CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, type);
			return mapper.readValue(stream, collectionType);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		} finally {
			EntityUtils.consume(entity);
		}
	}
	
	protected <K,V> Map<K,V> getMap(MapType type, CloseableHttpResponse response) throws Exception {
		HttpEntity entity = response.getEntity();
		try {
			InputStream stream = entity.getContent();
			return mapper.readValue(stream, type);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyMap();
		} finally {
			EntityUtils.consume(entity);
		}
	}
	
	public Boolean isMeter() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			HttpGet request = new HttpGet(this.uri.toString() + "type");
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

}
