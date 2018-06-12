package fr.icam.emit.services.topics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.paho.client.mqttv3.MqttTopic;

import com.github.jeromerocheteau.JdbcServlet;

public class Creator extends JdbcServlet {

	private static final long serialVersionUID = 2017102310952007L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String topic = this.doCheck(request.getParameter("topic"));
		this.doCreate(request, response, topic, topic);
	}

	private String doCheck(String topic) {
		if (topic.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
			return this.doCheck(topic.substring(1));
		} else {
			return topic;
		}
	}

	private boolean doCheck(HttpServletRequest request, HttpServletResponse response, String topic) throws ServletException, IOException {
		request.setAttribute("topic", topic);
		this.doCall(request, response, "topic-item");
		Boolean found = (Boolean) request.getAttribute("found");
		if (found) {
			return true;
		} else {
			return false;
		}
	}

	private void doCreate(HttpServletRequest request, HttpServletResponse response, String topic, String current) throws ServletException, IOException {
		if (this.doCheck(request, response, current)) {
			return;
		} else {
			int index = current.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR);
			if (index >= 0) {
				String prefix = current.substring(0, index);
				String suffix = current.substring(index + 1);
				this.doCreate(request, response, topic, prefix);
				this.doCreate(request, response, current, prefix, suffix, topic.equals(current));
			} else {
				this.doCreate(request, response, current, null, current, topic.equals(current));
			}
		}
	}

	private void doCreate(HttpServletRequest request, HttpServletResponse response, String name, String prefix, String suffix, Boolean leaf) throws ServletException, IOException {
		request.setAttribute("name", name);
		request.setAttribute("prefix", prefix);
		request.setAttribute("suffix", suffix);
		request.setAttribute("leaf", leaf);
		this.doCall(request, response, "topic-insert");
		Integer count = (Integer) request.getAttribute("count");
		if (count > 0) {
			return;
		} else {
			throw new ServletException("Topic not created: " + count);
		}
	}

}
