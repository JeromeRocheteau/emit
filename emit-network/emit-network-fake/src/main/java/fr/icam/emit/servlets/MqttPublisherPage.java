package fr.icam.emit.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.icam.emit.listeners.MqttPublisher;

public class MqttPublisherPage extends MqttPublisherServlet {

	private static final long serialVersionUID = 201709140844006L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			int offset = Integer.valueOf(request.getParameter("offset"));
			int length = Integer.valueOf(request.getParameter("length"));
			List<MqttPublisher> publishers = this.doRetrieve(offset, length);
			StringBuffer buffer = new StringBuffer(1024 * 124);
			buffer.append("[");
			boolean flag = false;
			for (MqttPublisher publisher : publishers) {
				if (flag) {
					buffer.append(",");
				} else {
					flag = true;
				}
				String uuid = publisher.getUuid();
				String broker = publisher.getBroker();
				String instrument = publisher.getInstrument();
				String feature = publisher.getFeature();
				Integer floor = publisher.getFloor();
				Integer ceil = publisher.getCeil();
				Long delay = publisher.getDelay();
				Boolean status = publisher.getStatus();
				String json = this.toJson(broker, uuid, instrument, feature, floor, ceil, delay, status);
				buffer.append(json);
			}
			buffer.append("]");
			response.getWriter().write(buffer.toString());
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private String toJson(String broker, String uuid, String instrument, String feature, Integer floor, Integer ceil, Long delay, Boolean status) {
		return "{"
				+ "\"broker\":\"" + broker + "\""
				+ ",\"uuid\":\"" + uuid + "\""
				+ ",\"topic\":\"" + instrument + "/" + feature + "\""
				+ ",\"instrument\":\"" + instrument + "\""
				+ ",\"feature\":\"" + feature + "\""
				+ ",\"floor\":" + floor
				+ ",\"ceil\":" + ceil
				+ ",\"delay\":" + delay
				+ ",\"status\":" + status
				+ "}";
	}
	
}
