package com.redhat.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;


public class Producer {
	
	 private ConnectionFactory factory;
	 private Connection connection;
	    private Session session;
	    private MessageProducer producer;
	 
	    public Producer(ConnectionFactory factory, String queueName) throws JMSException
	    {
	        this.factory = factory;
	        connection = factory.createConnection();
	        connection.start();
	        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        Destination destination = session.createQueue(queueName);
	        producer = session.createProducer(destination);
	    }
	 
	    public void run(String data) throws JMSException
	    {
            Message message = session.createTextMessage( data );
            
            System.out.println("Sending " + data);
            
            producer.send(message);
	    }
	 
	    public void close() throws JMSException
	    {
	        if (connection != null)
	        {
	            connection.close();
	        }
	    }
	 

}
