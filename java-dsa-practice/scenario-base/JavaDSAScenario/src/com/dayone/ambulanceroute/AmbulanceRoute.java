package com.dayone.ambulanceroute;

public class AmbulanceRoute {
    private HospitalUnit head;

    public AmbulanceRoute() {
        head = null;
    }

    // Add unit to circular list
    public void addUnit(String name, boolean available) {
        HospitalUnit newUnit = new HospitalUnit(name, available);
        if (head == null) {
            head = newUnit;
            head.next = head; // circular
        } else {
            HospitalUnit temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newUnit;
            newUnit.next = head;
        }
        System.out.println("Added unit: " + name);
    }

    // Remove a unit (under maintenance)
    public void removeUnit(String name) {
        if (head == null) {
            System.out.println("No units in the route.");
            return;
        }

        HospitalUnit curr = head;
        HospitalUnit prev = null;

        do {
            if (curr.name.equalsIgnoreCase(name)) {
                if (prev != null) {
                    prev.next = curr.next;
                    if (curr == head) head = curr.next;
                } else { // removing head
                    HospitalUnit last = head;
                    while (last.next != head) last = last.next;
                    if (head.next == head) { // only one unit
                        head = null;
                    } else {
                        last.next = head.next;
                        head = head.next;
                    }
                }
                System.out.println("Removed unit: " + name);
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);

        System.out.println("Unit not found: " + name);
    }

    // Dispatch patient to nearest available unit
    public void dispatchPatient() {
        if (head == null) {
            System.out.println("No units available.");
            return;
        }

        HospitalUnit curr = head;
        do {
            if (curr.available) {
                System.out.println("Patient directed to: " + curr.name);
                return;
            }
            curr = curr.next;
        } while (curr != head);

        System.out.println("No available units. Please wait.");
    }

    // Show all units
    public void showUnits() {
        if (head == null) {
            System.out.println("No units in the route.");
            return;
        }

        System.out.println("Hospital Units in Circular Route:");
        HospitalUnit temp = head;
        do {
            System.out.println(" - " + temp);
            temp = temp.next;
        } while (temp != head);
    }
}
