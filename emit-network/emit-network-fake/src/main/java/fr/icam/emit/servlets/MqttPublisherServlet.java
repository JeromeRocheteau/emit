package fr.icam.emit.servlets;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import fr.icam.emit.listeners.MqttPublisher;
import fr.icam.emit.listeners.MqttPublisherListener;

public class MqttPublisherServlet extends HttpServlet {

	private static final long serialVersionUID = 201709140844000L;

	private MqttPublisherListener listener;
	
	public void doCreate(String broker, String instrument, String feature, Integer floor, Integer ceil, Long delay) throws Exception {
		listener.doCreate(broker, instrument, feature, floor, ceil, delay);
	}
	
	public void doDelete(String uuid) throws Exception {
		listener.doDelete(uuid);
	}
	
	public MqttPublisher doRetrieve(String uuid) {
		return listener.doRetrieve(uuid);
	}
	
	public Integer doSize() {
		return listener.doSize();
	}
	
	public List<MqttPublisher> doRetrieve(int offset, int length) {
		return listener.doRetrieve(offset, length);
	}
	
	public void init() throws ServletException {
		super.init();
		listener = (MqttPublisherListener) this.getServletContext().getAttribute("mqtt-publisher-listener");
	}
	
}
