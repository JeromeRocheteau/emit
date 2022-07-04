package fr.icam.emit.clients;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import fr.icam.emit.entities.Broker;
import fr.icam.emit.entities.Callback;
import fr.icam.emit.entities.Client;
import fr.icam.emit.entities.Connect;
import fr.icam.emit.entities.Message;
import fr.icam.emit.entities.Subscribe;

public class EmitClient {

	public enum Mode {MONITORING, REPORTING, FOLLOWING, MANAGING, HANDLING};
	
	public enum Type {STRING, BOOLEAN, INTEGER, LONG, FLOAT, DOUBLE, DATE, UUID, URI};
	
	public enum Symbol {EQ, NEQ, LT, LEQ, GT, GEQ};
	
	private enum Category {TYPE, VALUE, TOPIC, STORAGE, FEATURE, GUARD};
	
	public enum Collection {MESSAGES, FAILURES};
	
	private Gson mapper;
	
    private HttpHost host;
    
    private CloseableHttpClient client;
	
    private UsernamePasswordCredentials credentials;
    
    private Type getType(Class<?> clazz) throws Exception {
    	if (URI.class.isAssignableFrom(clazz)) {
    		return Type.URI;
    	} else if (UUID.class.isAssignableFrom(clazz)) {
    		return Type.UUID;
    	} else if (Date.class.isAssignableFrom(clazz)) {
    		return Type.DATE;
    	} else if (String.class.isAssignableFrom(clazz)) {
    		return Type.STRING;
    	} else if (Boolean.class.isAssignableFrom(clazz)) {
    		return Type.BOOLEAN;
    	} else if (Integer.class.isAssignableFrom(clazz)) {
    		return Type.INTEGER;
    	} else if (Long.class.isAssignableFrom(clazz)) {
    		return Type.LONG;
    	} else if (Float.class.isAssignableFrom(clazz)) {
    		return Type.FLOAT;
    	} else if (Double.class.isAssignableFrom(clazz)) {
    		return Type.DOUBLE;
    	} else {
    		throw new Exception("unknown type from class '" + clazz.getCanonicalName() + "'");
    	}
    }
    
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
         List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
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
         List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
         parameters.add(new BasicNameValuePair("password", password));
         parameters.add(new BasicNameValuePair("confirmPassword", password));
         request.setEntity(new UrlEncodedFormEntity(parameters));
         return this.getBoolean(request);
	}
	
	/* brokers */
	
	public Integer getBrokerSize() throws Exception {
		URIBuilder builder = new URIBuilder("/emit/brokers/size");
        HttpGet request = new HttpGet(builder.build());
        return this.getSize(request);
	}
	
	public List<Broker> getBrokerPage(Integer offset, Integer length) throws Exception {
		URIBuilder builder = new URIBuilder("/emit/brokers/page");
		builder.addParameter("offset", offset.toString());
		builder.addParameter("length", length.toString());
        HttpGet request = new HttpGet(builder.build());
        return this.getList(Broker[].class, request);
	}
	
	public List<Broker> getBrokerList() throws Exception {
		URIBuilder builder = new URIBuilder("/emit/brokers/list");
        HttpGet request = new HttpGet(builder.build());
        return this.getList(Broker[].class, request);
	}
	
	public Broker getBroker(URI uri) throws Exception {
		URIBuilder builder = new URIBuilder("/emit/brokers/find");
		builder.addParameter("uri", uri.toString());
        HttpGet request = new HttpGet(builder.build());
        return this.getObject(Broker.class, request);
	}
	
	public Integer doBrokerCreate(String name, URI uri) throws Exception {
        HttpPost request = new HttpPost("/emit/brokers/create");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("uri", uri.toString()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Integer doBrokerCreate(String name, URI uri, String username, String password) throws Exception {
        HttpPost request = new HttpPost("/emit/brokers/create");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(4);
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("uri", uri.toString()));
        parameters.add(new BasicNameValuePair("username", username));
        parameters.add(new BasicNameValuePair("password", password));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Integer doBrokerUpdate(String name, URI uri) throws Exception {
        HttpPost request = new HttpPost("/emit/brokers/update");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("uri", uri.toString()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Integer doBrokerUpdate(String name, URI uri, String username, String password) throws Exception {
        HttpPost request = new HttpPost("/emit/brokers/update");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(4);
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("uri", uri.toString()));
        parameters.add(new BasicNameValuePair("username", username));
        parameters.add(new BasicNameValuePair("password", password));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Integer doBrokerDelete(URI uri) throws Exception {
        HttpPost request = new HttpPost("/emit/brokers/delete");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(1);
        parameters.add(new BasicNameValuePair("uri", uri.toString()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	/* end of brokers */
	
	/* clients */
	
	public Integer getClientSize() throws Exception {
		URIBuilder builder = new URIBuilder("/emit/clients/size");
        HttpGet request = new HttpGet(builder.build());
        return this.getSize(request);
	}
	
	public List<Client> getClientPage(Integer offset, Integer length) throws Exception {
		URIBuilder builder = new URIBuilder("/emit/clients/page");
		builder.addParameter("offset", offset.toString());
		builder.addParameter("length", length.toString());
        HttpGet request = new HttpGet(builder.build());
        return this.getList(Client[].class, request);
	}
	
	public Client getClient(UUID uuid) throws Exception {
		URIBuilder builder = new URIBuilder("/emit/clients/find");
		builder.addParameter("client", uuid.toString());
        HttpGet request = new HttpGet(builder.build());
        return this.getObject(Client.class, request);
	}
	
	public UUID doClientCreate(String name, Broker broker, Boolean visibility) throws Exception {
        HttpPost request = new HttpPost("/emit/clients/create");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(3);
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("broker", broker.getUri()));
        parameters.add(new BasicNameValuePair("open", visibility.toString()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getUUID(request);
	}
	
	public UUID doClientCreate(UUID uuid, String name, Broker broker, Boolean visibility) throws Exception {
        HttpPost request = new HttpPost("/emit/clients/create");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(4);
        parameters.add(new BasicNameValuePair("uuid", uuid.toString()));
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("broker", broker.getUri()));
        parameters.add(new BasicNameValuePair("open", visibility.toString()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getUUID(request);
	}
	
	public Integer doClientUpdate(UUID uuid, String name, Broker broker, Boolean visibility) throws Exception {
        HttpPost request = new HttpPost("/emit/clients/update");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(4);
        parameters.add(new BasicNameValuePair("uuid", uuid.toString()));
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("broker", broker.getUri()));
        parameters.add(new BasicNameValuePair("open", visibility.toString()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Integer doClientDelete(UUID uuid) throws Exception {
        HttpPost request = new HttpPost("/emit/clients/delete");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(1);
        parameters.add(new BasicNameValuePair("uuid", uuid.toString()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	/* end of clients */
	
	/* callbacks */

	public Integer getCallbackSize() throws Exception {
		URIBuilder builder = new URIBuilder("/emit/callbacks/size");
        HttpGet request = new HttpGet(builder.build());
        return this.getSize(request);
	}
	
	public List<Callback> getCallbackList() throws Exception {
		URIBuilder builder = new URIBuilder("/emit/callbacks/list");
        HttpGet request = new HttpGet(builder.build());
        return this.getList(Callback[].class, request);
	}
	
	public List<Callback> getCallbackPage(Integer offset, Integer length) throws Exception {
		URIBuilder builder = new URIBuilder("/emit/callbacks/page");
		builder.addParameter("offset", offset.toString());
		builder.addParameter("length", length.toString());
        HttpGet request = new HttpGet(builder.build());
        return this.getList(Callback[].class, request);
	}
	
	public Callback getCallback(Long id) throws Exception {
		URIBuilder builder = new URIBuilder("/emit/callbacks/find");
		builder.addParameter("id", id.toString());
        HttpGet request = new HttpGet(builder.build());
        return this.getObject(Callback.class, request);
	}
	
	public Long doTypeCallbackCreate(String name, Type type) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/create/type");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(4);
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("atomic", Boolean.TRUE.toString()));
        parameters.add(new BasicNameValuePair("category", Category.TYPE.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("type", type.toString().toLowerCase()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getLong(request);
	}
	
	public Integer doTypeCallbackUpdate(Long id, String name, Type type) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/update/type");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(5);
        parameters.add(new BasicNameValuePair("id", id.toString()));
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("atomic", Boolean.TRUE.toString()));
        parameters.add(new BasicNameValuePair("category", Category.TYPE.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("type", type.toString().toLowerCase()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Long doValueCallbackCreate(String name, Type type, String value) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/create/value");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(5);
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("atomic", Boolean.TRUE.toString()));
        parameters.add(new BasicNameValuePair("category", Category.VALUE.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("type", type.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("value", value));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getLong(request);
	}
	
	public Integer doValueCallbackUpdate(Long id, String name, Type type, String value) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/update/value");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(6);
        parameters.add(new BasicNameValuePair("id", id.toString()));
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("atomic", Boolean.TRUE.toString()));
        parameters.add(new BasicNameValuePair("category", Category.VALUE.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("type", type.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("value", value));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Long doTopicCallbackCreate(String name, String topic) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/create/topic");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(4);
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("atomic", Boolean.TRUE.toString()));
        parameters.add(new BasicNameValuePair("category", Category.TOPIC.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("topic", topic));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getLong(request);
	}
	
	public Integer doTopicCallbackUpdate(Long id, String name, String topic) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/update/topic");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(5);
        parameters.add(new BasicNameValuePair("id", id.toString()));
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("atomic", Boolean.TRUE.toString()));
        parameters.add(new BasicNameValuePair("category", Category.TOPIC.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("topic", topic));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Long doStorageCallbackCreate(String name, Collection collection) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/create/storage");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(4);
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("atomic", Boolean.TRUE.toString()));
        parameters.add(new BasicNameValuePair("category", Category.STORAGE.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("collection", collection.toString().toLowerCase()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getLong(request);
	}
	
	public Integer doStorageCallbackUpdate(Long id, String name, Collection collection) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/update/storage");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(5);
        parameters.add(new BasicNameValuePair("id", id.toString()));
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("atomic", Boolean.TRUE.toString()));
        parameters.add(new BasicNameValuePair("category", Category.STORAGE.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("collection", collection.toString().toLowerCase()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public <T> Long doFeatureCallbackCreate(String name, Symbol symbol, Class<T> type, T value) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/create/feature");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(6);
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("atomic", Boolean.TRUE.toString()));
        parameters.add(new BasicNameValuePair("category", Category.FEATURE.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("symbol", symbol.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("type", this.getType(type).toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("value", value.toString()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getLong(request);
	}
	
	public <T> Integer doFeatureCallbackUpdate(Long id, String name, Symbol symbol, Class<T> type, T value) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/update/feature");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(7);
        parameters.add(new BasicNameValuePair("id", id.toString()));
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("atomic", Boolean.TRUE.toString()));
        parameters.add(new BasicNameValuePair("category", Category.FEATURE.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("symbol", symbol.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("type", this.getType(type).toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("value", value.toString()));        
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Long doGuardCallbackCreate(String name, Callback testCallback, Callback successCallback) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/create/guard");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(5);
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("atomic", Boolean.TRUE.toString()));
        parameters.add(new BasicNameValuePair("category", Category.GUARD.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("test", testCallback.getId().toString()));
        parameters.add(new BasicNameValuePair("success", successCallback.getId().toString()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getLong(request);
	}
	
	public Long doGuardCallbackCreate(String name, Callback testCallback, Callback successCallback, Callback failureCallback) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/create/guard");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(6);
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("atomic", Boolean.TRUE.toString()));
        parameters.add(new BasicNameValuePair("category", Category.GUARD.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("test", testCallback.getId().toString()));
        parameters.add(new BasicNameValuePair("success", successCallback.getId().toString()));
        parameters.add(new BasicNameValuePair("failure", failureCallback.getId().toString()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getLong(request);
	}
	
	public Integer doGuardCallbackUpdate(Long id, String name, Callback testCallback, Callback successCallback) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/update/guard");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(6);
        parameters.add(new BasicNameValuePair("id", id.toString()));
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("atomic", Boolean.TRUE.toString()));
        parameters.add(new BasicNameValuePair("category", Category.GUARD.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("test", testCallback.getId().toString()));
        parameters.add(new BasicNameValuePair("success", successCallback.getId().toString()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Integer doGuardCallbackUpdate(Long id, String name, Callback testCallback, Callback successCallback, Callback failureCallback) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/update/guard");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(7);
        parameters.add(new BasicNameValuePair("id", id.toString()));
        parameters.add(new BasicNameValuePair("name", name));
        parameters.add(new BasicNameValuePair("atomic", Boolean.TRUE.toString()));
        parameters.add(new BasicNameValuePair("category", Category.GUARD.toString().toLowerCase()));
        parameters.add(new BasicNameValuePair("test", testCallback.getId().toString()));
        parameters.add(new BasicNameValuePair("success", successCallback.getId().toString()));
        parameters.add(new BasicNameValuePair("failure", failureCallback.getId().toString()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Boolean doCallbackDelete(Long id) throws Exception {
        HttpPost request = new HttpPost("/emit/callbacks/delete");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(1);
        parameters.add(new BasicNameValuePair("id", id.toString()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getBoolean(request);
	}
	
	/* end of callbacks */
	
	/* clients & callbacks */
	
	public Integer doAttach(Client client, Callback callback) throws Exception {
        HttpPost request = new HttpPost("/emit/clients/attach");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(3);
        parameters.add(new BasicNameValuePair("uuid", client.getUuid()));
        parameters.add(new BasicNameValuePair("id", callback.getId().toString()));
        parameters.add(new BasicNameValuePair("category", callback.getCategory()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Integer doDetach(Client client) throws Exception {
        HttpPost request = new HttpPost("/emit/clients/detach");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(1);
        parameters.add(new BasicNameValuePair("uuid", client.getUuid()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Callback isAttached(Client client) throws Exception {
		URIBuilder builder = new URIBuilder("/emit/clients/attached");
		builder.addParameter("uuid", client.getUuid());
        HttpGet request = new HttpGet(builder.build());
        return this.getObject(Callback.class, request);
	}
	
	/* end of clients & callbacks */
	
	/* client connections */
	
	public Integer doConnect(Client client) throws Exception {
        HttpPost request = new HttpPost("/emit/clients/connect");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(1);
        parameters.add(new BasicNameValuePair("client", client.getUuid()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Integer doDisconnect(Connect connect) throws Exception {
        HttpPost request = new HttpPost("/emit/clients/disconnect");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(1);
        parameters.add(new BasicNameValuePair("client", connect.getClient().getUuid()));
        parameters.add(new BasicNameValuePair("connect", connect.getId().toString()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Connect isConnected(Client client) throws Exception {
		URIBuilder builder = new URIBuilder("/emit/clients/connected");
		builder.addParameter("client", client.getUuid());
        HttpGet request = new HttpGet(builder.build());
        return this.getObject(Connect.class, request);
	}
	
	/* end of client connections */
	
	/* client subscriptions */
	
	public Integer doSubscribe(Client client, String topic) throws Exception {
        HttpPost request = new HttpPost("/emit/clients/subscribe");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
        parameters.add(new BasicNameValuePair("client", client.getUuid()));
        parameters.add(new BasicNameValuePair("topic", topic));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Integer doUnsubscribe(Client client, String topic) throws Exception {
        HttpPost request = new HttpPost("/emit/clients/unsubscribe");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
        parameters.add(new BasicNameValuePair("client", client.getUuid()));
        parameters.add(new BasicNameValuePair("topic", topic));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	public Subscribe isSubscribing(Client client) throws Exception {
        HttpPost request = new HttpPost("/emit/clients/subscribing");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(1);
        parameters.add(new BasicNameValuePair("client", client.getUuid()));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getObject(Subscribe.class, request);
	}
	
	/* end of client subscriptions */
	
	/* client publications */
	
	public Integer doPublish(Client client, String topic, int qos, boolean retained, boolean persisted, byte[] payload) throws Exception {
        HttpPost request = new HttpPost("/emit/clients/publish");
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(6);
        parameters.add(new BasicNameValuePair("client", client.getUuid()));
        parameters.add(new BasicNameValuePair("topic", topic));
        parameters.add(new BasicNameValuePair("qos", Integer.valueOf(qos).toString()));
        parameters.add(new BasicNameValuePair("retained", Boolean.valueOf(retained).toString()));
        parameters.add(new BasicNameValuePair("persisted", Boolean.valueOf(persisted).toString()));
        parameters.add(new BasicNameValuePair("payload", new String(payload)));
        request.setEntity(new UrlEncodedFormEntity(parameters));
        return this.getInteger(request);
	}
	
	/* end of client publications */

	
	/* begin of client messages */
	
	public Long getTimestamp() throws Exception {
		URIBuilder builder = new URIBuilder("/emit/timestamp");
        HttpGet request = new HttpGet(builder.build());
        return this.getLong(request);
	}
	
	public Integer getMessageSize(Client client) throws Exception {
		URIBuilder builder = new URIBuilder("/emit/messages/size");
		builder.addParameter("client", client.getUuid());
        HttpGet request = new HttpGet(builder.build());
        return this.getInteger(request);
	}
	
	public List<Message> getMessagePage(Client client, Long issued) throws Exception {
		URIBuilder builder = new URIBuilder("/emit/messages/search");
		builder.addParameter("client", client.getUuid());
		builder.addParameter("started", issued.toString());
        HttpGet request = new HttpGet(builder.build());
        return this.getList(Message[].class, request);
	}
	
	/*
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
	
	public Connect getConnect(Client client) throws Exception {
		String parameters = "?client=" + client.getUuid();
        HttpGet request = new HttpGet("/emit/clients/connected" + parameters);
        return this.getObject(Connect.class, request);
	}
	
	public Boolean doConnect(Client client) throws Exception {
		 HttpPost request = new HttpPost("/emit/clients/connect");
         List<NameValuePair> parameters = new ArrayList<NameValuePair>();
         parameters.add(new BasicNameValuePair("client", client.getUuid()));
         request.setEntity(new UrlEncodedFormEntity(parameters));
         return this.getBoolean(request);
	}
	
	public Boolean doDisconnect(Client client, Connect connect) throws Exception {
		 HttpPost request = new HttpPost("/emit/clients/disconnect");
         List<NameValuePair> parameters = new ArrayList<NameValuePair>();
         parameters.add(new BasicNameValuePair("client", client.getUuid()));
         parameters.add(new BasicNameValuePair("connect", connect.getId().toString()));
         request.setEntity(new UrlEncodedFormEntity(parameters));
         return this.getBoolean(request);
	}
	*/
	
	/*
      <url-pattern>/clients/subscribing</url-pattern>
      <url-pattern>/clients/subscribe</url-pattern>
      <url-pattern>/clients/unsubscribe</url-pattern>
      <url-pattern>/clients/publish</url-pattern>
      <url-pattern>/clients/callback</url-pattern>
      <url-pattern>/clients/shares/create</url-pattern>
      <url-pattern>/clients/shares/update</url-pattern>
      <url-pattern>/clients/shares/delete</url-pattern>
      <url-pattern>/clients/shares/remove</url-pattern>
      <url-pattern>/clients/callbacks/list</url-pattern>
      <url-pattern>/clients/callbacks/item</url-pattern>
      <url-pattern>/clients/callbacks/attach</url-pattern>
      <url-pattern>/clients/callbacks/detach</url-pattern>
	 * */

	private Integer getSize(HttpRequestBase request) throws IOException, ClientProtocolException, EmitClientException {
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

	private <T> T getObject(Class<T> type, HttpRequestBase request) throws IOException, ClientProtocolException, EmitClientException {
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
	
	private <T> List<T> getList(Class<T[]> type, HttpRequestBase request) throws IOException, ClientProtocolException, EmitClientException {
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
	
	private Integer getInteger(HttpRequestBase request) throws IOException, ClientProtocolException, EmitClientException {
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
	
	private Long getLong(HttpRequestBase request) throws IOException, ClientProtocolException, EmitClientException {
		HttpResponse response = client.execute(host, request);
		HttpEntity entity = response.getEntity();
		String message = EntityUtils.toString(entity);
		int status = response.getStatusLine().getStatusCode();
		EntityUtils.consume(entity);
		if (status == 200) {
			return Long.valueOf(message);
		} else {
			throw new EmitClientException(status, message);
		}
	}

	private UUID getUUID(HttpRequestBase request) throws IOException, ClientProtocolException, EmitClientException {
		return UUID.fromString(this.getString(request));
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
