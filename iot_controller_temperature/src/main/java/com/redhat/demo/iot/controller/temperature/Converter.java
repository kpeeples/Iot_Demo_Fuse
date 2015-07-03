package com.redhat.demo.iot.controller.temperature;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;

import com.redhat.demo.iot_controller_data.DataSet;

public class Converter {
	
	@Handler
	public DataSet f2c(Exchange exchange, DataSet input) {
		
		DataSet result = input;
		
		result.setPayload( round( (result.getPayload() - 32)/1.8, 2)  );
		
		return result;
	}

	
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

}
