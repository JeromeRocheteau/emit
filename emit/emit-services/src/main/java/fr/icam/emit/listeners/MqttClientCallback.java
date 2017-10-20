package fr.icam.emit.listeners;

import org.bson.Document;
import org.bson.types.Binary;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.mongodb.client.MongoCollection;

public class MqttClientCallback implements MqttCallback {

	private String uuid;
	
	private MongoCollection<Document> collection;
	
	public MqttClientCallback(MongoCollection<Document> collection, String uuid) {
		this.collection = collection;
		this.uuid = uuid;
	}
	
	@Override
	public void connectionLost(Throwable throwable) {
		throwable.printStackTrace();
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		boolean retained = message.isRetained();
		int qos = message.getQos();
		byte[] payload = message.getPayload();
        Document document = new Document();
        document.append("type", "sub");
        document.append("issued", System.currentTimeMillis());
        document.append("client", uuid);
        document.append("qos", qos);
        document.append("retained", retained);
        document.append("topic", topic);
        // document.append("duplicate", duplicate);
        document.append("payload", new Binary(payload));
        collection.insertOne(document);
	}


}
