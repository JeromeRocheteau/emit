package fr.icam.emit.clients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import fr.icam.emit.entities.Action;
import fr.icam.emit.entities.Client;
import fr.icam.emit.entities.Message;

public class EmitClient {

	public enum Mode {MONITORING, REPORTING, FOLLOWING, MANAGING, HANDLING};
	
	private Gson mapper;
	
    private HttpHost host;
    
    private CloseableHttpClient client;
	
    private UsernamePasswordCredentials credentials;
    
	public EmitClient(String protocol, String hostname, int port, String username, String password) {
		mapper = new Gson();
        host = new HttpHost(hostname, port, protocol);
        client = HttpClients.createDefault();
        credentials = new UsernamePasswordCredentials(username, password);
	}

	public void setUp() throws Exception {
		this.doIndex();
		this.doUserAuth();
	}
	
	public void tearDown() throws Exception {
		this.doUserClear();
		client.close();
	}

	private void doIndex() throws Exception {
        HttpGet request = new HttpGet("/emit");
        HttpResponse response = client.execute(host, request);
        HttpEntity entity = response.getEntity();
        EntityUtils.consume(entity);
	}
	
	private int doUserAuth() throws Exception {
		 HttpPost request = new HttpPost("/emit/j_security_check");
         List<NameValuePair> parameters = new ArrayList<NameValuePair>();
         parameters.add(new BasicNameValuePair("j_username", credentials.getUserName()));
         parameters.add(new BasicNameValuePair("j_password", credentials.getPassword()));
         request.setEntity(new UrlEncodedFormEntity(parameters));
         return this.getStatus(request);
	}
	
	private int doUserClear() throws Exception {
        HttpGet request = new HttpGet("/emit/user/clear");
        return this.getStatus(request);
	}
	
	public String getUserName() throws Exception {
        HttpGet request = new HttpGet("/emit/user/name");
        return this.getString(request);
	}

	public Boolean isSuperUser() throws Exception {
        HttpGet request = new HttpGet("/emit/user/role");
        return this.getBoolean(request);
	}

	public Boolean doUserUpdate(String password) throws Exception {
		 HttpPost request = new HttpPost("/emit/user/update");
         List<NameValuePair> parameters = new ArrayList<NameValuePair>();
         parameters.add(new BasicNameValuePair("password", password));
         parameters.add(new BasicNameValuePair("confirmPassword", password));
         request.setEntity(new UrlEncodedFormEntity(parameters));
         return this.getBoolean(request);
	}
	
	public Integer getClientSize(Mode mode) throws Exception {
        HttpGet request = new HttpGet("/emit/clients/" + mode.toString().toLowerCase() + "/size");
        return this.getSize(request);
	}
	
	public List<Client> getClientPage(Mode mode, Integer offset, Integer length) throws Exception {
		String parameters = "?offset=" + offset.intValue() + "&length=" + length.intValue();
        HttpGet request = new HttpGet("/emit/clients/" + mode.toString().toLowerCase() + "/page" + parameters);
        return this.getList(Client[].class, request);
	}
	
	public Integer getActionSize(Client client) throws Exception {
		String parameters = "?client=" + client.getUuid();
        HttpGet request = new HttpGet("/emit/clients/actions/size" + parameters);
        return this.getSize(request);
	}
	
	public List<Action> getActionPage(Client client, Integer offset, Integer length) throws Exception {
		String parameters = "?client=" + client.getUuid() + "&offset=" + offset.intValue() + "&length=" + length.intValue();
        HttpGet request = new HttpGet("/emit/clients/actions/page" + parameters);
        return this.getList(Action[].class, request);
	}
	
	public Integer getMessageSize(Client client) throws Exception {
		String parameters = "?client=" + client.getUuid();
        HttpGet request = new HttpGet("/emit/clients/messages/size" + parameters);
        return this.getSize(request);
	}
	
	public List<Message> getMessagePage(Client client, Integer offset, Integer length) throws Exception {
		String parameters = "?client=" + client.getUuid() + "&offset=" + offset.intValue() + "&length=" + length.intValue();
        HttpGet request = new HttpGet("/emit/clients/messages/page" + parameters);
        return this.getList(Message[].class, request);
	}
	
	/*
      <url-pattern>/clients/connected</url-pattern>
      <url-pattern>/clients/connect</url-pattern>
      <url-pattern>/clients/disconnect</url-pattern>
      <url-pattern>/clients/subscribing</url-pattern>
      <url-pattern>/clients/subscribe</url-pattern>
      <url-pattern>/clients/unsubscribe</url-pattern>
      <url-pattern>/clients/publish</url-pattern>
      <url-pattern>/clients/callback</url-pattern>
      <url-pattern>/clients/shares/create</url-pattern>
      <url-pattern>/clients/shares/update</url-pattern>
      <url-pattern>/clients/shares/delete</url-pattern>
      <url-pattern>/clients/shares/remove</url-pattern>
      <url-pattern>/topics/roots/list</url-pattern>
      <url-pattern>/topics/leafs/list</url-pattern>
      <url-pattern>/topics/nodes/list</url-pattern>
      <url-pattern>/clients/callbacks/list</url-pattern>
      <url-pattern>/clients/callbacks/item</url-pattern>
      <url-pattern>/clients/callbacks/attach</url-pattern>
      <url-pattern>/clients/callbacks/detach</url-pattern>
      <url-pattern>/tokens/item</url-pattern>
      <url-pattern>/tokens/create</url-pattern>
      <url-pattern>/tokens/delete</url-pattern>
	 * */

	private Integer getSize(HttpGet request) throws IOException, ClientProtocolException, EmitClientException {
		HttpResponse response = client.execute(host, request);
        HttpEntity entity = response.getEntity();
        String message = EntityUtils.toString(entity);
        int status = response.getStatusLine().getStatusCode();
        EntityUtils.consume(entity);
        if (status == 200) {
        	return Integer.valueOf(message);
        } else {
        	throw new EmitClientException(status, message);
        }
	}

	private <T> T getObject(Class<T> type, HttpGet request) throws IOException, ClientProtocolException, EmitClientException {
		HttpResponse response = client.execute(host, request);
        HttpEntity entity = response.getEntity();
        String message = EntityUtils.toString(entity);
        int status = response.getStatusLine().getStatusCode();
        EntityUtils.consume(entity);
        if (status == 200) {
        	return mapper.fromJson(message, type);
        } else {
        	throw new EmitClientException(status, message);
        }
	}
	
	private <T> List<T> getList(Class<T[]> type, HttpGet request) throws IOException, ClientProtocolException, EmitClientException {
		HttpResponse response = client.execute(host, request);
        HttpEntity entity = response.getEntity();
        String message = EntityUtils.toString(entity);
        int status = response.getStatusLine().getStatusCode();
        EntityUtils.consume(entity);
        if (status == 200) {
        	return Arrays.asList(mapper.fromJson(message, type));
        } else {
        	throw new EmitClientException(status, message);
        }
	}
	
	private Boolean getBoolean(HttpRequestBase request) throws IOException, ClientProtocolException, EmitClientException {
		HttpResponse response = client.execute(host, request);
		HttpEntity entity = response.getEntity();
		String message = EntityUtils.toString(entity);
		int status = response.getStatusLine().getStatusCode();
		EntityUtils.consume(entity);
		if (status == 200) {
			return Boolean.valueOf(message);
		} else {
			throw new EmitClientException(status, message);
		}
	}

	private String getString(HttpRequestBase request) throws IOException, ClientProtocolException, EmitClientException {
		HttpResponse response = client.execute(host, request);
		HttpEntity entity = response.getEntity();
		String message = EntityUtils.toString(entity);
		int status = response.getStatusLine().getStatusCode();
		EntityUtils.consume(entity);
		if (status == 200) {
			return this.doClean(message);
		} else {
			throw new EmitClientException(status, message);
		}
	}
	
	private int getStatus(HttpRequestBase request) throws IOException, ClientProtocolException, EmitClientException {
		HttpResponse response = client.execute(host, request);
		HttpEntity entity = response.getEntity();
		int status = response.getStatusLine().getStatusCode();
		EntityUtils.consume(entity);
		return status;
	}
	
	private String doClean(String string) {
		String clean = string.trim();
		if (clean.startsWith("\"")) {
			if (clean.startsWith("\"")) {
				return clean.substring(1, clean.length() - 1);
			} else {
				return clean.substring(1, clean.length());
			}
		} else if (clean.startsWith("\"")) {
			return clean.substring(0, clean.length() - 1);
		} else {
			return clean;
		}
	}

}
