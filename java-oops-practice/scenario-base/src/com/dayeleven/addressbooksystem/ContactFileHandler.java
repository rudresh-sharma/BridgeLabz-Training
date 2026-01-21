package com.dayeleven.addressbooksystem;

import java.io.*;
import java.util.ArrayList;

public class ContactFileHandler {

    // FULL PATH to your folder
    private static final String FILE_NAME = 
        "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-oops-practice\\scenario-base\\src\\com\\dayeleven\\addressbooksystem\\contacts.dat";

    // Save contacts to file
    public static void saveContacts(ArrayList<Contact> contacts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    // Load contacts from file
    @SuppressWarnings("unchecked")
    public static ArrayList<Contact> loadContacts() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Contact>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
