package HW4;

/**
 * Trie class that implements a Trie data structure for storing words and checking prefixes.
 * This class supports inserting words, checking if a word exists, and checking if a prefix exists.
 * 
 * @Author: Kirtan Patel
 * @Date: 11/02/2024  
 */
public class Trie {

    // Root node of the Trie
    private TrieNode root;

    /**
     * Constructor to initialize the Trie with a root node.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the Trie.
     * @param word the word to insert into the Trie
     */
    public void insert(String word) {
        TrieNode current = root;  // Start from the root node
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';  // Calculate index for each character
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();  // Create a new node if it doesn't exist
            }
            current = current.children[index];  // Move to the next node
        }
        current.isWord = true;  // Mark the end of the word
    }

    /**
     * Checks if a word exists in the Trie.
     * @param word the word to search for in the Trie
     * @return true if the word exists, false otherwise
     */
    public boolean containsWord(String word) {
        TrieNode node = getNode(word);  // Get the end node for the word
        return node != null && node.isWord;  // Check if node exists and is marked as a word
    }

    /**
     * Checks if a prefix exists in the Trie.
     * @param prefix the prefix to search for in the Trie
     * @return true if the prefix exists, false otherwise
     */
    public boolean containsPrefix(String prefix) {
        return getNode(prefix) != null;  // Check if the end node for the prefix exists
    }

    /**
     * Helper method to retrieve the end node of a word or prefix.
     * @param word the word or prefix to search for in the Trie
     * @return the end node if the word or prefix exists, or null if it does not
     */
    private TrieNode getNode(String word) {
        TrieNode current = root;  // Start from the root node
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';  // Calculate index for each character
            if (current.children[index] == null) {
                return null;  // Prefix or word does not exist
            }
            current = current.children[index]; /** Move to the next node and children meaning nodes that are 
            									* 	below a given parent element or node in the structure
            									*/
        }
        return current;  // Return the final node reached
    }

    /**
     * Inner class representing each node in the Trie.
     * Each TrieNode has a flag to indicate if it completes a word and an array of 26 children for each letter a-z.
     */
    private static class TrieNode {
        private boolean isWord;  // Flag indicating if this node completes a word
        private TrieNode[] children;

        /**
         * Constructor for TrieNode, initializing the isWord flag and the array of children.
         */
        public TrieNode() {
            this.isWord = false;  // Initially, no word ends at this node
            this.children = new TrieNode[26];  // 26 children for each letter a-z
        }
    }
}
