package com.bridgelabz.streams.filestream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// Class to read and write file using FileInputStream and FileOutputStream
public class ReadWriteFile {
    public static void main(String[] args) {
        // Define variables to store the source and destination files path
        String sourceFile = "src/main/java/com/bridgelabz/streams/filestream/ReadWriteFileSource.txt";
        String destinationFile = "src/main/java/com/bridgelabz/streams/filestream/ReadWriteFileDestination.txt";

        // Use try-catch to handle exception
        try(FileInputStream fis = new FileInputStream(sourceFile);
            FileOutputStream fos = new FileOutputStream(destinationFile)) {
            int byteContent;
            while((byteContent=fis.read())!=-1) {
                fos.write(byteContent);
            }
            System.out.println("File Copied successfully.");
        } catch(FileNotFoundException e) {
            System.out.println("Source file not found: " + e.getMessage());
        } catch(IOException e) {
            System.out.println("An error occurred while processing file: " + e.getMessage());
        }
    }
}
// Sample output ->
//File Copied successfully.