package BinarySearchTree.Revision;

public class SearchInsertionDeletion {

	static class Node {
		int val;
		Node right;
		Node left;

		public Node(int val) {
			this.val = val;
		}

		Node(int val, Node right, Node left) {
			this.val = val;
			this.left = left;
			this.right = right;
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

		Node nodeRecursive = searchRecursive(root, 80);

		System.out.println("The node is found with val " + nodeRecursive.val);

		Node nodeIterative = searchIterative(root, 70);

		System.out.println("The node is found with val " + nodeIterative.val);

		Node rootRec = null;
		rootRec = insertRecursive(rootRec, 50);
		rootRec = insertRecursive(rootRec, 30);
		rootRec = insertRecursive(rootRec, 20);
		rootRec = insertRecursive(rootRec, 40);
		rootRec = insertRecursive(rootRec, 70);
		rootRec = insertRecursive(rootRec, 60);
		rootRec = insertRecursive(rootRec, 80);

		inOrder(rootRec);
		System.out.println();

		Node rootItr = null;
		rootItr = insertIterative(rootItr, 50);
		rootItr = insertIterative(rootItr, 30);
		rootItr = insertIterative(rootItr, 20);
		rootItr = insertIterative(rootItr, 40);
		rootItr = insertIterative(rootItr, 70);
		rootItr = insertIterative(rootItr, 60);
		rootItr = insertIterative(rootItr, 80);

		inOrder(rootItr);
		System.out.println();

		root = deleteRecursive(root, 70);

		System.out.println("Deleting node via recursion in BST");

		inOrder(root);
		System.out.println();

		root = deleteIterative(root, 50);

		System.out.println("Deleting node via iteration in BST");

		inOrder(root);
		System.out.println();

		root = deleteRecursiveOptimized(root, 40);

		System.out.println("Deleting node via iteration in BST");

		inOrder(root);
	}

	private static Node deleteRecursiveOptimized(Node node, int key) {
		if (node == null) {
			return node;
		}
		if (key < node.val) {
			node.left = deleteRecursiveOptimized(node.left, key);
			return node;
		} else if (key > node.val) {
			node.right = deleteRecursiveOptimized(node.right, key);
			return node;
		}
		if (node.left == null) {
			return node.right;
		} else if (node.right == null) {
			return node.left;
		}
		
		Node parent = node;
		Node successor = node.right;
		while (successor.left != null) {
			parent = successor;
			successor = successor.left;
		}
		if (parent.left == successor) {
			parent.left = successor.right;
		} else {
			parent.right = successor.right;
		}
		node.val = successor.val;
		return node;
	}

	private static Node deleteIterative(Node node, int key) {
		if (node == null) {
			return node;
		}
		Node parent = null;
		Node curr = node;
		while (curr != null && curr.val != key) {
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
			Node newNode;
			if (curr.left == null) {
				newNode = curr.right;
			} else {
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
			Node successor = getSuccesor(node);
			node.val = successor.val;
			node.right = deleteRecursive(node.right, successor.val);
		}
		return node;
	}

	private static Node getSuccesor(Node curr) {
		curr = curr.right;
		while (curr.left != null) {
			curr = curr.left;
		}
		return curr;
	}

	private static Node insertIterative(Node node, int key) {

		Node newNode = new Node(key);

		if (node == null) {
			return newNode;
		}

		Node curr = node;
		Node parent = null;
		while (curr != null) {
			parent = curr;
			if (curr.val == key) {
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

	private static Node insertRecursive(Node node, int key) {
		if (node == null) {
			return new Node(key);
		}
		if (node.val == key) {
			return node;
		}

		if (key < node.val) {
			node.left = insertRecursive(node.left, key);
		} else {
			node.right = insertRecursive(node.right, key);
		}
		return node;
	}

	private static Node searchIterative(Node node, int key) {
		while (node != null) {
			if (node.val == key) {
				return node;
			} else if (key < node.val) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return null;
	}

	private static Node searchRecursive(Node node, int key) {
		if (node == null || node.val == key) {
			return node;
		}
		if (key < node.val) {
			return searchRecursive(node.left, key);
		} else {
			return searchRecursive(node.right, key);
		}
	}

	private static void inOrder(Node root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.val + " ");
			inOrder(root.right);
		}
	}

}
