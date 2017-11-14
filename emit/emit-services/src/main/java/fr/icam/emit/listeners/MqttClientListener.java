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
	
	public MongoCollection<Document> getMessages() {
		return messages;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		MongoDatabase db = (MongoDatabase) sce.getServletContext().getAttribute("mongodb-database");
		String name = sce.getServletContext().getInitParameter("mongodb-message-collection");
		messages = db.getCollection(name);
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
					System.out.println("[EMIT] Disonnect Client " + client.getClientId());
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
		}
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
		MqttClient client = clients.get(id);
		if (client == null) {
			throw new NullPointerException(id);
		} else {
			client.subscribe(topic);
		}
	}
	
	public void doUnsubscribe(String id, String topic) throws Exception {
		MqttClient client = clients.get(id);
		if (client == null) {
			throw new NullPointerException(id);
		} else {
			client.unsubscribe(topic);
		}
	}
	
	public void doPublish(String id, String topic, int qos, boolean retained, byte[] payload) throws Exception {
		MqttClient client = clients.get(id);
		if (client == null) {
			throw new NullPointerException(id);
		} else {
			client.publish(topic, payload, qos, retained);
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
	
	public void doAttach(String id, MqttCallback callback) {
		MqttClient client = clients.get(id);
		if (client == null) {
			throw new NullPointerException(id);
		} else if (callback instanceof EmitMqttCallback) {
			EmitMqttCallback cb = (EmitMqttCallback) callback;
			client.setCallback(cb);
			cb.doEmbedd(new HashMap<String, Object>(), true);
		} else {
			MqttCallbackWrapper cb = new MqttCallbackWrapper(callback);
			client.setCallback(cb);
			cb.doEmbedd(new HashMap<String, Object>(), true);
		}
	}
	
	public void doDetach(String id) {
		MqttClient client = clients.get(id);
		if (client == null) {
			throw new NullPointerException(id);
		} else {
			client.setCallback(null);
		}
	}
	
}
