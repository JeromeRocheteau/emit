package fr.icam.emit.callbacks;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class TopicMqttCallback extends EmitMqttCallback {

	private String filter;
	
	public TopicMqttCallback(String topic) {
		this.filter = topic;
	}
	
	@Override
	public void messageArrived(String topic, MqttMessage message) {
		System.out.println("[EMIT] Topic MQTT Callback - message arrived: " + new String(message.getPayload()));
		try {
			boolean matched = MqttTopic.isMatched(filter, topic);
			this.status(matched);
		} catch (Exception e) {
			this.status(false);
		}
	}

}
