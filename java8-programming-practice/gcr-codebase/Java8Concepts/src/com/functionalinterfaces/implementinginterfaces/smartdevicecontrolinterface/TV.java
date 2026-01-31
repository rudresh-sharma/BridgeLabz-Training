package com.functionalinterfaces.implementinginterfaces.smartdevicecontrolinterface;

public class TV implements OperateDevice{

	@Override
	public void turnOn() {
		System.out.println("TV is Turned On");
	}

	@Override
	public void turnOff() {
		System.out.println("TV is Turned Off");		
	}
	
	
}
