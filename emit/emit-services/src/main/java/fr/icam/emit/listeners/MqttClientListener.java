package fr.icam.emit.listeners;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttClientListener implements ServletContextListener {

	private Map<String, MqttClient> clients;
	
	public void doCreate(String uuid, String broker) throws Exception {
		MemoryPersistence persistence = new MemoryPersistence();
		MqttClient client = new MqttClient(broker, uuid, persistence);
		clients.put(uuid, client);
	}
	
	public void doDelete(String uuid) {
		clients.remove(uuid);
	}
	
	public void doConnect(String uuid) throws Exception {
		MqttClient client = clients.get(uuid);
		if (client == null) {
			throw new NullPointerException(uuid);
		} else if (client.isConnected()) {
			throw new ConnectException(uuid);
		} else {
			MqttConnectOptions options = new MqttConnectOptions();
			options.setCleanSession(true);
			client.connect(options);			
		}
	}
	
	public void doDisconnect(String uuid) throws Exception {
		MqttClient client = clients.get(uuid);
		if (client == null) {
			throw new NullPointerException(uuid);
		} else if (client.isConnected()) {
			client.disconnect();
		}
	}
	
	public void doSubscribe(String uuid, String topic) throws Exception {
		MqttClient client = clients.get(uuid);
		if (client == null) {
			throw new NullPointerException(uuid);
		} else {
			client.subscribe(topic);
		}
	}
	
	public void doUnsubscribe(String uuid, String topic) throws Exception {
		MqttClient client = clients.get(uuid);
		if (client == null) {
			throw new NullPointerException(uuid);
		} else {
			client.unsubscribe(topic);
		}
	}
	
	public void doPublish(String uuid, String topic, int qos, boolean retained, byte[] payload) throws Exception {
		MqttClient client = clients.get(uuid);
		if (client == null) {
			throw new NullPointerException(uuid);
		} else {
			client.publish(topic, payload, qos, retained);
		}
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		clients = new HashMap<String, MqttClient>(124);
		sce.getServletContext().setAttribute("mqtt-client-listener", this);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		for (String uuid : clients.keySet()) {
			MqttClient client = clients.get(uuid);
			if (client.isConnected()) {
				try {
					// FIXME if connected then set connect row stopped attribute
					// FIXME if subscribing then set subscribe row stopped attribute
					// TODO
					client.disconnect();
				} catch (MqttException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
