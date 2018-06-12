package fr.icam.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MyMqttPublisher {

    private static final int qos = 2;
    
    private static final String broker = "tcp://localhost:1883";

    private MqttClient client;
	
	public void doConnect() throws Exception {
        String id = this.getClass().getSimpleName();
        MemoryPersistence persistence = new MemoryPersistence();
        client = new MqttClient(broker, id, persistence);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        System.out.println("Connecting to " + broker);
        client.connect(options);
        // System.out.println("Connected");
	}
	
	public void doDisconnect() throws Exception {
        // System.out.println("Disconnecting");
		client.disconnect();
        System.out.println("Disconnected");
	}
	
	public void doPublish(String topic, String content) throws Exception {
		// System.out.println("Publishing: " + content);
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        client.publish(topic, message);
        // System.out.println("Published");
	}
	
	public static void main(String[] arguments) throws Exception {
		MyMqttPublisher client = new MyMqttPublisher();
		client.doConnect();
		for (int i = 0; i < 1000; i++) {
			client.doPublish("test", "Hello World! #" + (i + 1));
			Thread.sleep(10);
		}
		client.doDisconnect();
	}
	
}
