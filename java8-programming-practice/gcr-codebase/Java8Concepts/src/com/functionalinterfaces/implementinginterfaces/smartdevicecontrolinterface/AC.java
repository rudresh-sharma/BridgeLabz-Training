package com.functionalinterfaces.implementinginterfaces.smartdevicecontrolinterface;

public class AC implements OperateDevice{
	@Override
	public void turnOn() {
		System.out.println("AC is Turned On");
	}

	@Override
	public void turnOff() {
		System.out.println("AC is Turned Off");		
	}
}
