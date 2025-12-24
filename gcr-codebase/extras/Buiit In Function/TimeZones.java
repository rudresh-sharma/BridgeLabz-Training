/*
Problem 1: Time Zones and ZonedDateTime Write a program that displays the current
time in different time zones:
➢ GMT (Greenwich Mean Time)
➢ IST (Indian Standard Time)
➢ PST (Pacific Standard Time)
Hint: Use ZonedDateTime and ZoneId to work with different time zones.


*/


import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeZones {

    public static void main(String[] args) {

        // tAKING INPUTS
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z");
        ZonedDateTime gmtTime = ZonedDateTime.now(ZoneId.of("GMT")); 
        ZonedDateTime istTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        ZonedDateTime pstTime = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

 	// Printing time according to gmt
        System.out.println("Current Time in Different Time Zones:");
        System.out.println("-------------------------------------");
        System.out.println("GMT : " + gmtTime.format(formatter));
        System.out.println("IST : " + istTime.format(formatter));
        System.out.println("PST : " + pstTime.format(formatter));
    }
}
