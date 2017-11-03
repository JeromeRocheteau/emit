package fr.icam.emit.callbacks;

import org.bson.Document;
import org.bson.types.Binary;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoMqttCallback extends EmitMqttCallback {

	private String clientId;
	
	private MongoCollection<Document> messages;
	
	private MongoCollection<Document> failures;
	
	public MongoMqttCallback(MongoDatabase db, String id) throws Exception {
		this.clientId = id;
		messages = db.getCollection("messages");
		failures = db.getCollection("failures");
	}
	
	@Override
	public void connectionLost(Throwable throwable) {
		String message = throwable.getMessage();
		Document document = new Document();
        document.append("issued", System.currentTimeMillis());
        document.append("client", clientId);
		document.append("message", message);
		failures.insertOne(document);
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) {
		boolean retained = message.isRetained();
		int qos = message.getQos();
		boolean duplicate = message.isDuplicate();
		byte[] payload = message.getPayload();
        Document document = new Document();
        document.append("id", message.getId());
        document.append("mode", "sub");
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
        messages.insertOne(document);
	}

}
