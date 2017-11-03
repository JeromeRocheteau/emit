package fr.icam.emit.callbacks;

import java.util.Map;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public abstract class EmitMqttCallback implements MqttCallback {

	protected boolean root;
	
	protected Map<String, Object> parameters;
	
	public void doEmbedd(Map<String, Object> parameters, boolean root) {
		this.parameters = parameters;
		this.root = root;
	}
	
	@Override
	public void connectionLost(Throwable throwable) { }

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) { }
	
	@Override
	public abstract void messageArrived(String topic, MqttMessage message);
	
}
