package fr.icam.emit.callbacks;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public abstract class MongoMqttCallback extends EmitMqttCallback {

	protected String clientId;
	
	protected MongoCollection<Document> collection;
	
	public MongoMqttCallback(MongoCollection<Document> collection, String id) throws Exception {
		this.clientId = id;
		this.collection = collection;
	}

}
