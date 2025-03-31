package com.bridgelabz.streams.userinput;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

// Class to read user input from console and write it into a file
public class ReadWriteUserInput {
    public static void main(String[] args) {
        // Create a variable to store destination file path
        String destinationPath = "src/main/java/com/bridgelabz/streams/userinput/ReadWriteUserInput.txt";

        // Use try-catch to handle exception
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            FileWriter fw = new FileWriter(destinationPath)) {
            System.out.print("Enter your name: ");
            String name = br.readLine();

            System.out.print("Enter your age: ");
            int age = Integer.parseInt(br.readLine());

            System.out.print("Enter your favorite programming language: ");
            String language = br.readLine();

            fw.write("Name: " + name + "\n");
            fw.write("Age: " + age + "\n");
            fw.write("Favorite programming language: " + language + "\n");

            System.out.println("User data successfully saved to " + destinationPath);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
