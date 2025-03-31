package com.bridgelabz.streams.readlargefile;

import java.io.*;
import java.util.Random;

// Class to read a large file line by line, and display lines containing "error" word
public class ReadLargeFile {
    public static void main(String[] args) {
        // variable to store the file path
        String filePath = "src/main/java/com/bridgelabz/streams/readlargefile/largeFile.txt";

        // Use try-catch to handle exception
        try {
            // write a large file of about 500_000 lines
            writeFile(filePath, 500_000);
            System.out.println("File created successfully, Reading now...");

            // read file using bufferReader
            readFile(filePath);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // method to write file
    public static void writeFile(String filePath, int lines) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            Random random = new Random();
            for(int i=1;i<=lines;i++) {
                String line = "Entry " + i + (random.nextInt(10)==0? "-Error detected":"- All good");
                bw.write(line);
                bw.newLine();
            }
        }
    }

    // method to read file
    public static void readFile(String filePath) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line=br.readLine())!=null) {
                if(line.toLowerCase().contains("error")) {
                    System.out.println(line);
                }
            }
        }
    }
}
// Sample Output ->
// File created successfully, Reading now...
// Entry 10-Error detected
// Entry 20-Error detected
// Entry 30-Error detected
// Entry 40-Error detected
// Entry 50-Error detected ...