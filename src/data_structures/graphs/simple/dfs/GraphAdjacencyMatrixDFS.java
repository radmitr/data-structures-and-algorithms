package data_structures.graphs.simple.dfs;

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
public class GraphAdjacencyMatrixDFS {

    public static final int INITIAL_MATRIX_SIZE = 100;

    private Node[] nodes;
    public int[][] adjacencyMatrix;
    private int matrixSize;
    private boolean isMatrixFull;

    public GraphAdjacencyMatrixDFS() {
        matrixSize = INITIAL_MATRIX_SIZE;
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
            };
        }
        int[] indexes = new int[n];

        int insertIndex = 0;
        for (int i = 0; i < matrixSize; i++) {
            if (adjacencyMatrix[idx][i] > 0) {
                indexes[insertIndex++] = i;
            };
        }
        return indexes;
    }

    /**
     * Функкия обхода графа
     */
    public void dfs(int index) throws IllegalAccessException {
        if (nodes[index].color == 1) {
            return;
        }
        nodes[index].color = 1;
        int[] adjacentIndexes = getAdjacentNodesIndexes(nodes[index].id);
        for (int idx : adjacentIndexes) {
            dfs(idx);
        }
    }

    /**
     * Функкия для старта обхода графа
     */
    public void dfs(String startNodeId) throws IllegalAccessException {
        int idx = findNodeIndexById(startNodeId);
        if (idx < 0) {
            return;
        }
        dfs(idx);
    }

    public boolean isConnectedGraph() throws IllegalAccessException {
        boolean result = true;
        repaintNodesToWhiteColor();
        for (Node node : nodes) {
            if (node != null) {
                // запускаем DFS на первом попавшемся node != null в массиве и выходим из цикла
                dfs(node.id);
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
        GraphAdjacencyMatrixDFS graph = new GraphAdjacencyMatrixDFS();

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
