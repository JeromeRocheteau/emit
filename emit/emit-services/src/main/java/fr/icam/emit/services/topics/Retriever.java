package fr.icam.emit.services.topics;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.github.jeromerocheteau.JdbcServlet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


public class Retriever extends JdbcServlet {

	private static final long serialVersionUID = 201806041640001L;

	private MongoCollection<Document> messages;
	
	@Override
	public void init() throws ServletException {
		super.init();
		MongoDatabase db = (MongoDatabase) this.getServletContext().getAttribute("mongodb-database");
		String collectionName = this.getServletContext().getInitParameter("mongodb-message-collection");
		messages = db.getCollection(collectionName);
	}

	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String client = request.getParameter("client");
			List<String> items = new LinkedList<String>();
			Bson filter = Filters.eq("client", client);
			MongoCursor<String> cursor = messages.distinct("topic", String.class).filter(filter).iterator();
			while (cursor.hasNext()) {
				String item = cursor.next();
				items.add(item);
			}
			this.doWrite(items, response.getWriter());
		} catch (Exception e) {
			throw new ServletException(e);
		}			
	}

}
