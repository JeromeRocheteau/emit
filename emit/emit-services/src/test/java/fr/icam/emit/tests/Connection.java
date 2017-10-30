package fr.icam.emit.tests;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Connection {

	public void doConnect(String broker, String username, String password) throws Exception {
		MqttClient client = new MqttClient(broker, UUID.randomUUID().toString(), new MemoryPersistence());
		MqttConnectOptions options = new MqttConnectOptions();
		if (username != null && password != null) {
			options.setUserName(username);
			options.setPassword(password.toCharArray());			
		}
		options.setCleanSession(true);
		client.connect(options);
		Thread.sleep(5000);
		client.disconnect();
	}
	
	public static void main(String[] args) throws Exception {
		Connection app = new Connection();
		app.doConnect("tcp://172.21.50.1:1883", "jerome", "louR8jay");
	}
	
}
