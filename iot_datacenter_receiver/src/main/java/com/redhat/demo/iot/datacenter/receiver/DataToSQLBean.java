package com.redhat.demo.iot.datacenter.receiver;

public class DataToSQLBean {

	public String toSql(DataSet dataSet) 
	    {
	        StringBuilder sb = new StringBuilder();
	 
	        sb.append("INSERT INTO \"temperature\" ");
	        sb.append("(deviceid, timestamp, value) values (");
	        sb.append("'").append(dataSet.getDeviceID()).append("', ");
	        sb.append("'").append(dataSet.getTimestamp()).append("', ");
	        sb.append("'").append(dataSet.getPayload()).append("') ");
	        
	        return sb.toString();
	    }
	    
}
