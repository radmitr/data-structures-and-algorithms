import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		BinaryTree biTree = new BinaryTree();

		biTree.addNode(6, "Pear");
		biTree.addNode(4, "Apple");
		biTree.addNode(0, "Plum");
		biTree.addNode(5, "Orange");
		biTree.addNode(9, "Lemon");
		biTree.addNode(7, "Cherry");
		biTree.addNode(12, "Apricot");

//		System.out.println(biTree.findByKey(12));

		System.out.println(biTree);

//		biTree.deleteNode(4);

		System.out.println(biTree);

		System.out.println(biTree.size());

	}

}

class BinaryTree {
	private class Node {
		int key;
		Object data;
		Node leftNode;
		Node rightNode;

		public Node(int key, Object data) {
			this.key = key;
			this.data = data;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", data=" + data + "]";
		}

	}

	private Node root;

	public void addNode(int key, Object data) {
		root = addNodeRecursive(root, key, data);
	}

	private Node addNodeRecursive(Node node, int key, Object data) {
		if (node == null) {
			return new Node(key, data);
		}
		if (key == node.key) {
			node.data = data;
			return node;
		}
		if (key < node.key) {
			node.leftNode = addNodeRecursive(node.leftNode, key, data);
		} else {
			node.rightNode = addNodeRecursive(node.rightNode, key, data);
		}
		return node;
	}

	public Object findByKey(int key) {
		return findByKeyRecursive(root, key);
	}

	private Object findByKeyRecursive(Node node, int key) {
		if (node == null) {
			return null;
		}
		if (node.key == key) {
			return node.data;
		}
		return (key < node.key) ? findByKeyRecursive(node.leftNode, key) : findByKeyRecursive(node.rightNode, key);
	}

	public void deleteNode(int key) {
		root = deleteNodeRewcursive(root, key);
	}

	private Node deleteNodeRewcursive(Node node, int key) {
		if (node == null) {
			return node;
		}
		if (key == node.key) {

			// node has no child node
			if (node.leftNode == null && node.rightNode == null) {
				return null;
			}

			// node has exactly one child

			if (node.leftNode == null) {
				return node.rightNode;
			}
			if (node.rightNode == null) {
				return node.leftNode;
			}

			// node has two child node
			if (node.rightNode != null && node.leftNode != null) {
				Node smallestNode = findSmallestValue(node.rightNode);
				node.key = smallestNode.key;
				node.data = smallestNode.data;
				node.rightNode = deleteNodeRewcursive(node.rightNode, smallestNode.key);
				return node;
			}

		}
		if (key < node.key) {
			node.leftNode = deleteNodeRewcursive(node.leftNode, key);
			return node;
		} else {
			node.rightNode = deleteNodeRewcursive(node.rightNode, key);
			return node;
		}
	}

	private Node findSmallestValue(Node node) {
		return node.leftNode == null ? node : findSmallestValue(node.leftNode);
	}

	public int size() {
		return sizeRecursive(root);
	}

	private int sizeRecursive(Node node) {
		if (node == null) {
			return 0;
		}
		return 1 + sizeRecursive(node.leftNode) + sizeRecursive(node.rightNode);
	}

	public String toString() {
		if (root == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		List<Node> nodes = new ArrayList<>();
		List<Node> nodesNextLevel = new ArrayList<>();
		nodes.add(root);
		for (;;) {
			for (; nodes.size() > 0;) {
				Node node = nodes.remove(0);
				sb.append(node).append("\t");
				if (node.leftNode != null) {
					nodesNextLevel.add(node.leftNode);
				}
				if (node.rightNode != null) {
					nodesNextLevel.add(node.rightNode);
				}
			}
			sb.append("\n");
			if (nodesNextLevel.size() == 0) {
				break;
			}
			nodes.addAll(nodesNextLevel);
			nodesNextLevel.clear();
		}
		return sb.toString();
	}
}
