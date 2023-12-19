package datastructures.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ------------------------------------------------------------------------------------------------
 * Поиск в ширину для графов
 * ------------------------------------------------------------------------------------------------
 * Поиск в ширину (breadth-first search, BFS) — один из методов обхода графа. Особенностью является
 * поочередный обход ближайших вершин к стартовой. В отличие от поиска в глубину, сначала
 * обрабатываются все вершины, смежные данной, а только потом происходит переход. Для решения
 * подобной задачи используется тот же подход (цветовая маркировка), как и для поиска в глубину.
 * ------------------------------------------------------------------------------------------------
 * Наиболее простая реализация
 *
 * В отличие от поиска в глубину, поиск в ширину реализуется обычным циклическим алгоритмом
 * без применения рекурсии. Для этого используют очередь, в которую добавляют все вершины смежные
 * данной и имеющие белый цвет, после этого начинают извлекать и обрабатывать вершины из этой
 * очереди. Алгоритм заканчивается, когда очередь становиться пустой.
 * ------------------------------------------------------------------------------------------------
 * Проверка графа на связность
 *
 * Поиск в ширину также можно использовать для проверки графа на связность. Достаточно
 * запустить поиск в ширину (указывая в качестве стартовой любую вершину) и после него проверить
 * все вершины на цвет. Если все вершины поменяли цвет, то граф связный, если остались
 * «белого цвета», то граф не связный.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/s_oXy8TUPg0">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class AdjacencyListBasedGraphBFS {

    private final Map<String, Node> nodes = new HashMap<>();

    private class Node {
        final String id;
        Object data;
        List<Node> adjacentNodes = new ArrayList<>();
        int color = 0; // 0 - white, 1 - black

        public Node(String id) {
            this.id = id;
        }
    }

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

    public void bfs(Node startNode) {
        Deque<Node> nodeDeque = new ArrayDeque<>();
        nodeDeque.push(startNode);
        while (nodeDeque.size() > 0) {
            Node currentNode = nodeDeque.poll();
            for (Node node : currentNode.adjacentNodes) {
                if (node.color == 0) {
                    nodeDeque.push(node);
                }
            }
            currentNode.color = 1;
        }
    }

    public void bfs(String startNodeId) {
        Node node = nodes.get(startNodeId);
        if (node == null) {
            return;
        }
        bfs(node);
    }

    public boolean isConnectedGraph() {
        boolean result = true;
        repaintNodesToWhiteColor();
        for (String nodeId : nodes.keySet()) {
            // запускаем BFS на первом попавшемся ключе nodeId из множестве и выходим из цикла
            bfs(nodes.get(nodeId));
            break;
        }
        // если хотя бы одна вершина осталась белая, то граф несвязный
        for (String nodeId : nodes.keySet()) {
            if (nodes.get(nodeId).color == 0) {
                result = false;
                break;
            }
        }
        repaintNodesToWhiteColor();
        return result;
    }

    public void repaintNodesToWhiteColor() {
        for (String nodeId : nodes.keySet()) {
            nodes.get(nodeId).color = 0;
        }
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

    //==================================================================================
    public static void main(String[] args) {
        AdjacencyListBasedGraphBFS graph = new AdjacencyListBasedGraphBFS();

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
        System.out.println(graph.isConnectedGraph());
        System.out.println();

        graph.removeEdge("a", "d");
        System.out.println(graph);
        System.out.println(graph.isConnectedGraph());
    }
}
