package com.bridgelabz.streams.pipedstream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;

// class for writer thread
class WriterThread extends Thread {
    // Attribute
    private final PipedOutputStream pos;

    // Constructor
    WriterThread(PipedOutputStream pos) {
        this.pos = pos;
    }

    // method to run thread
    public void run() {
        try {
            String message = "Sample of writer thread";
            pos.write(message.getBytes(StandardCharsets.UTF_8));
            pos.close();
        } catch (IOException e) {
            System.out.println("WriterThread error: " + e.getMessage());
        }
    }
}

// Class for reader thread
class ReaderThread extends Thread {
    // Attribute
    private final PipedInputStream pis;

    // Constructor
    ReaderThread(PipedInputStream pis) {
        this.pis = pis;
    }

    // method to run reader thread
    public void run() {
        try {
            int data;
            while((data=pis.read())!=-1) {
                System.out.print((char) data);
            }
            pis.close();
        } catch (IOException e) {
            System.out.println("ReaderThread error: " + e.getMessage());
        }
    }
}

// Class to manage file writing and reading by separate threads
public class InterThreadCommunication {
    public static void main(String[] args) {
        // Use try-catch to handle exceptions
        try {
            PipedOutputStream pos = new PipedOutputStream();
            PipedInputStream pis = new PipedInputStream(pos);

            WriterThread writer = new WriterThread(pos);
            ReaderThread reader = new ReaderThread(pis);

            writer.start();
            reader.start();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
//Sample of writer thread