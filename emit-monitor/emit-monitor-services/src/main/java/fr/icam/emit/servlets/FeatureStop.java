package fr.icam.emit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.icam.emit.listeners.MqttListener;

public class FeatureStop extends HttpServlet {

	private static final long serialVersionUID = 201708251500007L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			MqttListener.stop(id);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}