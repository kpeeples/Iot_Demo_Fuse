package com.redhat.demo.iot.controller.cep;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
	
	public static String sourceBrokerURL = "tcp://iotcontrollertemperature:61616";
	public static String targetBrokerURL = "tcp://iotdatacenterreceiver:61616";
	public static String sourceQueueName = "message.to.cep";
	public static String targetQueueName = "message.to.datacenter";
	 
    public static void main( String[] args ) throws Exception
    {
    	String	messageFromQueue;
    		
    	System.out.println(" Check if remote AMQ-Broker are already available");
    	AMQTester tester = new AMQTester(); 
    	
    	while( tester.testAvailability( sourceBrokerURL ) == false ) {
    		System.out.println(" AMQ-Broker " + sourceBrokerURL + " not yet available ");
    		Thread.sleep(10000);
    	}
    	
    	while( tester.testAvailability( targetBrokerURL ) == false ) {
    		System.out.println(" AMQ-Broker " + targetBrokerURL + " not yet available ");
    		Thread.sleep(10000);
    	}
    	
		Consumer consumer = new Consumer(sourceQueueName, sourceBrokerURL);
		Producer producer = new Producer(targetQueueName, targetBrokerURL);
		CepServer cepServer = new CepServer();
		
		while ( 1 ==1 ) {
			messageFromQueue = consumer.run(10000);
									
			if ( messageFromQueue != null ) {
	
	            // Convert TextMessage to DataSet via jaxb unmarshalling
	            JAXBContext jaxbContext = JAXBContext.newInstance(DataSet.class);
	            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	
	            StringReader reader = new StringReader( messageFromQueue );
	            DataSet event = (DataSet) unmarshaller.unmarshal(reader);
	            
	            event = cepServer.insert( event);	 	            
	            
	            // Convert changed message back to XML
	            String result;
	            StringWriter sw = new StringWriter();
	            try {
	                JAXBContext dataSetContext = JAXBContext.newInstance(DataSet.class);
	                Marshaller dataSetMarshaller = dataSetContext.createMarshaller();
	                dataSetMarshaller.marshal(event, sw);
	                result = sw.toString();
	            } catch (JAXBException e) {
	                throw new RuntimeException(e);
	            }
	            
	            if ( event.getRequired() == 1)
	            	producer.run(result);
	            
			}
            
		}
		
    }
}
