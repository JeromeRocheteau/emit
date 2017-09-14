package fr.icam.emit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.icam.emit.listeners.MqttPublisher;

public class MqttPublisherStatus extends MqttPublisherServlet {

	private static final long serialVersionUID = 201709140844003L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String uuid = request.getParameter("uuid");
			MqttPublisher publisher = this.doRetrieve(uuid);
			Boolean status = publisher.getStatus();
			response.getWriter().write(status.toString());
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
}
