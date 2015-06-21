package com.redhat.demo.iot.datacenter.receiver;

import java.text.SimpleDateFormat;

public class DataToSQLBean {

	public String toSql(DataSet dataSet) 
	    {
	        StringBuilder sb = new StringBuilder();
	                
//	        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyy HH:mm:ss SSS");
	 
	        sb.append("INSERT INTO \"temperature\" ");
	        sb.append("(deviceid, timestamp, value, average) values (");
	        sb.append("'").append(dataSet.getDeviceID()).append("', ");
//	        sb.append("'").append( format.format(dataSet.getTimestamp() )).append("', ");
	        sb.append("'").append( dataSet.getTimestamp().trim() ).append("', ");
	        sb.append("'").append(dataSet.getPayload()).append("', ");
	        sb.append("'").append(dataSet.getAverage()).append("') ");
	        
	        System.out.println(sb.toString());
	        
	        return sb.toString();
	    }
	    
}
