import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Graph graph = new Graph();

		graph.addNode("a");
		graph.addNode("b");
		graph.addNode("c");
		graph.addNode("d");
		graph.addNode("e");

		graph.addEdge("a", "b");
		graph.addEdge("a", "e");
		graph.addEdge("a", "d");
		graph.addEdge("a", "c");
		graph.addEdge("c", "b");

		System.out.println(graph);
		
//		graph.removeEdge("b", "c");
//		System.out.println(graph);	
//		graph.removeNode("b");
//		System.out.println(graph);
		
		System.out.println(graph.isAdjacent("b", "c"));
		System.out.println(Arrays.toString(graph.getAdjacentNodesId("a")));
	}
}

class Graph {

	private class Node {
		final String id;
		Object data;
		List<Node> adjacentNodes = new ArrayList<>();

		public Node(String id) {
			super();
			this.id = id;
		}
	}

	private final Map<String, Node> nodes = new HashMap<>();

	public void addNode(String id, Object data) {
		if (nodes.get(id) != null) {
			throw new IllegalArgumentException("Node with this ID already exists");
		}
		Node newNode = new Node(id);
		newNode.data = data;
		nodes.put(id, newNode);
	}

	public void addNode(String id) {
		addNode(id, null);
	}

	public void addEdge(String idFrom, String idTo) {
		Node nodeFrom = nodes.get(idFrom);
		Node nodeTo = nodes.get(idTo);
		if (nodeFrom == null || nodeTo == null) {
			throw new IllegalArgumentException("Node with this id does not exist");
		}
		if (nodeFrom == nodeTo) {
			throw new IllegalArgumentException("Loop edge");
		}
		nodeFrom.adjacentNodes.add(nodeTo);
		nodeTo.adjacentNodes.add(nodeFrom);
	}

	public void removeNode(String id) {
		Node removeNode = nodes.get(id);
		if (removeNode == null) {
			return;
		}
		for (Node node : removeNode.adjacentNodes) {
			node.adjacentNodes.remove(removeNode);
		}
		nodes.remove(id);
	}

	public void removeEdge(String idFrom, String idTo) {
		Node nodeFrom = nodes.get(idFrom);
		Node nodeTo = nodes.get(idTo);
		if (nodeFrom == null || nodeTo == null) {
			throw new IllegalArgumentException("Node with this id does not exist");
		}
		nodeFrom.adjacentNodes.remove(nodeTo);
		nodeTo.adjacentNodes.remove(nodeFrom);
	}

	public boolean isAdjacent(String idFrom, String idTo) {
		Node nodeFrom = nodes.get(idFrom);
		Node nodeTo = nodes.get(idTo);
		if (nodeFrom == null || nodeTo == null) {
			return false;
		}
		return nodeFrom.adjacentNodes.contains(nodeTo);
	}

	public String[] getAdjacentNodesId(String id) {
		Node node = nodes.get(id);
		if (node == null) {
			return null;
		}
		String[] ids = new String[node.adjacentNodes.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = node.adjacentNodes.get(i).id;
		}
		return ids;
	}

	public Object getNodeDataById(String id) {
		Node node = nodes.get(id);
		if (node == null) {
			return null;
		}
		return node.data;
	}

	public void setNodeDataById(String id, Object data) {
		Node node = nodes.get(id);
		if (node == null) {
			return;
		}
		node.data = data;
	}

	public String toString() {
		String result = "";
		for (String k : nodes.keySet()) {
			result += k + " -> ";
			for (Node node : nodes.get(k).adjacentNodes) {
				result += node.id + ", ";
			}
			result = result.substring(0, result.length() - 2) + System.lineSeparator();
		}
		return result;
	}
}
