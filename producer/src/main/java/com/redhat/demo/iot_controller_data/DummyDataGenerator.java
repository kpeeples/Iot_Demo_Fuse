package com.redhat.demo.iot_controller_data;

import java.io.StringWriter;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
 
public class DummyDataGenerator {

	private DataSet tempSet;
	
	public void createInitialDataSet(int devType, int devID, int pay ) {
		tempSet = new DataSet();
		
		tempSet.setTimestamp( String.valueOf( System.nanoTime() ));
		tempSet.setDeviceType(devType);
		tempSet.setDeviceID(devID);
		tempSet.setPayload(pay);
		tempSet.setCount(0);
	}
	
	public void updateDataSet() {
		Random random = new Random();
		
		tempSet.setTimestamp( String.valueOf( System.nanoTime() ));
		
		int randValue = random.nextInt(1000);
		
		tempSet.setCount( tempSet.getCount() + 1 );
		
		if ( randValue <= 200 )
			tempSet.setPayload(tempSet.getPayload()+1);
		else if ( randValue >= 800 )
			tempSet.setPayload(tempSet.getPayload()-1);
	}
	
	public DataSet getDataSet(){
		return tempSet;
	}
	
	public String getDataSetXML() {
		return jaxbObjectToXML();
	}
	
	private String jaxbObjectToXML( ) {
		 
		StringWriter writer = new StringWriter();
        
        try {
            JAXBContext context = JAXBContext.newInstance(DataSet.class);
            
            Marshaller m = context.createMarshaller();
            
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            m.marshal(this.getDataSet(), writer);
           
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
        return writer.toString();
	}
	
}