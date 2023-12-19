package datastructures.graphs;

import java.util.ArrayDeque;
import java.util.Deque;

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
public class AdjacencyMatrixBasedGraphBFS {

    public static final int DEFAULT_MATRIX_SIZE = 100;

    private Node[] nodes;
    public int[][] adjacencyMatrix;
    private int matrixSize;
    private boolean isMatrixFull;

    public AdjacencyMatrixBasedGraphBFS() {
        matrixSize = DEFAULT_MATRIX_SIZE;
        nodes = new Node[matrixSize];
        adjacencyMatrix = new int[matrixSize][matrixSize];
    }

    private class Node {
        final String id;
        Object data;
        int color = 0; // 0 - white, 1 - black

        public Node(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id;
        }
    }

    public int findNodeIndexById(String id) {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null && nodes[i].id.equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void addNode(String id, Object data) {
        int idx = findNodeIndexById(id);
        if (idx >= 0) {
            throw new IllegalArgumentException("Node with this ID already exists");
        }
        if (isMatrixFull) {
            throw new IndexOutOfBoundsException("Matrix is full");
        }
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                nodes[i] = new Node(id);
                nodes[i].data = data;
                if (i == nodes.length - 1) {
                    isMatrixFull = true;
                }
                return;
            }
        }
    }

    public void addNode(String id) {
        addNode(id, null);
    }

    public void addEdge(String id1, String id2) throws IllegalAccessException {
        int i = findNodeIndexById(id1);
        int j = findNodeIndexById(id2);
        if (i < 0 || j < 0) {
            throw new IllegalAccessException("No node with this ID");
        }
        if (i == j) {
            throw new IllegalAccessException("Loop edge");
        }
        adjacencyMatrix[i][j] = 1;
        adjacencyMatrix[j][i] = 1;
    }

    public void removeNode(String id) {
        int idx = findNodeIndexById(id);
        if (idx < 0) {
            throw new IllegalArgumentException("No node with this ID");
        }
        nodes[idx] = null;
        for (int i = 0; i < matrixSize; i++) {
            adjacencyMatrix[idx][i] = 0;
            adjacencyMatrix[i][idx] = 0;
        }
        isMatrixFull = false;
    }

    public void removeEdge(String id1, String id2) throws IllegalAccessException {
        int i = findNodeIndexById(id1);
        int j = findNodeIndexById(id2);
        if (i < 0 || j < 0) {
            throw new IllegalAccessException("No node with this ID");
        }
        adjacencyMatrix[i][j] = 0;
        adjacencyMatrix[j][i] = 0;
    }

    public boolean isAdjacent(String id1, String id2) throws IllegalAccessException {
        int i = findNodeIndexById(id1);
        int j = findNodeIndexById(id2);
        if (i < 0 || j < 0) {
            throw new IllegalAccessException("No node with this ID");
        }
        if (adjacencyMatrix[i][j] != 0) {
            return true;
        } else {
            return false;
        }
    }

    public int[] getAdjacentNodesIndexes(String id) throws IllegalAccessException {
        int idx = findNodeIndexById(id);
        if (idx < 0) {
            throw new IllegalAccessException("No node with this ID");
        }

        int n = 0;
        for (int i = 0; i < matrixSize; i++) {
            if (adjacencyMatrix[idx][i] > 0) {
                n++;
            }
        }
        int[] indexes = new int[n];

        int insertIndex = 0;
        for (int i = 0; i < matrixSize; i++) {
            if (adjacencyMatrix[idx][i] > 0) {
                indexes[insertIndex++] = i;
            }
        }
        return indexes;
    }

    /**
     * Функкия обхода графа в ширину
     */
    public void bfs(int index) throws IllegalAccessException {
        Deque<Integer> nodeDeque = new ArrayDeque<>();
        nodeDeque.push(index);
        while (nodeDeque.size() > 0) {
            int currentIndex = nodeDeque.poll();
            int[] adjacentIndexes = getAdjacentNodesIndexes(nodes[currentIndex].id);
            for (int idx : adjacentIndexes) {
                if (nodes[idx].color == 0) {
                    nodeDeque.push(idx);
                }
            }
            nodes[currentIndex].color = 1;
        }
    }

    /**
     * Функкия для старта обхода графа в ширину
     */
    public void bfs(String startNodeId) throws IllegalAccessException {
        int idx = findNodeIndexById(startNodeId);
        if (idx < 0) {
            return;
        }
        bfs(idx);
    }

    public boolean isConnectedGraph() throws IllegalAccessException {
        boolean result = true;
        repaintNodesToWhiteColor();
        for (Node node : nodes) {
            if (node != null) {
                // запускаем BFS на первом попавшемся node != null в массиве и выходим из цикла
                bfs(node.id);
                break;
            }
        }
        // если хотя бы одна вершина осталась белая, то граф несвязный
        for (Node node : nodes) {
            if (node != null && node.color == 0) {
                result = false;
                break;
            }
        }
        repaintNodesToWhiteColor();
        return result;
    }

    public void repaintNodesToWhiteColor() {
        for (Node node : nodes) {
            if (node != null) {
                node.color = 0;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\t");

        for (Node node : nodes) {
            if (node != null) {
                builder.append(node.id).append("\t");
            }
        }
        builder.append(System.lineSeparator());

        for (int i = 0; i < matrixSize; i++) {
            if (nodes[i] != null) {
                builder.append("\s").append(nodes[i].id).append("\t");
                for (int j = 0; j < matrixSize; j++) {
                    if (nodes[j] != null) {
                        if (adjacencyMatrix[i][j] > 0) {
                            builder.append("1").append("\t");
                        } else {
                            builder.append("0").append("\t");
                        }
                    }
                }
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    //===========================================================================
    public static void main(String[] args) throws IllegalAccessException {
        AdjacencyMatrixBasedGraphBFS graph = new AdjacencyMatrixBasedGraphBFS();

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
