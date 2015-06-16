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
	public static String brokerURL = "tcp://localhost:61616";
	
	
	// Default Values for message producer
    private static final String	DEFAULT_DEVICETYPE   = "1";
    private static final String DEFAULT_DEVICEID     = "1";
    private static final String DEFAULT_INITIALVALUE = "70";
    private static final String DEFAULT_COUNT 		 = "1";
    private static final String DEFAULT_WAIT		 = "1";

	 
    public static void main( String[] args ) throws Exception
    {
    	
    	com.redhat.demo.iot_controller_data.DummyDataGenerator dummy = null;
        
        // setup the connection to ActiveMQ
    	ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", brokerURL);
    	
    	// Create data to send
        dummy = new DummyDataGenerator();
        
        int devType 	 = Integer.parseInt( System.getProperty("deviceType", DEFAULT_DEVICETYPE));
        int devID		 = Integer.parseInt( System.getProperty("deviceID", DEFAULT_DEVICEID));
        int initialValue = Integer.parseInt(System.getProperty("initialValue", DEFAULT_INITIALVALUE));
        int count = Integer.parseInt(System.getProperty("count", DEFAULT_COUNT));
        int waitTime = Integer.parseInt(System.getProperty("waitTime", DEFAULT_WAIT));
        
        dummy.createInitialDataSet(devType, devID, initialValue); 

        Producer producer = new Producer(factory, "message.receive");
        
        int counter = 0;
        while ( counter < count ) {
			
			producer.run( dummy.getDataSetXML() );
		    
			dummy.updateDataSet();
			
			counter++;
			
			Thread.sleep ( waitTime * 1000 );
        }
		    
        producer.close();
    }
}
