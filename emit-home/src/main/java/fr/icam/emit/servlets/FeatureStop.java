package fr.icam.emit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;
import com.mongodb.client.MongoDatabase;

import fr.icam.emit.entities.Feature;
import fr.icam.emit.listeners.MqttListener;

public class FeatureStop extends JdbcServlet {

	private static final long serialVersionUID = 201708251500008L;

	private MongoDatabase database;
	
	@Override
	public void init() throws ServletException {
		super.init();
		database = (MongoDatabase) this.getServletContext().getAttribute("mongodb-database");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			this.doCall(request, response, "feature-item");
			Feature feature = (Feature) request.getAttribute("feature");
			MqttListener client = MqttListener.get(feature, database);
			client.doStop();
			this.doCall(request, response, "measurement-item");
			this.doCall(request, response, "measurement-update");
			this.doCall(request, response, "feature-disable");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
		
}