package com.redhat.demo.iot.controller.cep;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    		
    	SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyy HH:mm:ss SSS");
    	
    	DataSet	dataset = new DataSet();
    	dataset.setDeviceType(1);
    	dataset.setDeviceID(1);
    	dataset.setTimestamp( new Date() );
    	dataset.setCount(1);
    	dataset.setPayload(70);
    
    	System.out.println("dataset.getTimestamp() = " + dataset.getTimestamp());

    	System.out.println("format.format(dataset.getTimestamp()) = " + format.format(dataset.getTimestamp()) );
    	
    }    	
}
