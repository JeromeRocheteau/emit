package fr.icam.emit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.icam.emit.listeners.MqttPublisher;

public class MqttPublisherStart extends MqttPublisherServlet {

	private static final long serialVersionUID = 201709140844004L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String uuid = request.getParameter("uuid");
			MqttPublisher publisher = this.doRetrieve(uuid);
			publisher.doStart();
			response.getWriter().write(Boolean.TRUE.toString());
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
}
