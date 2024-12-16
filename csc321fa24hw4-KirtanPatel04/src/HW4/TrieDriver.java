package HW4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * TrieDriver class to interact with a Trie data structure.
 * This class loads words from a dictionary file into a Trie and provides a user interface
 * for checking if a sequence is a word or prefix in the Trie.
 *  
 * @Author: Kirtan Patel 
 * @Date: 11/02/2024
 */
public class TrieDriver {

    public static void main(String[] args) {
        Trie trie = new Trie();  // Initialize a new Trie

        // Load words from "dictionary.txt" into the Trie
        try (BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"))) {
            String word;
            while ((word = reader.readLine()) != null) {
                trie.insert(word.toLowerCase());  // Convert to lowercase to ensure case-insensitivity
            }
            System.out.println("Dictionary loaded into Trie.");
        } catch (IOException e) {
            System.err.println("Error reading dictionary file: " + e.getMessage());
            return;  // Exit if there is an error loading the dictionary
        }

        // User interaction loop for checking words and prefixes
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a sequence (blank line to end): ");
            String sequence = scanner.nextLine().toLowerCase();  // Convert user input to lowercase

            // Exit loop if the user inputs a blank line
            if (sequence.isBlank()) {
                System.out.println("DONE");
                break;
            }

            // Check if the sequence is a word or prefix in the Trie
            if (trie.containsWord(sequence)) {
                System.out.println("  " + sequence + " is a word.");
            } else if (trie.containsPrefix(sequence)) {
                System.out.println("  " + sequence + " is not a word, but it is a prefix of a word.");
            } else {
                System.out.println("  " + sequence + " is neither a word nor a prefix.");
            }
        }

        scanner.close();  // Close the scanner to prevent resource leaks
    }
}
