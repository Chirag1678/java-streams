package com.bridgelabz.streams.bufferstream;

import java.io.*;

// Class to read and write a text file using buffered streams
public class ReadWriteStream {
    public static void main(String[] args) {
        // Create variables to store source file, destination file using buffered, destination file using File streams
        String sourceFile = "src/main/java/com/bridgelabz/streams/bufferstream/ReadWriteBufferSource.txt";
        String destinationBuffered = "src/main/java/com/bridgelabz/streams/bufferstream/ReadWriteBufferDestination.txt";
        String destinationUnbuffered = "src/main/java/com/bridgelabz/streams/bufferstream/ReadWriteUnbufferedDestination.txt";

        // Create a source file of 10MB
        createFile(sourceFile, 10 * 1024 * 1024);

        // Copy file using buffered streams
        long bufferedTime = copyBufferedStreams(sourceFile, destinationBuffered);

        // Copy file using unbuffered steams
        long unbufferedTime = copyUnbufferedStreams(sourceFile, destinationUnbuffered);

        // Display the result
        System.out.println("Buffered Stream Copy Time: " + (bufferedTime / 1e6) + " ms.");
        System.out.println("Unbuffered Stream Copy Time: " + (unbufferedTime / 1e6) + " ms");
    }

    // method to create file of specific size
    public static void createFile(String fileName, int size) {
        try(FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] buffer = new byte[4096];
            int iterations = size / buffer.length;
            for(int i=0;i<iterations;i++) {
                fos.write(buffer);
            }
            System.out.println("File created successfully.");
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    // method to copy file using buffered streams
    public static long copyBufferedStreams(String source, String destination) {
        long startTime = System.nanoTime();
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destination))) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while((bytesRead = bis.read(buffer))!=-1) {
                bos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println("Error copying file with buffered streams: " + e.getMessage());
        }
        return System.nanoTime()-startTime;
    }

    // method to copy file using unbuffered stream
    private static long copyUnbufferedStreams(String source, String destination) {
        long startTime = System.nanoTime();
        try(FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(destination)) {
            int byteContent;
            while((byteContent = fis.read())!=-1) {
                fos.write(byteContent);
            }
        } catch (IOException e) {
            System.out.println("Error copying file with unbuffered streams: " + e.getMessage());
        }
        return System.nanoTime()-startTime;
    }
}
// Sample Output ->
// File created successfully.
// Buffered Stream Copy Time: 15.520166 ms.
// Unbuffered Stream Copy Time: 21809.264833 ms