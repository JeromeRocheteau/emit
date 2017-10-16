package fr.icam.emit.listeners;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MqttPublisherListener implements ServletContextListener {

	private Map<String, Integer> indicies;
	
	private List<MqttPublisher> publishers;
	
	public void doCreate(URI broker, String topic, Integer floor, Integer ceil, Long delay) throws Exception {
		UUID identifier = UUID.randomUUID();
		MqttPublisher publisher = new MqttPublisher(broker, identifier, topic, floor, ceil, delay);
		Integer index = publishers.size();
		indicies.put(identifier.toString(), index);
		publishers.add(publisher);
	}
	
	public void doDelete(String uuid) throws Exception {
		Integer index = indicies.get(uuid);
		indicies.remove(uuid);
		publishers.remove(index.intValue());
	}
	
	public MqttPublisher doRetrieve(String uuid) {
		Integer index = indicies.get(uuid);
		return publishers.get(index.intValue());
	}

	public Integer doSize() {
		return publishers.size();
	}
	
	public List<MqttPublisher> doRetrieve(int offset, int length) {
		int size = publishers.size();
		List<MqttPublisher> items = new ArrayList<MqttPublisher>(length);
		if (offset < size) {
			for (int i = 0; i < length; i++) {
				if (offset + i < size) {
					items.add(publishers.get(offset + i));
				}
			}			
		}
		return items;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		indicies = new HashMap<String, Integer>(124);
		publishers = new ArrayList<MqttPublisher>(124);
		sce.getServletContext().setAttribute("mqtt-publisher-listener", this);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		for (MqttPublisher publisher : publishers) {
			if (publisher.getStatus()) {
				try {
					publisher.doStop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
