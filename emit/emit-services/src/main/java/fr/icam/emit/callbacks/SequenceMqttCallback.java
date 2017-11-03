package fr.icam.emit.callbacks;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SequenceMqttCallback extends EmitMqttCallback implements MqttCallback {

	protected List<EmitMqttCallback> callbacks;
	
	private SequenceMqttCallback() {
		this.callbacks = new LinkedList<EmitMqttCallback>();
	}
	
	public SequenceMqttCallback(EmitMqttCallback... callbacks) {
		this();
		for (EmitMqttCallback callback : callbacks) {
			this.callbacks.add(callback);
		}
	}

	@Override
	public void doEmbedd(Map<String, Object> parameters, boolean root) {
		super.doEmbedd(parameters, root);
		for (EmitMqttCallback callback : callbacks) {
			callback.doEmbedd(parameters, false);
		}
	}	
	
	@Override
	public void connectionLost(Throwable throwable) {
		for (MqttCallback callback : callbacks) {
			callback.connectionLost(throwable);
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		for (MqttCallback callback : callbacks) {
			callback.deliveryComplete(token);
		}
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) {
		for (EmitMqttCallback callback : callbacks) {
			callback.messageArrived(topic, message);
		}
		if (root) {
			parameters.clear();
		}
	}

}
