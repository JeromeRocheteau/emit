package fr.icam.emit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MqttPublisherCreate extends MqttPublisherServlet {

	private static final long serialVersionUID = 201709140844001L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String broker = request.getParameter("broker");
			String instrument = request.getParameter("instrument");
			String feature = request.getParameter("feature");
			Integer ceil = Integer.valueOf(request.getParameter("ceil"));
			Integer floor = Integer.valueOf(request.getParameter("floor"));
			Long delay = Long.valueOf(request.getParameter("delay"));
			this.doCreate(broker, instrument, feature, floor, ceil, delay);
			response.getWriter().write(Boolean.TRUE.toString());
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
}
