package fr.icam.emit.listeners;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublisher implements Runnable {
	
	private MqttClient client;
	
	private MqttConnectOptions options;
	
	private ScheduledExecutorService service;
	
	private Random random;
	
	private String broker;
	
	private String uuid;
	
	private String instrument;
	
	private String feature;
	
	private Integer ceil;
	
	private Integer floor;
	
	private Long delay;
	
	private Boolean status;
	
	public String getBroker() {
		return broker;
	}

	public String getUuid() {
		return uuid;
	}

	public String getInstrument() {
		return instrument;
	}

	public String getFeature() {
		return feature;
	}

	public Integer getCeil() {
		return ceil;
	}

	public Integer getFloor() {
		return floor;
	}

	public Long getDelay() {
		return delay;
	}

	public Boolean getStatus() {
		return status;
	}

	MqttPublisher(String broker, String uuid, String instrument, String feature, Integer floor, Integer ceil, Long delay) throws Exception {
		try {
			random = new Random();
			this.broker = broker;
			this.uuid = uuid;
			this.instrument = instrument;
			this.feature = feature;
			this.floor = floor;
			this.ceil = ceil;
			this.delay = delay;
			this.status = Boolean.FALSE;
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public void doStart() throws Exception {
		MemoryPersistence persistence = new MemoryPersistence();
		client = new MqttClient(broker, uuid, persistence);
		options = new MqttConnectOptions();
		options.setCleanSession(true);
		client.connect(options);
		service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(this, 0, delay, TimeUnit.MILLISECONDS);
		this.status = Boolean.TRUE;
	}
	
	public void doStop() throws Exception {
		service.shutdown();
		client.disconnect();
		this.status = Boolean.FALSE;
	}
	
	@Override
	public void run() {
		try {
			float measurement  = ((ceil.floatValue() - floor.floatValue()) * random.nextFloat()) + floor.floatValue();
			MqttMessage message =  new MqttMessage();
			message.setPayload(Float.valueOf(measurement).toString().getBytes());
			if (client.isConnected()) {
				client.publish(instrument + "/" + feature, message);
			} else {
				client.connect();
				this.run();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
