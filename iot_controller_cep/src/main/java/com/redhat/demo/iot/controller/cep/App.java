package com.redhat.demo.iot.controller.cep;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.redhat.demo.iot.controller.cep.data.DataSet;


/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger log = Logger.getLogger(CepServer.class.getName());
	
	public static String brokerURL = "tcp://localhost:61616";
	public static String sourceQueueName = "message.to.cep";
	public static String targetQueueName = "message.to.datacenter";
	 
    public static void main( String[] args ) throws Exception
    {
    	String	messageFromQueue;
    		
		Consumer consumer = new Consumer(sourceQueueName, brokerURL);
		Producer producer = new Producer(targetQueueName, brokerURL);
		CepServer cepServer = new CepServer();
		
		while ( 1 ==1 ) {
			messageFromQueue = consumer.run();
			
            log.info("Received message with content " + messageFromQueue);

            // Convert TextMessage to DataSet via jaxb unmarshalling
            JAXBContext jaxbContext = JAXBContext.newInstance(DataSet.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader( messageFromQueue );
            DataSet event = (DataSet) unmarshaller.unmarshal(reader);
            
            cepServer.insert( event);
         
            log.info("Message.required after CEP = " + event.getRequired());
            
            // Forward received message to queue defined as 'replyToQueue'
            // but only if CEP tells us to do so!
            if ( event.getRequired() == 1)
            	producer.run(messageFromQueue);

            
		}
		
    }
}
