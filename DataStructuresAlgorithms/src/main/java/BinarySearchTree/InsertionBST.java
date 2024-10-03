package BinarySearchTree;

/*
 * BST Insertion - A new key/node is always inserted at the leaf of a BST.
 * Search the key in the BST from root till the leaf node, then add the new node as the child of the leaf node.
 */
public class InsertionBST {

	static class Node {
		int key;
		Node left, right;

		Node(int item) {
			this.key = item;
			left = right = null;
		}
	}

	public static void main(String[] args) {

		Node root = null;

		// Creating the following BST
        //      50
        //     /  \
        //    30   70
        //   / \   / \
        //  20 40 60 80

		root = insertRecursive(root, 50);
		root = insertRecursive(root, 30);
		root = insertRecursive(root, 20);
		root = insertRecursive(root, 40);
		root = insertRecursive(root, 70);
		root = insertRecursive(root, 60);
		root = insertRecursive(root, 80);

		root = insert(root, 50);
		root = insert(root, 30);
		root = insert(root, 20);
		root = insert(root, 40);
		root = insert(root, 70);
		root = insert(root, 60);
		root = insert(root, 80);
		
		// Print inorder traversal of the BST.
		inOrder(root);
	}

	// Insert a new node iteratively
	// Time complexity: O(h) for height of binary tree (O(n) for skewed)
	// Space complexity: O(1) for constant extra space.
	private static Node insert(Node node, int key) {

		Node newNode = new Node(key);
		
		// If node is empty return new node
		if(node == null) {
			return newNode;
		}

		// Find the node which will have the new node as it's child
		Node curr = node;
		Node parent = null;
		while (curr != null) {
			parent = curr;
			if(key < curr.key) {
				curr = curr.left;
			} else if(key > curr.key) {
				curr = curr.right;
			} else {
				return node;
			}
		}
		
		// Make the new node as child of parent node based on key comparison
		if(key < parent.key) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}

		return node;
	}

	// Insert a new node with the given key via recursion.
	// Time complexity: O(h) where h is height of binary search tree
	// In worst case the height of BST is n, skewed and TC is O(n).
	// Space complexity: O(h) where h is for recursive call stack
	private static Node insertRecursive(Node node, int key) {

		// If the tree is empty, return a new node with the given key
		if (node == null) {
			return new Node(key);
		}

		// If the key is already present, then return the node.
		if (node.key == key) {
			return node;
		}
		// Otherwise recur down the tree
		if (key < node.key) {
			node.left = insertRecursive(node.left, key);
		} else {
			node.right = insertRecursive(node.right, key);
		}
		// Return the unchanged node pointer.
		return node;
	}

	// A utility function to do inorder tree traversal
	private static void inOrder(Node root) {
		if (root != null) {
			inOrder(root.left);
			System.out.println(root.key + " ");
			inOrder(root.right);
		}
	}

}
