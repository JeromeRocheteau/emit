package fr.icam.emit.listeners;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.bson.Document;
import org.bson.types.Binary;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import fr.icam.emit.callbacks.EmitMqttCallback;
import fr.icam.emit.callbacks.MqttCallbackWrapper;

public class MqttClientListener implements ServletContextListener {
	
	private Map<String, MqttClient> clients;
	
	private MongoCollection<Document> messages;
	
	private MongoCollection<Document> failures;
	
	public MongoCollection<Document> getMessages() {
		return messages;
	}
	public MongoCollection<Document> getFailures() {
		return failures;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		MongoDatabase db = (MongoDatabase) sce.getServletContext().getAttribute("mongodb-database");
		String messageCollectionName = sce.getServletContext().getInitParameter("mongodb-message-collection");
		String failureCollectionName = sce.getServletContext().getInitParameter("mongodb-failure-collection");
		messages = db.getCollection(messageCollectionName);
		failures = db.getCollection(failureCollectionName);

		System.out.println("[EMIT] MongoDD Collection: " + messages.toString());
		System.out.println("[EMIT] MongoDD Collection: " + failures.toString());
		
		clients = new HashMap<String, MqttClient>(124);
		sce.getServletContext().setAttribute("mqtt-client-listener", this);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		for (String id : clients.keySet()) {
			MqttClient client = clients.get(id);
			if (client.isConnected()) {
				try {
					client.disconnect();
					System.out.println("[EMIT] Disconnected Client " + client.getClientId());
				} catch (MqttException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void doCreate(String broker, String id) throws Exception {
		MemoryPersistence persistence = new MemoryPersistence();
		MqttClient client = new MqttClient(broker, id, persistence);
		clients.put(id, client);
	}
	
	public void doDelete(String id) {
		clients.remove(id);
	}
	
	public boolean isConnected(String id) throws Exception {
		MqttClient client = clients.get(id);
		if (client == null) {
			throw new NullPointerException(id);
		} else {
			return client.isConnected(); 			
		}		
	}
	
	public void doConnect(String id, String username, String password) throws Exception {
		System.out.println("[EMIT] Connecting Client " + id);
		MqttClient client = clients.get(id);
		if (client == null) {
			throw new NullPointerException(id);
		} else if (client.isConnected()) {
			throw new ConnectException(id);
		} else {
			MqttConnectOptions options = new MqttConnectOptions();
			if (username != null && password != null) {
				options.setUserName(username);
				options.setPassword(password.toCharArray());
			}
			options.setCleanSession(true);
			client.connect(options);
			System.out.println("[EMIT] Connected Client " + id);		}
	}
	
	public void doDisconnect(String id) throws Exception {
		MqttClient client = clients.get(id);
		if (client == null) {
			throw new NullPointerException(id);
		} else if (client.isConnected()) {
			client.disconnect();
		}
	}
	
	public void doSubscribe(String id, String topic) throws Exception {
		System.out.println("[EMIT] Subscribing to '" + topic + "' by Client " + id);
		MqttClient client = clients.get(id);
		if (client == null) {
			throw new NullPointerException(id);
		} else {
			client.subscribe(topic);
			System.out.println("[EMIT] Subscribed to '" + topic + "' Client " + id);
		}
	}
	
	public void doUnsubscribe(String id, String topic) throws Exception {
		System.out.println("[EMIT] Unsubscribing to '" + topic + "' by Client " + id);
		MqttClient client = clients.get(id);
		if (client == null) {
			throw new NullPointerException(id);
		} else {
			client.unsubscribe(topic);
			System.out.println("[EMIT] Unsubscribed to '" + topic + "' by Client " + id);
		}
	}
	
	public void doPublish(String id, String topic, int qos, boolean retained, boolean persisted, byte[] payload) throws Exception {
		MqttClient client = clients.get(id);
		if (client == null) {
			throw new NullPointerException(id);
		} else {
			client.publish(topic, payload, qos, retained);
			if (persisted) {
				Document document = new Document();
				document.append("mode", "publish");
				document.append("issued", System.currentTimeMillis());
				document.append("client", id);
				document.append("qos", qos);
				document.append("retained", retained);
				document.append("topic", topic);
				document.append("payload", new Binary(payload));
				messages.insertOne(document);
			}
		}
	}
	
	public void doAttach(String id, MqttCallback callback) {
		System.out.println("[EMIT] Attaching '" + callback.getClass().getSimpleName() + "' to Client " + id);
		MqttClient client = clients.get(id);
		if (client == null) {
			throw new NullPointerException(id);
		} else if (callback instanceof EmitMqttCallback) {
			EmitMqttCallback cb = (EmitMqttCallback) callback;
			client.setCallback(cb);
			cb.embedd(new HashMap<String, Object>(), true);
			System.out.println("[EMIT] Attached '" + callback.getClass().getSimpleName() + "' to Client " + id);
		} else {
			MqttCallbackWrapper cb = new MqttCallbackWrapper(callback);
			client.setCallback(cb);
			cb.embedd(new HashMap<String, Object>(), true);
			System.out.println("[EMIT] Attached '" + callback.getClass().getSimpleName() + "' to Client " + id);
		}
	}
	
	public void doDetach(String id) {
		System.out.println("[EMIT] Detaching Client " + id);
		MqttClient client = clients.get(id);
		if (client == null) {
			throw new NullPointerException(id);
		} else {
			client.setCallback(null);
			System.out.println("[EMIT] Detached Client " + id);
		}
	}
	
}
