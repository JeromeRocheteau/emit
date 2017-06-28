package fr.icam.arduinommetter.arduinometter;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

public class MessageHandler {
	
	 private final static String QUEUE_NAME = "hello";
	 
	 private static final String EXCHANGE_NAME = "Arduino";
	 
	public MessageHandler(){
		
	}
	
	public void send(String message) throws IOException, TimeoutException{
		 //open connexion
		  ConnectionFactory factory = new ConnectionFactory();
		  factory.setHost("localhost");
		  factory.setUsername("EMIT");
		  factory.setPassword("m3asur3");
		  Connection connection = factory.newConnection();
		  Channel channel = connection.createChannel();
		  
		  channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		  
		  //oppen channel
		  channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());	  
		  
		  //System.out.println(" [x] Sent '" + message + "'");
		  
		  //close all
		  channel.close();
		  connection.close();
	}
	 

}
