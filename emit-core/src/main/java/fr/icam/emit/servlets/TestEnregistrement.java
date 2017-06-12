package fr.icam.emit.servlets;

import javax.jms.DeliveryMode;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

import fr.icam.emit.analysis.File_handler;
import fr.icam.emit.analysis.InstrumentReader;
import fr.icam.emit.analysis.Serie;
import fr.icam.emit.api.Feature;
import fr.icam.emit.api.Meter;

public class TestEnregistrement extends JdbcServlet{
	/*
	 * 
	 */
	
	
	private static final long serialVersionUID = 2017041201530L;



	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String context = "";
		InitialContext ctx;
		try {
						
	       // get the initial context
	       ctx = new InitialContext();
	       //context = "Message published: " + ctx.getNameInNamespace();
	       response.getWriter().write("M du programme");                                               
	       // lookup the topic object
	       Topic topic = (Topic) ctx.lookup("topic/topic0");
	       response.getWriter().write("M du programme");                                                             
	       // lookup the topic connection factory
	       TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.
	           lookup("topic/connectionFactory");
	                                                                           
	       // create a topic connection
	       TopicConnection topicConn = connFactory.createTopicConnection();
	                                                                           
	       // create a topic session
	       TopicSession topicSession = topicConn.createTopicSession(false, 
	           Session.AUTO_ACKNOWLEDGE);
	       response.getWriter().write("M du programme");                                                               
	       // create a topic publisher
	       TopicPublisher topicPublisher = topicSession.createPublisher(topic);
	       topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	                                                                           
	       // create the "Hello World" message
	       TextMessage message = topicSession.createTextMessage();
	       message.setText("Hello World");
	                                                                           
	       // publish the messages
	       topicPublisher.publish(message);
	                                                                           
	       // print what we did
	       context = context +"Message published: " + message.getText();
	                                                                           
	       // close the topic connection
	       topicConn.close();
			
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		}
		response.getWriter().write(context);
		response.getWriter().write("fin du programme");
	
		
		
	}
	
	
	
	
	
	
	
	
}
