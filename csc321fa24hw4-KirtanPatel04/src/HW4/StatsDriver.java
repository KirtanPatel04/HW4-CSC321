package HW4;

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Driver class that generates stats on various data structures for storing a dictionary.
 * 
 * @Author: Sherri Weitl Harms and Dave Reed
 * @Modified by: Kirtan Patel
 * @Date: 11/02/2024
 * @Version: 10/17/24  
 */
public class StatsDriver {
    public static void main(String[] args) throws java.io.FileNotFoundException {

        // Test ArrayList, LinkedList, and TreeSet
        ArrayList<String> wordsAL = new ArrayList<>();
        runMe(wordsAL, "dictionary.txt");

        LinkedList<String> wordsLinked = new LinkedList<>();
        runMe(wordsLinked, "dictionary.txt");

        TreeSet<String> wordsTree = new TreeSet<>();
        runMe(wordsTree, "dictionary.txt");

        // Test Trie
        Trie trie = new Trie();
        runMeTrie(trie, "dictionary.txt");
    }

    // Method for ArrayList, LinkedList, and TreeSet
    public static void runMe(Collection<String> words, String filename) throws java.io.FileNotFoundException {
        Scanner input = new Scanner(new File(filename));

        // Force garbage collection and measure total memory before loading words
        System.gc();
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // Load words into the data structure
        while (input.hasNext()) {
            words.add(input.next());
        }
        input.close();

        // Force garbage collection and measure total memory after loading words
        System.gc();
        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // Print data structure type and memory usage
        if (words instanceof ArrayList) System.out.println("ArrayList");
        else if (words instanceof LinkedList) System.out.println("LinkedList");
        else if (words instanceof TreeSet) System.out.println("TreeSet");
        else System.out.println("Collection");

        System.out.println("memory (MB): " + (afterMemory - beforeMemory) / 1e6);

        // Measure time for 10,000 search operations
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            words.contains("zyzzyvas");
            words.contains("zzzzzzzz");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("time (sec): " + (endTime - startTime) / 1e3);
    }

    // Method for Trie data structure
    public static void runMeTrie(Trie trie, String filename) throws java.io.FileNotFoundException {
        Scanner input = new Scanner(new File(filename));

        // Force garbage collection and measure total memory before loading words
        System.gc();
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // Load words into the Trie
        while (input.hasNext()) {
            trie.insert(input.next().toLowerCase()); // Insert each word into the Trie
        }
        input.close();

        // Force garbage collection and measure total memory after loading words
        System.gc();
        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("Trie");
        System.out.println("memory (MB): " + (afterMemory - beforeMemory) / 1e6);

        // Measure time for 10,000 search operations in the Trie
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            trie.containsWord("zyzzyvas");
            trie.containsWord("zzzzzzzz");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("time (sec): " + (endTime - startTime) / 1e3);
    }
}