package Tries;

public class Trie {

	Trie[] trie;
	boolean checkEnd;

	Trie() {
		trie = new Trie[26];
	}

	public static void main(String[] args) {
		String word1 = "and";
		String word2 = "ant";

		Trie trie = new Trie();

		trie.insert(word1);
		trie.insert(word2);

		boolean isPresent1 = trie.search("and");
		System.out.println("The word and is present: " + isPresent1);
		boolean isPresent2 = trie.search("an");
		System.out.println("The word an is present: " + isPresent2);
		boolean isPresent3 = trie.search("andrew");
		System.out.println("The word andrew is present: " + isPresent3);
		boolean isPresent4 = trie.search("ant");
		System.out.println("The word ant is present: " + isPresent4);
		boolean isPresent5 = trie.search("atn");
		System.out.println("The word atn is present: " + isPresent5);

		boolean startsWith1 = trie.startsWith("and");
		System.out.println("The word and is a prefix present in trie: " + startsWith1);
		boolean startsWith2 = trie.startsWith("an");
		System.out.println("The word an is a prefix present in trie: " + startsWith2);
		boolean startsWith3 = trie.startsWith("a");
		System.out.println("The word a is a prefix present in trie: " + startsWith3);
		boolean startsWith4 = trie.startsWith("");
		System.out.println("The empty word is a prefix present in trie: " + startsWith4);
		boolean startsWith5 = trie.startsWith("andrew");
		System.out.println("The word andrew is a prefix present in trie: " + startsWith5);
		boolean startsWith6 = trie.startsWith("atn");
		System.out.println("The word atn is a prefix present in trie: " + startsWith6);
	}

	// Cannot use this/super keyword in static context
	private void insert(String word) {
		Trie trie = this;
		for (char c : word.toCharArray()) {
			if (trie.trie[c - 'a'] == null) {
				trie.trie[c - 'a'] = new Trie();
			}
			trie = trie.trie[c - 'a'];
		}
		trie.checkEnd = true;
	}

	private boolean search(String word) {
		Trie trie = this;
		for (char c : word.toCharArray()) {
			if (trie.trie[c - 'a'] == null) {
				return false;
			}
			trie = trie.trie[c - 'a'];
		}
		return trie.checkEnd;
	}

	private boolean startsWith(String word) {
		Trie trie = this;
		for (char c : word.toCharArray()) {
			if (trie.trie[c - 'a'] == null) {
				return false;
			}
			trie = trie.trie[c - 'a'];
		}
		return true;
	}
}
