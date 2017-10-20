package fr.icam.emit.services.messages;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Binary;

import com.github.jeromerocheteau.JdbcServlet;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

import fr.icam.emit.entities.Message;
import fr.icam.emit.listeners.MqttClientListener;

public class Pager extends JdbcServlet {

	private static final long serialVersionUID = 201710201430002L;

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
			List<Message> items = new LinkedList<Message>();
			String client = request.getParameter("client");
			Integer offset = Integer.valueOf(request.getParameter("offset"));
			Integer length = Integer.valueOf(request.getParameter("length"));
			Bson filter = Filters.eq("client", client);
			FindIterable<Document> find = collection.find(filter);
			find.skip(offset);
			find.limit(length);
			find.sort(Sorts.descending("_id"));
			MongoCursor<Document> cursor = find.iterator();
			while (cursor.hasNext()) {
				Document document = cursor.next();
				Long issued = document.getLong("issued");
				String type = document.getString("type");
				String topic = document.getString("topic");
				Integer qos  = document.getInteger("qos");
				Boolean retained = document.getBoolean("retained");
				// Boolean duplicate = document.getBoolean("duplicate");
				byte[] payload = ((Binary) document.get("payload")).getData();
				Message item = new Message(issued, type, topic, qos, retained, payload);
				items.add(item);
			}
			this.doWrite(items, response.getWriter());
		} catch (Exception e) {
			throw new ServletException(e);
		}			
	}

}
