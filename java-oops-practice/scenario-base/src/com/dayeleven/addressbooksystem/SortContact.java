package com.dayeleven.addressbooksystem;

import java.util.ArrayList;

public class SortContact {
	
	public static void sortContact(ArrayList<Contact> contacts) {
		
		for (int i = 0; i < contacts.size() - 1; i++) {
		    int minIndex = i;

		    for (int j = i + 1; j < contacts.size(); j++) {
		        if (contacts.get(j).getfName().compareToIgnoreCase(contacts.get(minIndex).getfName()) < 0) {
		            minIndex = j;
		        }
		    }

		    // swap i and minIndex
		    Contact temp = contacts.get(i);
		    contacts.set(i, contacts.get(minIndex));
		    contacts.set(minIndex, temp);
		}	
	}	
}
