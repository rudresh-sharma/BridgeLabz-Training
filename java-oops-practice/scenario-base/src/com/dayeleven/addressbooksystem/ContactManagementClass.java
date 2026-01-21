package com.dayeleven.addressbooksystem;

import java.util.ArrayList;
import java.util.Scanner;

public class ContactManagementClass {

    public static void main(String[] args) {
    	ArrayList<Contact> contacts = ContactFileHandler.loadContacts();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            printMainMenu();
            choice = getIntInput(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    addContact(sc, contacts);
                    break;
                case 2:
                    editContact(sc, contacts);
                    break;
                case 3:
                    deleteContact(sc, contacts);
                    break;
                case 4:
                    searchContact(sc, contacts);
                    break;
                case 5:
                    displayContacts(contacts);
                    break;
                case 0:
                    ContactFileHandler.saveContacts(contacts);
                    System.out.println("All contacts saved. Exiting DigiContact. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        sc.close();
    }

    // ======= MAIN MENU =======
    private static void printMainMenu() {
        System.out.println("======================================");
        System.out.println("      Welcome to DigiContact!!        ");
        System.out.println("1. Add a contact");
        System.out.println("2. Edit an existing contact");
        System.out.println("3. Delete a contact");
        System.out.println("4. Search a contact");
        System.out.println("5. Display all contacts alphabetically");
        System.out.println("0. Exit");
        System.out.println("======================================");
    }

    // ======= ADD CONTACT =======
    private static void addContact(Scanner sc, ArrayList<Contact> contacts) {
        System.out.println("----- Add New Contact -----");

        String[] name = getValidFullName(sc);
        String firstName = name[0];
        String lastName = name[1];

        String phoneNo = getValidPhoneNumber(sc);
        String email = getValidEmail(sc);
        String city = getValidCityOrState(sc, "City");
        String state = getValidCityOrState(sc, "State");

        Address address = new Address(city, state);
        Contact newContact = new Contact(firstName, lastName, phoneNo, email, address);

        if (isDuplicate(contacts, newContact)) {
            System.out.println("This contact already exists. Cannot add duplicate.");
        } else {
            contacts.add(newContact);
            ContactFileHandler.saveContacts(contacts);
            System.out.println("Contact added successfully!");
        }
    }

    // ======= EDIT CONTACT =======
    private static void editContact(Scanner sc, ArrayList<Contact> contacts) {
        System.out.println("----- Edit Contact -----");
        String[] name = getValidFullName(sc);
        String firstName = name[0];
        String lastName = name[1];

        ArrayList<Contact> matchedContacts = findContactsByName(contacts, firstName, lastName);

        if (matchedContacts.isEmpty()) {
            System.out.println("No contact found with that name.");
            return;
        }

        Contact toEdit = selectContactFromList(sc, matchedContacts);

        boolean editing = true;
        while (editing) {
            System.out.println("What do you want to edit?");
            System.out.println("1. Name");
            System.out.println("2. Phone Number");
            System.out.println("3. Email");
            System.out.println("4. Address");
            System.out.println("0. Finish editing");

            int editChoice = getIntInput(sc, "Enter choice: ");

            switch (editChoice) {
                case 1:
                    String[] newName = getValidFullName(sc);
                    toEdit.setfName(newName[0]);
                    toEdit.setLname(newName[1]);
                    System.out.println("Name updated successfully!");
                    break;
                case 2:
                    String newPhone = getValidPhoneNumber(sc);
                    toEdit.setPhoneNo(newPhone);
                    System.out.println("Phone number updated successfully!");
                    break;
                case 3:
                    String newEmail = getValidEmail(sc);
                    toEdit.setEmail(newEmail);
                    System.out.println("Email updated successfully!");
                    break;
                case 4:
                    String newCity = getValidCityOrState(sc, "City");
                    String newState = getValidCityOrState(sc, "State");
                    toEdit.setAddress(new Address(newCity, newState));
                    System.out.println("Address updated successfully!");
                    break;
                case 0:
                    editing = false;
                    System.out.println("Finished editing contact.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            
            ContactFileHandler.saveContacts(contacts);

        }
    }

    // ======= DELETE CONTACT =======
    private static void deleteContact(Scanner sc, ArrayList<Contact> contacts) {
        System.out.println("----- Delete Contact -----");
        String[] name = getValidFullName(sc);
        String firstName = name[0];
        String lastName = name[1];

        ArrayList<Contact> matchedContacts = findContactsByName(contacts, firstName, lastName);

        if (matchedContacts.isEmpty()) {
            System.out.println("No contact found with that name.");
            return;
        }

        Contact toDelete = selectContactFromList(sc, matchedContacts);

        System.out.print("Are you sure you want to delete this contact? (Y/N): ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            contacts.remove(toDelete);
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
        
        ContactFileHandler.saveContacts(contacts);

    }

    // ======= SEARCH CONTACT =======
    private static void searchContact(Scanner sc, ArrayList<Contact> contacts) {
        System.out.println("----- Search Contact -----");
        System.out.println("Search by:");
        System.out.println("1. City");
        System.out.println("2. State");
        int searchChoice = getIntInput(sc, "Enter choice: ");

        switch (searchChoice) {
            case 1:
                String city = getValidCityOrState(sc, "City");
                printContactsByCity(contacts, city);
                break;
            case 2:
                String state = getValidCityOrState(sc, "State");
                printContactsByState(contacts, state);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // ======= DISPLAY CONTACTS =======
    private static void displayContacts(ArrayList<Contact> contacts) {
        System.out.println("----- All Contacts (Sorted Alphabetically) -----");
        SortContact.sortContact(contacts);

        System.out.printf("%-15s %-20s %-15s %-25s %-15s %-15s\n",
                "First Name", "Last Name", "Phone", "Email", "City", "State");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        for (Contact c : contacts) {
            System.out.printf("%-15s %-20s %-15s %-25s %-15s %-15s\n",
                    c.getfName(), c.getLname(), c.getPhoneNo(), c.getEmail(),
                    c.getAddress().getCity(), c.getAddress().getState());
        }
        System.out.println();
    }

    // ======= HELPER METHODS =======
    private static String[] getValidFullName(Scanner sc) {
        while (true) {
            System.out.print("Enter full name (First Last): ");
            String fullName = sc.nextLine().trim();
            String[] parts = fullName.split("\\s+");
            if (parts.length < 2) {
                System.out.println("Invalid input. Enter at least first and last name.");
                continue;
            }
            boolean valid = true;
            for (String s : parts) {
                if (!s.matches("[A-Za-z]+")) {
                    valid = false;
                    break;
                }
            }
            if (!valid) {
                System.out.println("Name should contain only letters.");
                continue;
            }
            String firstName = parts[0];
            String lastName = "";
            for (int i = 1; i < parts.length; i++) lastName += parts[i] + " ";
            lastName = lastName.trim();
            return new String[]{firstName, lastName};
        }
    }

    private static String getValidPhoneNumber(Scanner sc) {
        while (true) {
            System.out.print("Enter phone number: ");
            String phone = sc.nextLine().trim();
            if (phone.matches("[0-9]{10}")) return phone;
            System.out.println("Invalid phone number. Must be 10 digits.");
        }
    }

    private static String getValidEmail(Scanner sc) {
        while (true) {
            System.out.print("Enter email: ");
            String email = sc.nextLine().trim();
            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) return email;
            System.out.println("Invalid email format.");
        }
    }

    private static String getValidCityOrState(Scanner sc, String field) {
        while (true) {
            System.out.print("Enter " + field + ": ");
            String value = sc.nextLine().trim();
            if (value.matches("[A-Za-z ]+")) return value;
            System.out.println(field + " must contain only letters.");
        }
    }

    private static boolean isDuplicate(ArrayList<Contact> contacts, Contact c) {
        for (Contact existing : contacts) {
            if (existing.getfName().equalsIgnoreCase(c.getfName()) &&
                    existing.getLname().equalsIgnoreCase(c.getLname()) &&
                    existing.getPhoneNo().equals(c.getPhoneNo())) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<Contact> findContactsByName(ArrayList<Contact> contacts, String firstName, String lastName) {
        ArrayList<Contact> matched = new ArrayList<>();
        for (Contact c : contacts) {
            if (c.getfName().equalsIgnoreCase(firstName) &&
                    c.getLname().equalsIgnoreCase(lastName)) {
                matched.add(c);
            }
        }
        return matched;
    }

    private static Contact selectContactFromList(Scanner sc, ArrayList<Contact> matchedContacts) {
        if (matchedContacts.size() == 1) return matchedContacts.get(0);

        System.out.println("Multiple contacts found:");
        for (int i = 0; i < matchedContacts.size(); i++) {
            Contact c = matchedContacts.get(i);
            System.out.printf("%d. %s %s, Phone: %s\n", i + 1, c.getfName(), c.getLname(), c.getPhoneNo());
        }

        int index;
        while (true) {
            index = getIntInput(sc, "Select contact number: ") - 1;
            if (index >= 0 && index < matchedContacts.size()) break;
            System.out.println("Invalid selection. Try again.");
        }
        return matchedContacts.get(index);
    }

    private static void printContactsByCity(ArrayList<Contact> contacts, String city) {
        boolean found = false;
        for (Contact c : contacts) {
            if (c.getAddress().getCity().equalsIgnoreCase(city)) {
                c.printContactDetail();
                found = true;
            }
        }
        if (!found) System.out.println("No contacts found in city: " + city);
    }

    private static void printContactsByState(ArrayList<Contact> contacts, String state) {
        boolean found = false;
        for (Contact c : contacts) {
            if (c.getAddress().getState().equalsIgnoreCase(state)) {
                c.printContactDetail();
                found = true;
            }
        }
        if (!found) System.out.println("No contacts found in state: " + state);
    }

    private static int getIntInput(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                return n;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
}
