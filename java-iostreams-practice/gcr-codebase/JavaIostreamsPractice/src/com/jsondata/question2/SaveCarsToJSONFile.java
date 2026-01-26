package com.jsondata.question2;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SaveCarsToJSONFile {
    public static void main(String[] args) {
        try {
            // Create multiple Car objects
            Car car1 = new Car("Toyota", "Camry", 2026);
            Car car2 = new Car("Honda", "Civic", 2025);
            Car car3 = new Car("Ford", "Mustang", 2024);
            Car car4 = new Car("Tesla", "Model S", 2026);
            Car car5 = new Car("BMW", "X5", 2023);

            // Add cars to a list
            List<Car> cars = new ArrayList<>();
            cars.add(car1);
            cars.add(car2);
            cars.add(car3);
            cars.add(car4);
            cars.add(car5);

            // Create ObjectMapper
            ObjectMapper mapper = new ObjectMapper();

            // ✅ Specify full path for the JSON file
            String filePath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\jsondata\\question2\\cars.json";
            File file = new File(filePath);

            // Save JSON array to the file
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, cars);

            System.out.println("JSON file saved at: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
