package Tries;

public class TrieNode {

	// Array for child nodes of each node
	TrieNode[] trie;
	// Used for indicating the end of the string
	boolean checkEnd;

	TrieNode() {
		// Initilize every index of the child array with null
		// indicating that there are no child nodes at present.
		trie = new TrieNode[26];

	}

	public static void main(String[] args) {

		TrieNode trie = new TrieNode();

		String word1 = "and";
		String word2 = "ant";

		System.out.println("Tries Insertion");
		insertion(trie, word1);
		insertKey(trie, word2);

		System.out.println("Tries Traversal");
		boolean isPresent1 = search(trie, "and");
		System.out.println("The values and is present " + isPresent1);
		boolean isPresent2 = search(trie, "ani");
		System.out.println("The values ani is present " + isPresent2);
		boolean isPresent3 = search(trie, "an");
		System.out.println("The values an is present " + isPresent3);
		boolean isPresent4 = search(trie, "andrew");
		System.out.println("The values andrew is present " + isPresent4);
		boolean isPresent5 = search(trie, "ant");
		System.out.println("The values ant is present " + isPresent5);
	}

	// Time complexity - O(n) or O(number of words*max(word_sizes))
	// Space complexity - O(n) or O(number of words*max(word_sizes))
	private static boolean search(TrieNode trie, String value) {
		// Initialize the current pointer with root node
		TrieNode curr = trie;
		for (char c : value.toCharArray()) {
			// Check is a node exists for current character in the trie
			// If it's not then return false
			if (curr.trie[c - 'a'] == null) {
				return false;
			}
			// Move the pointer to the already existing node for the character.
			curr = curr.trie[c - 'a'];
		}
		// Return true if the word exists and is marked as ending.
		return curr.checkEnd;
	}

	// Time complexity - O(n) or O(number of words*max(word_sizes))
	// Space complexity - O(n) or O(number of words*max(word_sizes))
	private static void insertKey(TrieNode trie, String word) {
		// Get the pointer or reference of current TrieNode
		TrieNode curr = trie;
		for (char c : word.toCharArray()) {
			// Check if a node exists for the character in the Trie
			// Check if the TrieNode is null at an index given by character
			if (curr.trie[c - 'a'] == null) {
				// If node for a current character does not exist
				// insert a new TrieNode at the index
				// Keep the reference for the newly created node.
				curr.trie[c - 'a'] = new TrieNode();
			}
			// If the TrieNode is not null at the given index or
			// If we've just inserted a new TrieNode, then
			// Change curr's reference to the new TrieNode
			// Move the pointer to the newly created node.
			curr = curr.trie[c - 'a'];
		}
		// After inserting the word or reaching the end of a word.
		// Change the checkEnd flag to true, marking end of the word in a trie.
		curr.checkEnd = true;

	}

	// Even thought the trie is pointed to it's internal value
	// While returning the trie, the parent trie is maintained.
	// Time complexity - O(n) or O(number of words*max(word_sizes))
	// Space complexity - O(n) or O(number of words*max(word_sizes))
	private static void insertion(TrieNode trie, String word) {
		int n = word.length();
		for (int i = 0; i < n; i++) {
			int index = word.charAt(i) - 'a';
			if (trie.trie[index] == null) {
				trie.trie[index] = new TrieNode();
				trie = trie.trie[index];
			} else {
				trie = trie.trie[index];
			}
		}
		trie.checkEnd = true;

	}

}
