package com.bridgelabz.streams.bytearraystream;

import java.io.*;
import java.nio.file.Files;

// Class to convert image to byte array and then write back to another image file
public class ImageToByteArray {
    public static void main(String[] args) {
        // Paths to source and destination images
        String sourceImage = "src/main/java/com/bridgelabz/streams/bytearraystream/ImageToByteSource.jpeg";
        String destinationImage = "src/main/java/com/bridgelabz/streams/bytearraystream/ImageToByteDestination.jpeg";

        // Use try-catch to handle exceptions
        try {
            // Convert image to byteArray
            byte[] byteArray = convertToByteArray(sourceImage);

            // Write byteArray to destination image
            writeToImage(byteArray, destinationImage);

            System.out.println("Image successfully copied from " + sourceImage.split("/")[6] + " to " + destinationImage.split("/")[6]);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // method to convert image to byteArray
    public static byte[] convertToByteArray(String sourceImage) throws IOException {
        File file = new File(sourceImage);
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            Files.copy(file.toPath(), bos);
            return bos.toByteArray();
        }
    }

    // method to convert byteArray to image
    public static void writeToImage(byte[] byteArray, String destinationImage) throws IOException {
        try(ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
            FileOutputStream fos = new FileOutputStream(destinationImage)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while((bytesRead=bis.read(buffer))!=-1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}
