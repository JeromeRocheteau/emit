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

public class Search extends JdbcServlet {

	private static final long serialVersionUID = 201805121300001L;

	private MongoCollection<Document> messages;
	
	@Override
	public void init() throws ServletException {
		super.init();
		MqttClientListener listener = (MqttClientListener) this.getServletContext().getAttribute("mqtt-client-listener");
		messages = listener.getMessages();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			// System.out.println("[emit] GET " + System.currentTimeMillis());
			List<Message> items = new LinkedList<Message>();
			String client = request.getParameter("client");
			String started = request.getParameter("started");
			String stopped = request.getParameter("stopped");
			// System.out.println("[emit] PARAM " + System.currentTimeMillis());
			Bson filter = this.getFilter(client, started, stopped);
			if (filter != null) {
				// System.out.println("[emit] FILTER " + System.currentTimeMillis());
				FindIterable<Document> find = messages.find(filter);
				// System.out.println("[emit] FIND " + System.currentTimeMillis());
				find.sort(Sorts.descending("_id"));
				// System.out.println("[emit] SORT " + System.currentTimeMillis());
				MongoCursor<Document> cursor = find.iterator();
				while (cursor.hasNext()) {
					Document document = cursor.next();
					Long issued = document.getLong("issued");
					String mode = document.getString("mode");
					String topic = document.getString("topic");
					Integer qos  = document.getInteger("qos");
					Boolean retained = document.getBoolean("retained");
					// Boolean duplicate = document.getBoolean("duplicate");
					byte[] payload = ((Binary) document.get("payload")).getData();
					Message item = new Message(issued, mode, topic, qos, retained, payload);
					items.add(item);
				}
				// System.out.println("[emit] ITER " + System.currentTimeMillis());
			}
			this.doWrite(items, response.getWriter());
		} catch (Exception e) {
			throw new ServletException(e);
		}			
	}

	private Bson getFilter(String client, String started, String stopped) throws Exception {
		if (client == null) {
			throw new NullPointerException("missing HTTP string parameter 'client'");
		} else if (started == null) {
			throw new NullPointerException("missing HTTP number parameter 'started'");
		} else {
			Bson topicFilter = Filters.eq("client", client);
			Bson startedFilter = Filters.gte("issued", Long.valueOf(started));
			if (stopped == null) {
				return Filters.and(topicFilter, startedFilter);
			} else {
				Bson stoppedFilter = stopped == null ? null : Filters.lte("issued", Long.valueOf(stopped));
				return Filters.and(topicFilter, startedFilter, stoppedFilter);				
			}
		}
	}

}
