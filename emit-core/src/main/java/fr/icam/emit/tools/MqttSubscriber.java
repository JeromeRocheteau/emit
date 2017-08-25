package fr.icam.emit.tools;

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

public class MqttSubscriber extends MqttClient implements MqttCallback {

	private String topic;
		
	private MongoCollection<Document> collection;
	
	public MqttSubscriber(String uri, String topic, MongoDatabase db) throws MqttException {
		super(uri, UUID.randomUUID().toString(), new MemoryPersistence());
		this.topic = topic;
		this.collection = db.getCollection(this.getClientId());
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(true);
		this.connect(options);
	}
	
	public void doStart() throws Exception {
		this.setCallback(this);
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
		document.append("type", topic);
		document.append("value", value);
		document.append("time", System.currentTimeMillis()); /* FIXME */
		collection.insertOne(document);
	}

}
