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
		try {
			boolean matched = MqttTopic.isMatched(filter, topic);
			parameters.put("_value", matched);
		} catch (Exception e) {
			parameters.put("_value", null);
		}
	}

}
