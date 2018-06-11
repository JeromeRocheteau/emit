package fr.icam.emit.callbacks;

import org.bson.Document;
import org.bson.types.Binary;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.mongodb.client.MongoCollection;

public class StorageMqttCallback extends MongoMqttCallback {

	public StorageMqttCallback(MongoCollection<Document> collection, String id) throws Exception {
		super(collection, id);
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) {
		boolean retained = message.isRetained();
		int qos = message.getQos();
		boolean duplicate = message.isDuplicate();
		byte[] payload = message.getPayload();
        Document document = new Document();
        document.append("id", message.getId());
        document.append("mode", "subscribe");
        document.append("issued", System.currentTimeMillis());
        document.append("client", clientId);
        document.append("qos", qos);
        document.append("retained", retained);
        document.append("topic", topic);
        document.append("duplicate", duplicate);
        document.append("payload", new Binary(payload));
        for (String key : parameters.keySet()) {
       		document.append(key, parameters.get(key));
        }
        collection.insertOne(document);
	}

}
