package fr.icam.emit.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoListener implements ServletContextListener {

	private MongoClient client;
		
	public void contextInitialized(ServletContextEvent sce) {
		String instance = sce.getServletContext().getInitParameter("mongodb-server");
		String database = sce.getServletContext().getInitParameter("mongodb-database");
		MongoClientURI uri = new MongoClientURI(instance);
		client = new MongoClient(uri);
		MongoDatabase db = client.getDatabase(database);
		sce.getServletContext().setAttribute("mongodb-database", db);
		
		System.out.println("[EMIT] MongoDD Database: " + db.getName());
	}

	public void contextDestroyed(ServletContextEvent sce) {
		client.close();
	}

}
