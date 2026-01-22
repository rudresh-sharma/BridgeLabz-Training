package com.daynine.tailorshop;

import java.time.LocalDate;
public class OrderData {
	private LocalDate deadline;
	private String OrderId;
	public OrderData( String orderId,LocalDate deadline) {
		super();
		this.deadline = deadline;
		OrderId = orderId;
	}
	
	
	
	public LocalDate getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	
	
	
}
