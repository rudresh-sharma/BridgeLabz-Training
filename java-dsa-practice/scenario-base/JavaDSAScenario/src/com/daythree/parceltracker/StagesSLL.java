package com.daythree.parceltracker;

public class StagesSLL {
	
	Node head = null;
	Node pointer = null;;
	Node tail = null;
	
	
	
	void add(String stage) {
		Node temp = new Node(stage);
		
		if(head == null) {
			head = temp;
			pointer = head;
			
		}
		else {
			tail.next = temp;
		}
		
		tail = temp;
		
		
	}
	
	
	void displayParcelStages() {
		
		Node temp = head;
		
		while(temp != pointer) {
			System.out.println(temp.stage + " ");
			temp = temp.next;
		}
		
		System.out.println(temp.stage);
		
	
	}
	
	
	void addCheckPoint(String checkPoint) {
		Node temp = new Node(checkPoint);
		
		Node t = head;
		
		while(t != pointer) {
			t = t.next;
		}
		
		temp.next = pointer.next;
		t.next = temp;
		
		pointer = temp;
	}
	
	
	void markAsLost() {
		pointer = null;
		System.out.println("Item is lost");
		System.exit(0);
	}
	
	
	void moveNextStage() {
		pointer = pointer.next;
	}

	public Node getHead() {
		return head;
	}


	public void setHead(Node head) {
		this.head = head;
	}


	public Node getPointer() {
		return pointer;
	}


	public void setPointer(Node pointer) {
		this.pointer = pointer;
	}
	
	
	
	
	
}
