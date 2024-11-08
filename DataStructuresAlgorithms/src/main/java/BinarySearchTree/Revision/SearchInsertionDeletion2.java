package BinarySearchTree.Revision;

import BinarySearchTree.Revision.SearchInsertionDeletion.Node;

public class SearchInsertionDeletion2 {

	static class Node {
		int val;
		Node left;
		Node right;

		Node(int val) {
			this.val = val;
		}
	}

	public static void main(String... args) {
		Node root = new Node(50);
		root.left = new Node(30);
		root.right = new Node(70);
		root.left.left = new Node(20);
		root.left.right = new Node(40);
		root.right.left = new Node(60);
		root.right.right = new Node(80);

		Node iterativeNode = searchIterative(root, 80);

		System.out.println("Iterative search: The val at key is " + iterativeNode.val);

		Node recursiveNode = searchRecursive(root, 80);

		System.out.println("Recursive search: The val at key is " + recursiveNode.val);

		Node node = null;
		node = iterativeInsert(node, 50);
		node = iterativeInsert(node, 30);
		node = iterativeInsert(node, 70);
		node = iterativeInsert(node, 20);
		node = iterativeInsert(node, 40);
		node = iterativeInsert(node, 60);
		node = iterativeInsert(node, 80);

		inOrderTraversal(node);
		System.out.println();

		node = null;
		node = recursiveInsert(node, 50);
		node = recursiveInsert(node, 30);
		node = recursiveInsert(node, 70);
		node = recursiveInsert(node, 20);
		node = recursiveInsert(node, 40);
		node = recursiveInsert(node, 60);
		node = recursiveInsert(node, 80);

		inOrderTraversal(node);
		System.out.println();

		node = deleteIterative(node, 40);

		inOrderTraversal(node);
		System.out.println();

		node = deleteRecursive(node, 50);

		inOrderTraversal(node);
		System.out.println();

		node = deleteRecursiveOpt(node, 60);

		inOrderTraversal(node);
		System.out.println();
	}

	private static Node searchIterative(Node root, int key) {

		if (root == null) {
			return null;
		}
		while (root != null) {
			if (key == root.val) {
				return root;
			} else if (key < root.val) {
				root = root.left;
			} else {
				root = root.right;
			}
		}

		return root;

	}

	private static Node searchRecursive(Node root, int key) {
		if (root == null || key == root.val) {
			return root;
		}
		if (key < root.val) {
			return searchRecursive(root.left, key);
		}
		return searchRecursive(root.right, key);
	}

	private static Node iterativeInsert(Node node, int key) {
		if (node == null) {
			return new Node(key);
		}
		Node newNode = new Node(key);
		Node parent = null;
		Node curr = node;
		while (curr != null) {
			parent = curr;
			if (key == curr.val) {
				return node;
			} else if (key < curr.val) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		if (key < parent.val) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		return node;
	}

	private static Node recursiveInsert(Node node, int key) {
		Node newNode = new Node(key);
		if (node == null) {
			return newNode;
		}
		if (key == node.val) {
			return node;
		} else if (key < node.val) {
			node.left = recursiveInsert(node.left, key);
		} else {
			node.right = recursiveInsert(node.right, key);
		}
		return node;
	}

	private static void inOrderTraversal(Node node) {
		if (node != null) {
			inOrderTraversal(node.left);
			System.out.print(node.val + " ");
			inOrderTraversal(node.right);
		}
	}

	private static Node deleteIterative(Node node, int key) {
		if (node == null) {
			return node;
		}
		Node parent = null;
		Node curr = node;
		while (curr != null && key != curr.val) {
			parent = curr;
			if (key < curr.val) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		if (curr == null) {
			return node;
		}
		if (curr.left == null || curr.right == null) {
			Node newNode = null;
			if (curr.left == null) {
				newNode = curr.right;
			}
			if (curr.right == null) {
				newNode = curr.left;
			}
			if (parent == null) {
				return newNode;
			}
			if (parent.left == curr) {
				parent.left = newNode;
			} else {
				parent.right = newNode;
			}

		} else {
			Node p = null;
			Node succ = curr.right;
			while (succ.left != null) {
				p = succ;
				succ = succ.left;
			}
			if (p != null) {
				p.left = succ.right;
			} else {
				curr.right = succ.right;
			}
			curr.val = succ.val;

		}
		return node;
	}

	private static Node deleteRecursive(Node node, int key) {
		if (node == null) {
			return node;
		}
		if (key < node.val) {
			node.left = deleteRecursive(node.left, key);
		} else if (key > node.val) {
			node.right = deleteRecursive(node.right, key);
		} else {
			if (node.left == null) {
				return node.right;
			}
			if (node.right == null) {
				return node.left;
			}
			Node successor = getSuccessor(node);
			node.val = successor.val;
			node.right = deleteRecursive(node.right, successor.val);
		}
		return node;
	}

	private static Node getSuccessor(Node curr) {
		curr = curr.right;
		while (curr.left != null) {
			curr = curr.left;
		}
		return curr;
	}

	private static Node deleteRecursiveOpt(Node node, int key) {
		if (node == null) {
			return node;
		}
		if (key < node.val) {
			node.left = deleteRecursiveOpt(node.left, key);
			return node;
		}
		if (key > node.val) {
			node.right = deleteRecursiveOpt(node.right, key);
			return node;
		}
		if (node.left == null) {
			return node.right;
		} else if (node.right == null) {
			return node.left;
		}

		Node parent = node;
		Node curr = node.right;
		while (curr.left != null) {
			parent = curr;
			curr = curr.left;
		}
		if (parent.left == curr) {
			parent.left = curr.right;
		} else {
			parent.right = curr.right;
		}
		node.val = curr.val;
		return node;
	}
}
