package fr.icam.emit.listeners;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.UUID;

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
import fr.icam.emit.callbacks.GuardMqttCallback;
import fr.icam.emit.callbacks.MqttCallbackWrapper;
import fr.icam.emit.callbacks.StorageMqttCallback;
import fr.icam.emit.callbacks.TypeMqttCallback;
import fr.icam.emit.types.Type;

public abstract class PowermeterListener implements ServletContextListener {
	
	private MqttClient client;
	
	private MongoCollection<Document> messages;
	
	public MongoCollection<Document> getMessages() {
		return messages;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		MongoDatabase db = (MongoDatabase) sce.getServletContext().getAttribute("mongodb-database");
		String messageCollectionName = sce.getServletContext().getInitParameter("mongodb-message-collection");
		String broker = sce.getServletContext().getInitParameter("mqtt-broker");
		String topic = sce.getServletContext().getInitParameter(this.getTopic());
		messages = db.getCollection(messageCollectionName);
		this.setMqttClient(broker, topic);
	}

	protected abstract String getTopic();

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (client.isConnected()) {
			try {
				client.disconnect();
				System.out.println("[EMIT] Disonnect Client " + client.getClientId());
			} catch (MqttException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setMqttClient(String broker, String topic) {
		String uuid = UUID.randomUUID().toString();
		MemoryPersistence persistence = new MemoryPersistence();
		try {
			client = new MqttClient(broker, uuid, persistence);
			MqttCallback callback = this.getCallback(uuid);
			this.doAttach(uuid, callback);
			this.doConnect(uuid);
			this.doSubscribe(uuid, topic);
		} catch (Exception e) {
			System.out.println("[EMIT ERROR] Client '" + client.getClientId()+ "' Initialization Error: " + e.getMessage());
			e.printStackTrace(System.out);
		}
	}

	public boolean isConnected(String id) throws Exception {
		if (client == null) {
			throw new NullPointerException(id);
		} else {
			return client.isConnected(); 			
		}		
	}
	
	public void doConnect(String id) throws Exception {
		if (client == null) {
			throw new NullPointerException(id);
		} else if (client.isConnected()) {
			throw new ConnectException(id);
		} else {
			MqttConnectOptions options = new MqttConnectOptions();
			options.setCleanSession(true);
			client.connect(options);
		}
	}
	
	public void doDisconnect(String id) throws Exception {
		if (client == null) {
			throw new NullPointerException(id);
		} else if (client.isConnected()) {
			client.disconnect();
		}
	}
	
	public void doSubscribe(String id, String topic) throws Exception {
		if (client == null) {
			throw new NullPointerException(id);
		} else {
			client.subscribe(topic);
		}
	}
	
	public void doUnsubscribe(String id, String topic) throws Exception {
		if (client == null) {
			throw new NullPointerException(id);
		} else {
			client.unsubscribe(topic);
		}
	}
	
	public void doPublish(String id, String topic, int qos, boolean retained, boolean persisted, byte[] payload) throws Exception {
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
		if (client == null) {
			throw new NullPointerException(id);
		} else if (callback instanceof EmitMqttCallback) {
			EmitMqttCallback cb = (EmitMqttCallback) callback;
			client.setCallback(cb);
			cb.embedd(new HashMap<String, Object>(), true);
		} else {
			MqttCallbackWrapper cb = new MqttCallbackWrapper(callback);
			client.setCallback(cb);
			cb.embedd(new HashMap<String, Object>(), true);
		}
	}
	
	public void doDetach(String id) {
		if (client == null) {
			throw new NullPointerException(id);
		} else {
			client.setCallback(null);
		}
	}
	
	private MqttCallback getCallback(String uuid) throws Exception {
		TypeMqttCallback<Float> typeCallback = new TypeMqttCallback<Float>(Type.FLOAT);
		StorageMqttCallback storageCallback = new StorageMqttCallback(messages, uuid);
		return new GuardMqttCallback(typeCallback, storageCallback);
	}
	
}
