package fr.icam.emit.servlets;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.client.MongoDatabase;

import fr.icam.emit.listeners.MqttListener;

public class FeatureStart extends HttpServlet {

	private static final long serialVersionUID = 201708251500007L;

	private MongoDatabase database;
	
	@Override
	public void init() throws ServletException {
		super.init();
		database = (MongoDatabase) this.getServletContext().getAttribute("mongodb-database");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			String uri = request.getParameter("uri");
			String topic = request.getParameter("topic");
			String uuid = UUID.randomUUID().toString();
			request.setAttribute("uuid", uuid);
			MqttListener.start(id, uri, uuid, topic, database);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}