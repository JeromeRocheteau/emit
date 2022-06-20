package fr.icam.emit.tests;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import fr.icam.emit.callbacks.EmitMqttCallback;

public class TestMqttCallback extends EmitMqttCallback {

	@Override
	public void messageArrived(String topic, MqttMessage message) {
		System.out.println("callback: " + this.hashCode() + " " + this.getClass().getSimpleName());
		System.out.println("  message: " + message.getId());
		for (String key : parameters.keySet()) {
			System.out.println("  " + key + ": " + parameters.get(key));	
		}
	}

}
