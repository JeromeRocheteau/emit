package fr.icam.emit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.github.jeromerocheteau.JdbcServlet;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

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
		FindIterable<Document> it = collection.find();
        String content = JSON.serialize(it);
        response.getWriter().write(content);
        response.getWriter().flush();
	}

}