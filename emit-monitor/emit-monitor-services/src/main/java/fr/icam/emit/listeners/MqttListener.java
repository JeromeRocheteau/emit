package fr.icam.emit.listeners;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MqttListener extends MqttClient implements MqttCallback {

	private static Map<Long, MqttListener> instances = new HashMap<Long, MqttListener>(124);

	public static void start(Long id, String uri, String uuid, String topic, MongoDatabase database) throws MqttException {
		MqttListener listener = instances.get(id);
		if (listener == null) {
			listener = new MqttListener(id, uri, uuid, topic, database);
			instances.put(id, listener);
		}
		listener.subscribe(topic);
	}
	
	public static void stop(Long id) throws MqttException {
		MqttListener listener = instances.get(id);
		if (listener == null) {
			throw new MqttException(new NullPointerException("No MQTT Client found for feature id = '" + id.toString() +"'"));
		}
		listener.disconnect();
		instances.remove(id);
	}
	
	private MongoCollection<Document> collection;
	
	private MqttListener(Long id, String uri, String uuid, String topic, MongoDatabase db) throws MqttException {
		super(uri, uuid, new MemoryPersistence());
		this.collection = db.getCollection(this.getClientId());
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(true);
		this.connect(options);
		this.setCallback(this);
	}

	public void connectionLost(Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		Double value = Double.valueOf(new String(message.getPayload()));
		Document document = new Document();
		// document.append("type", topic);
		document.append("value", value);
		document.append("time", System.currentTimeMillis()); /* FIXME */
		collection.insertOne(document);
	}

}
