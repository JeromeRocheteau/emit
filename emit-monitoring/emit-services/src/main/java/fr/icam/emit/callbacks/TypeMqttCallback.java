package fr.icam.emit.callbacks;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import fr.icam.emit.types.Type;

public class TypeMqttCallback<T extends Comparable<T>> extends EmitMqttCallback {

	private Type<T> type;
	
	public TypeMqttCallback(Type<T> type) {
		this.type = type;
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) {
		String string = new String(message.getPayload());
		try {
			T value = type.valueOf(string);
			parameters.put("type", type.getName());
			parameters.put("value", value);
			this.status(true);
		} catch (Exception e) {
			this.status(false);
		}
	}

}
