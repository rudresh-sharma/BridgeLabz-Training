
import java.util.Scanner;

public class AreaOfCircle {
    public static void main(String[] args) {
    
        System.out.print("Enter the radius:  ");

        Scanner sc = new Scanner(System.in);

        float radius = sc.nextFloat();

        float PI = (float) Math.PI;

        float area = PI * ((float) Math.pow(radius, 2));

        System.out.print("Area for given radius = ");

        System.out.println(area);

        sc.close();

    }
}
