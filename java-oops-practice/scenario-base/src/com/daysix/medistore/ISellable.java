package com.daysix.medistore;

public interface ISellable {
	
	boolean checkExpiry();
	double sell(int quantity, Medicine m);
		
}
