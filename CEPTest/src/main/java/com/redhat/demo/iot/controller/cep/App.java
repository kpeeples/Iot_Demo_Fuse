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
	 
    public static void main( String[] args ) throws Exception
    {
    	String	messageFromQueue;
    		
    	CepServer cepServer = new CepServer();
    	
    	System.out.println(" -> Inserting value 70 ");
    	
    	DataSet	dataset = new DataSet();
    	dataset.setDeviceType(1);
    	dataset.setDeviceID(1);
    	dataset.setTimestamp(String.valueOf( System.nanoTime() ));
    	dataset.setCount(1);
    	dataset.setPayload(70);
    	dataset = cepServer.insert(dataset);
    	System.out.println("Average = " + dataset.getAverage());
    	
    	System.out.println(" -> Inserting value 60 ");
    	dataset = new DataSet();
    	dataset.setDeviceType(1);
    	dataset.setDeviceID(1);
    	dataset.setCount(2);
    	dataset.setTimestamp(String.valueOf( System.nanoTime() ));
    	dataset.setPayload(60);
    	dataset = cepServer.insert(dataset);
    	System.out.println("Average = " + dataset.getAverage());

    	System.out.println(" -> Inserting value 60 ");
    	dataset = new DataSet();
    	dataset.setDeviceType(1);
    	dataset.setDeviceID(1);
    	dataset.setCount(3);
    	dataset.setTimestamp(String.valueOf( System.nanoTime() ));
    	dataset.setPayload(60);
    	cepServer.insert(dataset);
    	

    	System.out.println(" -> Inserting value 60 ");
    	dataset = new DataSet();
    	dataset.setDeviceType(1);
    	dataset.setDeviceID(1);
    	dataset.setCount(4);
    	dataset.setTimestamp(String.valueOf( System.nanoTime() ));
    	dataset.setPayload(60);
    	cepServer.insert(dataset);
    
    	System.out.println(" -> Inserting value 70 ");
    	dataset = new DataSet();
    	dataset.setDeviceType(1);
    	dataset.setDeviceID(1);
    	dataset.setCount(5);
    	dataset.setTimestamp(String.valueOf( System.nanoTime() ));
    	dataset.setPayload(70);
    	cepServer.insert(dataset);
    	
    	System.out.println(" New Device ");
    	System.out.println(" -> Inserting value 70 ");
    	dataset = new DataSet();
    	dataset.setDeviceType(1);
    	dataset.setDeviceID(2);
    	dataset.setCount(1);
    	dataset.setTimestamp(String.valueOf( System.nanoTime() ));
    	dataset.setPayload(70);
    	cepServer.insert(dataset);

    	System.out.println(" -> Inserting value 60 ");
    	dataset = new DataSet();
    	dataset.setDeviceType(1);
    	dataset.setDeviceID(2);
    	dataset.setCount(2);
    	dataset.setTimestamp(String.valueOf( System.nanoTime() ));
    	dataset.setPayload(60);
    	cepServer.insert(dataset);
    	
    	System.out.println(" Old Device ");
    	System.out.println(" -> Inserting value 70 ");
    	dataset = new DataSet();
    	dataset.setDeviceType(1);
    	dataset.setDeviceID(1);
    	dataset.setCount(6);
    	dataset.setTimestamp(String.valueOf( System.nanoTime() ));
    	dataset.setPayload(70);
    	cepServer.insert(dataset);
    	
    	System.out.println(" -> Inserting value 60 ");
    	dataset = new DataSet();
    	dataset.setDeviceType(1);
    	dataset.setDeviceID(1);
    	dataset.setCount(6);
    	dataset.setTimestamp(String.valueOf( System.nanoTime() ));
    	dataset.setPayload(60);
    	cepServer.insert(dataset);

    
    }    	
}
