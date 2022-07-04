package fr.icam.emit.callbacks;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import fr.icam.emit.types.Type;

public class ValueMqttCallback<T extends Comparable<T>> extends EmitMqttCallback {

	private Type<T> type;
	
	private T value;
	
	public ValueMqttCallback(Type<T> type, T value) {
		this.type = type;
		this.value = value;
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) {
		System.out.println("[EMIT] Value MQTT Callback - message arrived: " + new String(message.getPayload()));
		try {
			parameters.put("type", type.getName());
			parameters.put("value", value);
			this.status(true);
		} catch (Exception e) {
			this.status(false);
		}
	}

}
