package fr.icam.emit.services.messages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.github.jeromerocheteau.JdbcServlet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import fr.icam.emit.listeners.MqttClientListener;

public class Sizer extends JdbcServlet {

	private static final long serialVersionUID = 201710201430001L;

	private MongoCollection<Document> collection;
	
	@Override
	public void init() throws ServletException {
		super.init();
		MqttClientListener listener = (MqttClientListener) this.getServletContext().getAttribute("mqtt-client-listener");
		collection = listener.getCollection();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String client = request.getParameter("client");
			Bson filter = Filters.eq("client", client);
			long count = collection.count(filter);
			this.doWrite(count, response.getWriter());
		} catch (Exception e) {
			throw new ServletException(e);
		}			
	}

}
