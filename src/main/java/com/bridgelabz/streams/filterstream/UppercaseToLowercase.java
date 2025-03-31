package com.bridgelabz.streams.filterstream;

import java.io.*;

// Class to convert uppercase data of a text file to lowercase
public class UppercaseToLowercase {
    public static void main(String[] args) {
        // Create paths to store source and destination file
        String sourceFile = "src/main/java/com/bridgelabz/streams/filterstream/UppercaseToLowercaseSource.txt";
        String destinationFile ="src/main/java/com/bridgelabz/streams/filterstream/UppercaseToLowercaseDestination.txt";

        // Use try-catch to handle exceptions
        try {
            convertToLowercase(sourceFile, destinationFile);
            System.out.println("File converted successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // method to convert uppercase file to lowercase
    public static void convertToLowercase(String sourceFile, String destinationFile) throws  IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(sourceFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(destinationFile))) {
            String line;
            while((line=br.readLine())!=null) {
                bw.write(line.toLowerCase());
                bw.newLine();
            }
        }
    }
}
