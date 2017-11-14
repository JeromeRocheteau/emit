package fr.icam.emit.callbacks;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

import org.bson.Document;
import org.eclipse.paho.client.mqttv3.MqttCallback;

import com.mongodb.client.MongoCollection;

import fr.icam.emit.entities.Callback;
import fr.icam.emit.entities.callbacks.TopicCallback;
import fr.icam.emit.entities.callbacks.TypeCallback;
import fr.icam.emit.types.Type;

public class CallbackFactory {

	public static MessageMqttCallback from(MongoCollection<Document> collection, Callback callback, String id) throws Exception {
		String category = callback.getCategory();
		if (category.equals("message")) {
			return new MessageMqttCallback(collection, id);
		} else {
			throw new Exception("wrong callback category '" + category + "' <> 'message'");
		}
	}

	public static TopicMqttCallback from(TopicCallback callback) throws Exception {
		String category = callback.getCategory();
		if (category.equals("topic")) {
			return new TopicMqttCallback(callback.getTopic());			
		} else {
			throw new Exception("wrong callback category '" + category + "' <> 'topic'");
		}
	}

	public static MqttCallback from(TypeCallback callback) throws Exception {
		String callbackCategory = callback.getCategory();
		if (callbackCategory.equals("type")) {
			String category = callback.getType().getCategory();
			if (category.equals("datatype")) {
				String name = callback.getType().getName();
				if (name.equals("string")) {
					return new TypeMqttCallback<String>(Type.STRING);
				} else if (name.equals("boolean")) {
					return new TypeMqttCallback<Boolean>(Type.BOOLEAN);
				} else if (name.equals("integer")) {
					return new TypeMqttCallback<Integer>(Type.INTEGER);
				} else if (name.equals("long")) {
					return new TypeMqttCallback<Long>(Type.LONG);
				} else if (name.equals("float")) {
					return new TypeMqttCallback<Float>(Type.FLOAT);
				} else if (name.equals("double")) {
					return new TypeMqttCallback<Double>(Type.DOUBLE);
				} else if (name.equals("date")) {
					return new TypeMqttCallback<Date>(Type.DATE);
				} else if (name.equals("uri")) {
					return new TypeMqttCallback<URI>(Type.URI);
				} else if (name.equals("uuid")) {
					return new TypeMqttCallback<UUID>(Type.UUID);
				} else {
					throw new Exception("undefined datatype name '" + name + "'");
				}
			} else {
				throw new Exception("wrong type category '" + category + "' <> 'datatype'");
			}						
		} else {
			throw new Exception("wrong callback category '" + callbackCategory + "'  <> 'type'");
		}
	}
	
}
