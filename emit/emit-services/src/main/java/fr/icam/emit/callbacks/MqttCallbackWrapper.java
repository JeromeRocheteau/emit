package fr.icam.emit.callbacks;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttCallbackWrapper extends EmitMqttCallback {

	private MqttCallback callback;
	
	public MqttCallbackWrapper(MqttCallback callback) {
		this.callback = callback;
	}
	
	@Override
	public void messageArrived(String topic, MqttMessage message) {
		try {
			this.callback.messageArrived(topic, message);
		} catch (Exception e) {
			this.callback.connectionLost(e);
		}
	}

}
