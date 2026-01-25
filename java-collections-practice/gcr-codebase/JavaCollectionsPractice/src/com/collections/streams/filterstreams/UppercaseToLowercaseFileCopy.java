package com.collections.streams.filterstreams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UppercaseToLowercaseFileCopy {
	public static void main(String[] args) {
		String sourceFile = "samplefiles/source.txt";
		String destinationFile = "samplefiles/lowercase.txt";

		// Try-with-resources ensures streams are closed properly
		try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
				BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile))) {

			String line;

			// Read file line by line
			while ((line = reader.readLine()) != null) {

				// Convert uppercase to lowercase
				writer.write(line.toLowerCase());
				writer.newLine();
			}

			System.out.println("File converted to lowercase successfully.");

		} catch (IOException e) {
			System.out.println("Error occurred: " + e.getMessage());
		}
	}
}