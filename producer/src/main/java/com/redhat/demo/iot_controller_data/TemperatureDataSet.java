package com.redhat.demo.iot_controller_data;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "dataSet")
@XmlType(propOrder = { "timestamp", "deviceType", "deviceID", "payload" })
public class TemperatureDataSet {
	private String	timestamp;
	private String	deviceType;
	private String	deviceID;	
	private	int		payload;
	
	public TemperatureDataSet()
	{
		this.timestamp 	= "";
		this.deviceType = "";
		this.deviceID	= "";
		this.payload	= 0;
	}
	
	public TemperatureDataSet(String time, String devType, String devID, int pay)
	{
		this.timestamp 	= time;
		this.deviceType = devType;
		this.deviceID	= devID;
		this.payload	= pay;
	}

	public String toXML()
	{
		String res = "";
			
		return res;
	}
	
	@Override
	public String toString()
	{
		String res="";
		
		res = this.getDeviceType() + ", " + this.getDeviceID() + ", " + this.getTimestamp() + ", " + this.getPayload();
		
		return res;
	}
	
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * @return the deviceID
	 */
	public String getDeviceID() {
		return deviceID;
	}

	/**
	 * @param deviceID the deviceID to set
	 */
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	/**
	 * @return the payload
	 */
	public int getPayload() {
		return payload;
	}

	/**
	 * @param payload the payload to set
	 */
	public void setPayload(int payload) {
		this.payload = payload;
	}
	
}
	