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
	public void doEmbedd(Map<String, Object> parameters, boolean root) {
		super.doEmbedd(parameters, root);
		callback.doEmbedd(parameters, false);
		successCallback.doEmbedd(parameters, false);
		if (failureCallback != null) {
			failureCallback.doEmbedd(parameters, false);
		}
	}
	
	@Override
	public final void messageArrived(String topic, MqttMessage message) {
		callback.messageArrived(topic, message);
		Object value = parameters.get("_value");
		if (value == null) {
			if (failureCallback != null) failureCallback.messageArrived(topic, message);
		} else {
			if (value instanceof Boolean) {
				Boolean bool = (Boolean) value;
				if (bool) {
					successCallback.messageArrived(topic, message);
				} else {
					if (failureCallback != null) failureCallback.messageArrived(topic, message);
				}
			} else {
				successCallback.messageArrived(topic, message);
			}
		}
		if (root) {
			parameters.clear();
		}
	}

}
