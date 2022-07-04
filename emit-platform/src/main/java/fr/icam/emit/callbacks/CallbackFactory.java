package fr.icam.emit.callbacks;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;

import fr.icam.emit.entities.Callback;
import fr.icam.emit.entities.callbacks.FeatureCallback;
import fr.icam.emit.entities.callbacks.GuardCallback;
import fr.icam.emit.entities.callbacks.StorageCallback;
import fr.icam.emit.entities.callbacks.TopicCallback;
import fr.icam.emit.entities.callbacks.TypeCallback;
import fr.icam.emit.entities.callbacks.ValueCallback;
import fr.icam.emit.listeners.MqttClientListener;
import fr.icam.emit.types.Symbol;
import fr.icam.emit.types.Type;

public class CallbackFactory {

	private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	public static EmitMqttCallback from(MqttClientListener listener, String id, Callback callback) throws Exception {
		String category = callback.getCategory();
		if (category.equals("type")) {
			return CallbackFactory.from((TypeCallback) callback);
		} else if (category.equals("value")) {
			return CallbackFactory.from((ValueCallback) callback);
		} else if (category.equals("topic")) {
			return CallbackFactory.from((TopicCallback) callback);
		} else if (category.equals("storage")) {
			return CallbackFactory.from(listener, (StorageCallback) callback, id);
		} else if (category.equals("feature")) {
			return CallbackFactory.from((FeatureCallback) callback);
		} else if (category.equals("guard")) {
			return CallbackFactory.from(listener, id, (GuardCallback) callback);
		} else {
			throw new ServletException("undefined callback category '" + category + "'");
		}
	}
	
	private static EmitMqttCallback from(MqttClientListener listener, String id, GuardCallback callback) throws Exception {
		EmitMqttCallback test = from(listener, id, callback.getTest());
		EmitMqttCallback success = from(listener, id, callback.getSuccess());
		if (callback.getFailure() == null) {
			return new GuardMqttCallback(test, success);
		} else {
			EmitMqttCallback failure = from(listener, id, callback.getFailure());
			return new GuardMqttCallback(test, success, failure);			
		}
	}

	private static EmitMqttCallback from(FeatureCallback callback) throws Exception {
		String value = callback.getValue();
		Symbol symbol = from(callback.getSymbol());
		String category = callback.getType().getCategory();
		if (category.equals("datatype")) {
			String name = callback.getType().getName();
			if (name.equals("string")) {
				return new FeatureMqttCallback<String>(symbol, Type.STRING, Type.STRING.valueOf(value));
			} else if (name.equals("boolean")) {
				return new FeatureMqttCallback<Boolean>(symbol, Type.BOOLEAN, Type.BOOLEAN.valueOf(value));
			} else if (name.equals("integer")) {
				return new FeatureMqttCallback<Integer>(symbol, Type.INTEGER, Type.INTEGER.valueOf(value));
			} else if (name.equals("long")) {
				return new FeatureMqttCallback<Long>(symbol, Type.LONG, Type.LONG.valueOf(value));
			} else if (name.equals("float")) {
				return new FeatureMqttCallback<Float>(symbol, Type.FLOAT, Type.FLOAT.valueOf(value));
			} else if (name.equals("double")) {
				return new FeatureMqttCallback<Double>(symbol, Type.DOUBLE, Type.DOUBLE.valueOf(value));
			} else if (name.equals("date")) {
				return new FeatureMqttCallback<Date>(symbol, Type.DATE, Type.DATE.valueOf(value));
			} else if (name.equals("uri")) {
				return new FeatureMqttCallback<URI>(symbol, Type.URI, Type.URI.valueOf(value));
			} else if (name.equals("uuid")) {
				return new FeatureMqttCallback<UUID>(symbol, Type.UUID, Type.UUID.valueOf(value));
			} else {
				throw new Exception("undefined datatype name '" + name + "'");
			}
		} else {
			throw new Exception("wrong type category '" + category + "' <> 'datatype'");
		}
	}

	private static Symbol from(fr.icam.emit.entities.Symbol symbol) throws Exception {
		String name = symbol.getName();
		if (name.equals(Symbol.EQ.getName())) {
			return Symbol.EQ;
		} else if (name.equals(Symbol.NEQ.getName())) {
			return Symbol.NEQ;
		} else if (name.equals(Symbol.LT.getName())) {
			return Symbol.LT;
		} else if (name.equals(Symbol.LEQ.getName())) {
			return Symbol.LEQ;
		} else if (name.equals(Symbol.GT.getName())) {
			return Symbol.GT;
		} else if (name.equals(Symbol.GEQ.getName())) {
			return Symbol.GEQ;
		} else {
			throw new Exception("undefined symbol name '" + name + "'");			
		}
	}

	private static StorageMqttCallback from(MqttClientListener listener, StorageCallback callback, String id) throws Exception {
		String name = callback.getCollection();
		if (name.equals("messages")) {
			return new StorageMqttCallback(listener.getMessages(), id);			
		} else if (name.equals("failures")) {
			return new StorageMqttCallback(listener.getFailures(), id);
		} else {
			throw new Exception("undefined collection name '" + name + "' (it should be either 'messages' or 'failures')");
		}
	}

	private static TopicMqttCallback from(TopicCallback callback) throws Exception {
		String topic = callback.getTopic();
		if (topic == null || topic.isEmpty()) {
			throw new Exception("wrong callback topic '" + topic + "'");
		} else {
			return new TopicMqttCallback(callback.getTopic());			
		}
	}

	private static EmitMqttCallback from(ValueCallback callback) throws Exception {
		String category = callback.getType().getCategory();
		String value = callback.getValue();
		if (category.equals("datatype")) {
			String name = callback.getType().getName();
			if (name.equals("string")) {
				return new ValueMqttCallback<String>(Type.STRING, value);
			} else if (name.equals("boolean")) {
				return new ValueMqttCallback<Boolean>(Type.BOOLEAN, Boolean.valueOf(value));
			} else if (name.equals("integer")) {
				return new ValueMqttCallback<Integer>(Type.INTEGER, Integer.valueOf(value));
			} else if (name.equals("long")) {
				return new ValueMqttCallback<Long>(Type.LONG, Long.valueOf(value));
			} else if (name.equals("float")) {
				return new ValueMqttCallback<Float>(Type.FLOAT, Float.valueOf(value));
			} else if (name.equals("double")) {
				return new ValueMqttCallback<Double>(Type.DOUBLE, Double.valueOf(value));
			} else if (name.equals("date")) {
				return new ValueMqttCallback<Date>(Type.DATE, formatter.parse(value));
			} else if (name.equals("uri")) {
				return new ValueMqttCallback<URI>(Type.URI, URI.create(value));
			} else if (name.equals("uuid")) {
				return new ValueMqttCallback<UUID>(Type.UUID, UUID.fromString(value));
			} else {
				throw new Exception("undefined datatype name '" + name + "'");
			}
		} else {
			throw new Exception("wrong type category '" + category + "' <> 'datatype'");
		}						
	}

	private static EmitMqttCallback from(TypeCallback callback) throws Exception {
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
	}
	
}
