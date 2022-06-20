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
	public void embedd(Map<String, Object> parameters, boolean root) {
		super.embedd(parameters, root);
		for (EmitMqttCallback callback : callbacks) {
			callback.embedd(parameters, false);
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
		this.status(true);
		for (EmitMqttCallback callback : callbacks) {
			callback.messageArrived(topic, message);
			if (callback.status()) {
				continue;
			} else {
				this.status(false);
				break;
			}
		}
		if (this.root()) {
			parameters.clear();
		}
	}

}
