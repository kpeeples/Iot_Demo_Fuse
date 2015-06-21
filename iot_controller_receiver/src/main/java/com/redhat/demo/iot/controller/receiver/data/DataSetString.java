package com.redhat.demo.iot.controller.receiver.data;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@XmlRootElement(name = "dataSet")
@XmlType(propOrder = { "timestamp", "deviceType", "deviceID", "count", "payload","required" })
//@CsvRecord(separator = ",", skipFirstLine = true, crlf = "UNIX")
@CsvRecord(separator = ",")
public class DataSetString {
	@DataField(pos = 1, required = true) 
	private String	deviceType;
	@DataField(pos = 2, required = true) 
	private String	deviceID;
	@DataField(pos = 3, required = true) 
	private	String	payload;
	@DataField(pos = 4, required = true)
	private String	timestamp;
	@DataField(pos = 5, required = true)
	private String	count;
	@DataField(pos = 6)
	private String	required;
	
	public DataSetString()
	{
		this.timestamp 	= "";
		this.deviceType = "";
		this.deviceID	= "";
		this.count		= "";
		this.payload	= "";
		this.required	= "";
	}
	
	public DataSetString(String time, String devType, String devID, String count, String pay, String required)
	{
		this.timestamp 	= time;
		this.deviceType = devType;
		this.deviceID	= devID;
		this.count		= count;
		this.payload	= pay;
		this.required	= required;
	}

	/**
	 * @return the required
	 */
	public String getRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(String required) {
		this.required = required;
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
	public String getPayload() {
		return payload;
	}

	/**
	 * @param payload the payload to set
	 */
	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	
	
}
	