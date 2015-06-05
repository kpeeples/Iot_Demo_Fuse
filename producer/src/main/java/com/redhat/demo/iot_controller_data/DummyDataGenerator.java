package com.redhat.demo.iot_controller_data;

import java.io.StringWriter;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
 
public class DummyDataGenerator {

	private TemperatureDataSet tempSet;
	
	public void createInitialDataSet(String devType, String devID, int pay ) {
		tempSet = new TemperatureDataSet();
		
		tempSet.setTimestamp( String.valueOf( System.nanoTime() ));
		tempSet.setDeviceType(devType);
		tempSet.setDeviceID(devID);
		tempSet.setPayload(pay);
	}
	
	public void updateDataSet() {
		Random random = new Random();
		
		tempSet.setTimestamp( String.valueOf( System.nanoTime() ));
		
		int randValue = random.nextInt(1000);
		
		if ( randValue <= 10 )
			tempSet.setPayload(tempSet.getPayload()+1);
		else if ( randValue >= 990 )
			tempSet.setPayload(tempSet.getPayload()-1);
	}
	
	public TemperatureDataSet getDataSet(){
		return tempSet;
	}
	
	public String getDataSetXML() {
		return jaxbObjectToXML(getDataSet());
	}
	
	private String jaxbObjectToXML(TemperatureDataSet emp) {
		 
		StringWriter writer = new StringWriter();
        
        try {
            JAXBContext context = JAXBContext.newInstance(TemperatureDataSet.class);
            
            Marshaller m = context.createMarshaller();
            
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            m.marshal(emp, writer);
           
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
        return writer.toString();
	}
	
}