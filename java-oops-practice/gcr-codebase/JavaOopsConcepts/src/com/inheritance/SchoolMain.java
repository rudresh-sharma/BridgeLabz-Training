package com.inheritance;

public class SchoolMain {

    public static void main(String[] args) {

        Person p1 = new Teacher("Mr. Sharma", 40, "Mathematics");
        Person p2 = new Student("Rudresh", 20, "B.Tech");
        Person p3 = new Staff("Suresh", 35, "Administration");

        printPerson(p1);
        System.out.println();

        printPerson(p2);
        System.out.println();

        printPerson(p3);
    }

    // Polymorphism
    public static void printPerson(Person p) {
        p.displayInfo();

        if (p instanceof Teacher) {
            ((Teacher) p).displayRole();
        } 
        else if (p instanceof Student) {
            ((Student) p).displayRole();
        } 
        else if (p instanceof Staff) {
            ((Staff) p).displayRole();
        }
    }
}
