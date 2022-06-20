package fr.icam.emit.callbacks;

import java.util.Map;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class GuardMqttCallback extends EmitMqttCallback implements MqttCallback {

	private EmitMqttCallback callback;
	
	private EmitMqttCallback successCallback;
	
	private EmitMqttCallback failureCallback;
	
	protected GuardMqttCallback() { }
	
	public GuardMqttCallback(EmitMqttCallback callback, EmitMqttCallback successCallback) {
		this.callback = callback;
		this.successCallback = successCallback;
	}
	
	public GuardMqttCallback(EmitMqttCallback callback, EmitMqttCallback successCallback, EmitMqttCallback failureCallback) {
		this.callback = callback;
		this.successCallback = successCallback;
		this.failureCallback = failureCallback;
	}
	
	@Override
	public void embedd(Map<String, Object> parameters, boolean root) {
		super.embedd(parameters, root);
		callback.embedd(parameters, false);
		successCallback.embedd(parameters, false);
		if (failureCallback != null) {
			failureCallback.embedd(parameters, false);
		}
	}
	
	@Override
	public final void messageArrived(String topic, MqttMessage message) {
		callback.messageArrived(topic, message);
		if (callback.status()) {
			successCallback.messageArrived(topic, message);
			this.status(successCallback.status());
		} else if (failureCallback != null) {
			failureCallback.messageArrived(topic, message);
			this.status(failureCallback.status());
		}
		if (this.root()) {
			parameters.clear();
		}
	}

}
