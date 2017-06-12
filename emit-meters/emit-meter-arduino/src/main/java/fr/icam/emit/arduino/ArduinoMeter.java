package fr.icam.emit.arduino;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ArduinoMeter  {
	
	private static final String EXCHANGE_NAME = "Arduino";	
	
	Consumer consumer;
	Channel channel;
	String tag;
	String result;
	boolean exist ;
	Connection connection;
	ConnectionFactory factory;
	
	public ArduinoMeter(){
		factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    factory.setUsername("EMIT");
		factory.setPassword("m3asur3");		
		exist = true;
	}

	public void doStart() throws Exception{
	// pour explications voir doc Rabbit MQ
    connection = factory.newConnection();
    channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
    String queueName = channel.queueDeclare().getQueue();
    channel.queueBind(queueName, EXCHANGE_NAME, "");
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    
   
    consumer = new DefaultConsumer(channel){
    String message = "";
    	// on écrase les méthodes de DefalltConsummer dont on a besoin pour récupérer les résultats.
    	  @Override
    	  public void handleDelivery(String consumerTag, Envelope envelope,
    	                             AMQP.BasicProperties properties, byte[] body)
    	      throws IOException {
    	    message =  message + new String(body, "UTF-8")+"\r\n";
    	    //System.out.println(" [x] Received '" + message + "'");
    	  }
    	  
    	  @Override
    	  public String toString(){
    		  
    		  return message;
    	  }
    	  
    	};
    	
    	tag = channel.basicConsume(queueName, true, consumer);
	}
	
	public void doStop() throws IOException{
    	 
    	
    	//String result = get_content();   	
    	result = consumer.toString();    	
    	channel.basicCancel(tag);
    	connection.close();
	}
	
	public void doRetrieve(OutputStream out) throws Exception{
		out.write(result.getBytes(Charset.forName("UTF-8")));
		result = "";
	}
	
	
    	
   }

