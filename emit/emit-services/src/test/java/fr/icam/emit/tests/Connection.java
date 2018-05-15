package fr.icam.emit.tests;

import java.util.HashMap;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import fr.icam.emit.callbacks.FeatureMqttCallback;
import fr.icam.emit.callbacks.GuardMqttCallback;
import fr.icam.emit.callbacks.SequenceMqttCallback;
import fr.icam.emit.callbacks.TopicMqttCallback;
import fr.icam.emit.callbacks.EmitMqttCallback;
import fr.icam.emit.callbacks.TypeMqttCallback;
import fr.icam.emit.types.Symbol;
import fr.icam.emit.types.Type;
import fr.icam.emit.types.Value;

public class Connection {	
	
	private MqttClient client;
	
	private EmitMqttCallback callback;

	public void doConnect(String broker, String username, String password) throws Exception {
		client = new MqttClient(broker, UUID.randomUUID().toString(), new MemoryPersistence());
		System.err.println("[TEST] connecting " + client.getClientId() + " to " + client.getServerURI());
		MqttConnectOptions options = new MqttConnectOptions();
		if (username != null && password != null) {
			options.setUserName(username);
			options.setPassword(password.toCharArray());			
		}
		options.setCleanSession(true);
		client.connect(options);
		System.err.println("[TEST] connected " + client.getClientId() + " to " + broker);
	}
	
	public void doDisonnect() throws Exception {
		System.err.println("[TEST] disconnecting " + client.getClientId() + " from " + client.getServerURI());
		client.disconnect();
		System.err.println("[TEST] disconnected " + client.getClientId() + " from " + client.getServerURI());
	}
	
	public void doSubscribe(String topic) throws Exception {
		System.err.println("[TEST] subscribing " + client.getClientId() + " on " + client.getServerURI() + " to " + topic);
		client.subscribe(topic);
	}
	
	public void doPublish(String topic, String content) throws Exception {
		System.err.println("[TEST] publishing " + client.getClientId() + " on " + client.getServerURI() + " to " + topic);
		client.publish(topic, new MqttMessage(content.getBytes()));
	}
	
	public void doAttach(EmitMqttCallback callback) {
		this.callback = callback;
		this.client.setCallback(this.callback);
		this.callback.embedd(new HashMap<String, Object>(), true);
	}
	
	public static void main(String[] args) throws Exception {
		Connection fst = new Connection();
		Connection snd = new Connection();

		fst.doConnect("tcp://localhost:1883", "jerome", "louR8jay");
		snd.doConnect("tcp://localhost:1883", "jerome", "louR8jay");

		EmitMqttCallback testCallback = new TestMqttCallback();
		EmitMqttCallback featureCallback = new FeatureMqttCallback<Float>(Symbol.EQ, Type.FLOAT, 4.0f);
		EmitMqttCallback typeCallback = new TypeMqttCallback<Float>(Type.FLOAT);
		EmitMqttCallback topicCallback = new TopicMqttCallback("test");
		
		EmitMqttCallback guardCallback = new GuardMqttCallback(topicCallback, new GuardMqttCallback(typeCallback, featureCallback));
		EmitMqttCallback sequenceCallback = new SequenceMqttCallback(guardCallback, testCallback);
		
		// fst.doAttach(new TopicMqttCallback("test", first));
		
		fst.doSubscribe("test");

		fst.doAttach(sequenceCallback);
		
		for (int i = 0; i < 10; i++) {
			Thread.sleep(100);
			snd.doPublish("test", "4.0");
		}
		
		for (int i = 0; i < 10; i++) {
			Thread.sleep(100);
			snd.doPublish("test", "hello world!");
		}
			
		fst.doDisonnect();
		snd.doDisonnect();
		
		System.exit(0);
		
	}
	
}
