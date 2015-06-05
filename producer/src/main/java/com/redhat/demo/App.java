package com.redhat.demo;

import java.io.StringWriter;

import javax.jms.ConnectionFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.redhat.demo.iot_controller_data.DataSet;
import com.redhat.demo.iot_controller_data.DummyDataGenerator;
import com.redhat.demo.iot_controller_data.TemperatureDataSet;


/**
 * Hello world!
 *
 */
public class App 
{
	public static String brokerURL = "tcp://localhost:61616";
	
	// Default Values for message producer
    private static final String DEFAULT_DEVICETYPE   = "1";
    private static final String DEFAULT_DEVICEID     = "1";
    private static final String DEFAULT_INITIALVALUE = "70";

	 
    public static void main( String[] args ) throws Exception
    {
    	
    	com.redhat.demo.iot_controller_data.DummyDataGenerator dummy = null;
        
        // setup the connection to ActiveMQ
    	ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", brokerURL);
    	
    	// Create data to send
        dummy = new DummyDataGenerator();
        
        String devType = System.getProperty("DeviceType", DEFAULT_DEVICETYPE);
        String devID = System.getProperty("DeviceID", DEFAULT_DEVICEID);
        int initialValue = Integer.parseInt(System.getProperty("initialValue", DEFAULT_INITIALVALUE));
        
        dummy.createInitialDataSet(devType, devID, initialValue); 

        // Transform DataSet to XML
        JAXBContext jaxbContext = JAXBContext.newInstance(TemperatureDataSet.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
 
		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
 
		jaxbMarshaller.marshal(dummy.getDataSet(), sw);
		
        Producer producer = new Producer(factory, "message.receive");
        producer.run( sw.toString() );
        
        producer.close();
    }
}
