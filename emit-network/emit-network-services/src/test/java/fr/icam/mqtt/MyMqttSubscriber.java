package fr.icam.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MyMqttSubscriber implements MqttCallback {
    
    private static final String broker = "tcp://172.21.50.3:1883";

    private MqttClient client;
    
	public void doConnect() throws Exception {
        String id = this.getClass().getSimpleName();
        MemoryPersistence persistence = new MemoryPersistence();
        client = new MqttClient(broker, id, persistence);
        client.setCallback(new MyMqttCallback());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        System.out.println("Connecting to " + broker);
        client.connect(options);
        System.out.println("Connected");
	}
	
	public void doDisconnect() throws Exception {
        System.out.println("Disconnecting");
		client.disconnect();
        System.out.println("Disconnected");
	}
	
	public void doSubsribe(String topic) throws Exception {
        client.subscribe(topic);
	}

	@Override
	public void connectionLost(Throwable t) {
		t.printStackTrace();
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		 System.err.println(topic + ": " + message);
	}
	
	public static void main(String[] arguments) throws Exception {
		MyMqttSubscriber client = new MyMqttSubscriber();
		client.doConnect();
		client.doSubsribe("#");
		Thread.sleep(15000);
		client.doDisconnect();
	}
	
}
