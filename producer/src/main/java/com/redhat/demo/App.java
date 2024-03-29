package com.redhat.demo;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.redhat.demo.iot_controller_data.DummyDataGenerator;


/**
 * Hello world!
 *
 */
public class App 
{
	
	// Default Values for message producer
    private static final String	DEFAULT_DEVICETYPE   = "1";
    private static final String DEFAULT_DEVICEID     = "1";
    private static final String DEFAULT_INITIALVALUE = "70";
    private static final String DEFAULT_COUNT 		 = "1";
    private static final String DEFAULT_UNIT		 = "C";
    private static final String DEFAULT_WAIT		 = "1";
    private static final String DEFAULT_FORMAT		 = "XML";
    private static final String DEFAULT_RECEIVER	= "localhost";

	 
    public static void main( String[] args ) throws Exception
    {
    	
    	DummyDataGenerator dummy = null;
        
   	
    	// Create data to send
        dummy = new DummyDataGenerator();
        
        int devType 	 = Integer.parseInt( System.getProperty("deviceType", DEFAULT_DEVICETYPE));
        int devID		 = Integer.parseInt( System.getProperty("deviceID", DEFAULT_DEVICEID));
        int initialValue = Integer.parseInt(System.getProperty("initialValue", DEFAULT_INITIALVALUE));
        int count = Integer.parseInt(System.getProperty("count", DEFAULT_COUNT));
        int waitTime = Integer.parseInt(System.getProperty("waitTime", DEFAULT_WAIT));
        String messageFormat = System.getProperty("messageFormat",DEFAULT_FORMAT);
        String unit			 = System.getProperty("payloadUnit", DEFAULT_UNIT);
        String brokerURL = "tcp://" + System.getProperty("receiverURL",DEFAULT_RECEIVER) + ":61616" ;
        
        dummy.createInitialDataSet(devType, devID, initialValue, unit); 

        // setup the connection to ActiveMQ
    	ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", brokerURL);
        
        Producer producer = new Producer(factory, "message.receive" );
        
        System.out.println(dummy.getDataSetCSV());
        
        int counter = 0;
        while ( counter < count ) {
			
        	if ( messageFormat.equals("XML")) {
        		System.out.println("Sending as XML");
        		producer.run( dummy.getDataSetXML() );
        	} else {
        		System.out.println("Sending as CSV");
        		producer.run( dummy.getDataSetCSV() );
        	}
        		
        	
			dummy.updateDataSet();
			
			counter++;
			
			Thread.sleep ( waitTime * 1000 );
        }
		    
        producer.close();
    }
}
