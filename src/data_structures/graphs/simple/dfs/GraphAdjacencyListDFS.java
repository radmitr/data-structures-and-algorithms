package data_structures.graphs.simple.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ------------------------------------------------------------------------------------------------
 * Поиск в глубину
 * ------------------------------------------------------------------------------------------------
 * Поиск в глубину (Depth-first search, DFS) — один из способов полного обхода графа,
 * особенностью которого является перебор вершин в порядке наискорейшего удаления от стартовой.
 * Это достигается за счет рекурсивного перебора вершин по следующему алгоритму - перебираем все
 * исходящие из рассматриваемой вершины рёбра. Если ребро ведёт в вершину, которая не была
 * рассмотрена ранее, то запускаем алгоритм от этой не рассмотренной вершины, а после
 * возвращаемся и продолжаем перебирать рёбра. Возврат происходит в том случае, если в
 * рассматриваемой вершине не осталось рёбер, которые ведут в не рассмотренную вершину.
 * ------------------------------------------------------------------------------------------------
 * Как избежать бесконечного рекурсивного вызова
 *
 * Как избежать бесконечного рекурсивного вызова? Например если переходить по всем ребрам из
 * вершины c то опять будет выполнен переход в вершину b. Для этого предлагается ввести цветовую
 * маркировку вершины. Вершина которая еще не была посещена «окрашивается» в белый цвет,
 * вершина которая уже была посещена в черный. В таком случае если вершина окрашена в черный, то
 * она уже посещена и выполнять к ней переход уже не нужно.
 * ------------------------------------------------------------------------------------------------
 * Полный алгоритм поиска в глубину
 *
 * В случае если граф связный, то просто выбирается вершина в качестве стартовой, и
 * выполняется рекурсивный обход с учетом цветовой маркировки.
 * Если граф не связный то в качестве стартовой вершины выбираются все «белые» вершины по
 * очереди. Выбранная вершина используется в качестве стартовой.
 * ------------------------------------------------------------------------------------------------
 * Проверка графа на связность
 *
 * Поиск в глубину можно использовать для проверки графа на связность. Достаточно запустить
 * поиск в глубину(указывая в качестве стартовой любую вершину) и после него проверить все вершины
 * на цвет. Если все вершины поменяли цвет, то граф связный, если остались «белого цвета» то граф
 * не связный.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/NY5Fs15ad6Q">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class GraphAdjacencyListDFS {

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
            throw new IllegalArgumentException("Node with this ID does not exist");
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
            throw new IllegalArgumentException("Node with this ID does not exist");
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

    public String[] getAdjacentNodesIds(String id) {
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

    /**
     * Функкия обхода графа
     */
    public void dfs(Node startNode) {
        if (startNode.color == 1) {
            return;
        }
        startNode.color = 1;
        for (Node node : startNode.adjacentNodes) {
            dfs(node);
        }
    }

    /**
     * Функкия для старта обхода графа
     */
    public void dfs(String startNodeId) {
        Node node = nodes.get(startNodeId);
        if (node == null) {
            return;
        }
        dfs(node);
    }

    public boolean isConnectedGraph() {
        boolean result = true;
        repaintNodesToWhiteColor();
        for (String nodeId : nodes.keySet()) {
            // запускаем DFS на первом попавшемся ключе nodeId из множестве и выходим из цикла
            dfs(nodes.get(nodeId));
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

    //===========================================================================
    public static void main(String[] args) {
        GraphAdjacencyListDFS graph = new GraphAdjacencyListDFS();

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
        System.out.println();
    }
}
