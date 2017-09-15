package fr.icam.emit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MqttPublisherSize extends MqttPublisherServlet {

	private static final long serialVersionUID = 201709140844007L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			Integer size = this.doSize();
			response.getWriter().write(size.toString());
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
}
