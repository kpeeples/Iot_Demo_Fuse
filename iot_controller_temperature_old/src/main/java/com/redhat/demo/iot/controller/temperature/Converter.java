package com.redhat.demo.iot.controller.temperature;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;

import com.redhat.demo.iot_controller_data.DataSet;

public class Converter {

	@Handler
	public DataSet fahrenheit2celsius(Exchange exchange, DataSet input ) {
		
		DataSet result = input;
		
		result.setPayload( (result.getPayload()-32) / 1.8 );
		
		return result;
	}
}
