package com.redhat.demo.iot.controller.cep.data;

public class CompareToData {
	
	private int deviceID;
	private int deviceType;
	
	public CompareToData() {
		
	}
	
	public CompareToData( int devID, int dtype ) {
		this.deviceID = devID;
		this.deviceType = dtype;
	}

	public int getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	
	

}
