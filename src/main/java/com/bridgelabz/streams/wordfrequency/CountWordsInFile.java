package com.bridgelabz.streams.wordfrequency;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Class to count the frequency of words in a file and print the top 5 words
public class CountWordsInFile {
    public static void main(String[] args) {
        // Variable to store file path
        String filePath = "src/main/java/com/bridgelabz/streams/wordfrequency/wordFile.txt";

        // Use try-catch to handle exceptions
        try {
            // write content in the file
            writeFile(filePath);

            // read words from file and display top 5 words
            readFile(filePath);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // method to create file
    public static void writeFile(String filePath) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("This is a sample text file. This file contains words that repeat.");
            bw.newLine();
            bw.write("Word frequency is counted in this file.");
            bw.newLine();
            bw.write("This sample demonstrates word count calculation.");
        }
    }

    // method to count words in file
    public static void readFile(String filePath) throws IOException {
       // Map to store word and frequency
        Map<String, Integer> frequencyMap = new HashMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line= br.readLine())!=null) {
                String[] words = line.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+");
                for(String word:words) {
                    if(!word.isEmpty()) {
                        frequencyMap.put(word, frequencyMap.getOrDefault(word,0)+1);
                    }
                }
            }
        }

        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(frequencyMap.entrySet());
        sortedWords.sort((a,b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("Top 5 most frequent words:");
        for(int i=0;i<Math.min(5, sortedWords.size()); i++) {
            System.out.println(sortedWords.get(i).getKey() + ": " + sortedWords.get(i).getValue());
        }
    }
}
// Sample Output ->
// Top 5 most frequent words:
// this: 4
// file: 3
// is: 2
// sample: 2
// word: 2