/*

Write a Program to compute the volume of Earth in km^3 and miles^3
Hint => Volume of a Sphere is (4/3) * pi * r^3 and radius of earth is 6378 km
O/P => The volume of earth in cubic kilometers is ____ and cubic miles is ____


*/

// Program to calculate the volume of the Earth
public class VolumeOfEarth {

    // main method
    public static void main(String[] args) {

        // ----- input values -----
        // radius of the Earth in kilometers
        double radiusKm = 6378;

        // conversion value from kilometers to miles
        double kmToMiles = 0.621371;

        // ----- calculation part -----
        // converting radius into miles
        double radiusMiles = radiusKm * kmToMiles;

        // calculating volume in cubic kilometers
        double volumeInKm = (4.0 / 3) * Math.PI * radiusKm * radiusKm * radiusKm;

        // calculating volume in cubic miles
        double volumeInMiles = (4.0 / 3) * Math.PI * radiusMiles * radiusMiles * radiusMiles;

        // ----- output -----
        // printing the final volumes
        System.out.printf(
            "The volume of earth in cubic kilometers is %.2f and cubic miles is %.2f%n",
            volumeInKm, volumeInMiles
        );
    }
}

