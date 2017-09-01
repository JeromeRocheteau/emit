package fr.icam.emit.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

import fr.icam.emit.entities.Feature;

public class MqttListener extends MqttClient implements MqttCallback {

	private static Map<Long, MqttListener> instances = new HashMap<Long, MqttListener>(124);

	public static MqttListener get(Feature feature, MongoDatabase database) throws MqttException {
		Long id = feature.getId();
		MqttListener listener = instances.get(id);
		if (listener == null) {
			listener = new MqttListener(feature, database);
			instances.put(id, listener);
		}
		return listener;
	}
	
	private MongoCollection<Document> collection;
	
	private String topic;
	
	private String uuid;
		
	public String getTopic() {
		return topic;
	}

	public String getUuid() {
		return uuid;
	}
	
	private MqttListener(Feature feature, MongoDatabase db) throws MqttException {
		super(feature.getInstrument().getUri(), UUID.randomUUID().toString(), new MemoryPersistence());
		this.topic = feature.getInstrument().getName() + "/" + feature.getName();
		this.uuid = this.getClientId();
		this.collection = db.getCollection(this.getClientId());
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(true);
		this.connect(options);
		this.setCallback(this);
	}
	
	public void doStart() throws Exception {
		this.subscribe(topic);
	}
	
	public void doStop() throws Exception {
		this.disconnect();
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
