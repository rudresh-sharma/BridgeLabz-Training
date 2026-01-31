package com.functionalinterfaces.implementinginterfaces.smartdevicecontrolinterface;

public class Lights implements OperateDevice{
	@Override
	public void turnOn() {
		System.out.println("Light is Turned On");
	}

	@Override
	public void turnOff() {
		System.out.println("Light is Turned Off");		
	}
}
