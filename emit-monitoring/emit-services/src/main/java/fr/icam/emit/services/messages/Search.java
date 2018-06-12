package fr.icam.emit.services.messages;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import com.github.jeromerocheteau.JdbcServlet;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

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
			List<Map<String, Object>> items = new LinkedList<Map<String, Object>>();
			String topic = request.getParameter("topic");
			String started = request.getParameter("started");
			String stopped = request.getParameter("stopped");
			Bson filter = this.getFilter(topic, started, stopped);
			if (filter != null) {
				FindIterable<Document> find = messages.find(filter);
				find.sort(Sorts.ascending("issued"));
				MongoCursor<Document> cursor = find.iterator();
				while (cursor.hasNext()) {
					Map<String, Object> item = new TreeMap<String, Object>();
					Document document = cursor.next();
					item.put("issued", document.get("issued"));
					item.put("topic", document.get("topic"));
					item.put("value", document.get("value"));
					items.add(item);
				}
			}
			this.doWrite(items, response.getWriter());
		} catch (Exception e) {
			throw new ServletException(e);
		}			
	}

	private Bson getFilter(String topic, String started, String stopped) throws Exception {
		if (topic == null) {
			throw new NullPointerException("missing HTTP string parameter 'topic'");
		} else if (started == null) {
			throw new NullPointerException("missing HTTP number parameter 'started'");
		} else {
			MqttTopic.validate(topic, false);
			Bson topicFilter = Filters.eq("topic", topic);
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
