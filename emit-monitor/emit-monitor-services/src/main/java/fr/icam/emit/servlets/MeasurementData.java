package fr.icam.emit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.github.jeromerocheteau.JdbcServlet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MeasurementData extends JdbcServlet {

	private static final long serialVersionUID = 201709041107005L;

	private MongoDatabase database;
	
	@Override
	public void init() throws ServletException {
		super.init();
		database = (MongoDatabase) this.getServletContext().getAttribute("mongodb-database");
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uuid = request.getParameter("uuid");
		MongoCollection<Document> collection = database.getCollection(uuid);
		MongoCursor<Document> cursor = collection.find().iterator();
		boolean flag = false;
		StringBuffer buffer = new StringBuffer(4096);
		buffer.append("[");
		while (cursor.hasNext()) {
			Document object = cursor.next();
			Long time = object.getLong("time");
			Double value = object.getDouble("value");
			String json = "{" + "\"time\":" + time + "," + "\"value\":" + value + "}";
	        if (flag) {
	        	buffer.append(",");
	        } else {
	            flag = true;
	        }
	        buffer.append(json);
		}
		buffer.append("]");
		response.getWriter().write(buffer.toString());
        response.getWriter().flush();
	}

}