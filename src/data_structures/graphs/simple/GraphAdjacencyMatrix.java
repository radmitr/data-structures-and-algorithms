package data_structures.graphs.simple;

/**
 * ------------------------------------------------------------------------------------------------
 * Способы представления простого графа
 * ------------------------------------------------------------------------------------------------
 * Распространенные способы представления простого графа
 *
 * Для создания структуры данных с помощью которой можно представить простой граф в
 * информатике чаще всего используется несколько подходов:
 *   ● Массив (список) ребер
 *   ● Массив (список) смежности
 *   ● Матрица смежности
 *
 * Вне зависимости от представления должны поддерживаться следующие базовые операции:
 *   ● Добавление и удаление вершины
 *   ● Добавление и удаление ребра
 *   ● Проверка на смежность вершин
 *   ● Получение всех смежных вершин для данной
 *   ● Получение данных хранимых вершиной
 *   ● Установка новых данных хранимых в вершине
 * ================================================================================================
 * Матрица смежности
 *
 * Представление в виде матрицы смежности подразумевает использование двумерного массива
 * количество строк и столбцов в котором равно количеству вершин. Элементом такого массива будет 0
 * если вершины представляющие строку и столбец не смежные, и положительное число в случае если
 * вершины смежные.
 *
 * 1. Добавление вершины в таком представлении одна из самых затратных операций. При этом
 * нужно увеличить размер матрицы на одну единицу и скопировать данные из предыдущей матрицы в
 * текущую.
 *
 * 2. Добавление ребра выполняется очень быстро (в этом преимущество этого представления). На
 * пересечении строки и столбца отвечающих за смежные вершины для этого ребра поставить значение
 * больше 0.
 *
 * 3. Для удаления вершины нужно или фактически удалить строку и столбец с заданной вершиной,
 * или пометить его как удаленный (ленивое удаление).
 *
 * 4. Для удаления ребра нужно установить значение равное 0 в строке и столбце соответствующие
 * вершинам этого ребра.
 *
 * 5. Для проверки вершин на смежность достаточно проверить значение стоящее на пересечении
 * строки и столбца соответствующих данным вершинам. Если стоит значение больше нуля то вершины
 * смежные.
 *
 * 6. Для получения вершин смежных данной следует выполнить следующие действия. Выбрать
 * столбец соответствующий данной вершине. Все вершины для которых в этом столбце стоит значение
 * больше нуля смежные ей.
 * ------------------------------------------------------------------------------------------------
 * Представление в виде списков смежности стоит применять для разреженных графов (число
 * ребер гораздо меньше квадрата количества вершин). Представление в виде матрица смежности
 * стоит использовать для плотных графов (количество ребер сопоставимо с квадратом количества
 * вершин).
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/Yvp0-Og2T28">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class GraphAdjacencyMatrix {

    public static final int INITIAL_MATRIX_SIZE = 100;

    private Node[] nodes;
    public int[][] adjacencyMatrix;
    private int matrixSize;
    private boolean isMatrixFull;

    public GraphAdjacencyMatrix() {
        matrixSize = INITIAL_MATRIX_SIZE;
        nodes = new Node[matrixSize];
        adjacencyMatrix = new int[matrixSize][matrixSize];
    }

    private class Node {
        final String id;
        Object data;

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

    //==================================================================================
    public static void main(String[] args) throws IllegalAccessException {
        GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix();

        // 1 - add nodes
        graph.addNode("a", 1);
        graph.addNode("b", 2);
        graph.addNode("c", 3);
        graph.addNode("d", 4);
        graph.addNode("e", 5);

        // 2 - add edges
        graph.addEdge("a", "b");
        graph.addEdge("a", "e");
        graph.addEdge("a", "d");
        graph.addEdge("a", "c");
        graph.addEdge("c", "b");

        // 3 - find node index by ID
        System.out.println(graph.findNodeIndexById("c"));

        // 4 - show graph
        System.out.println(graph);

        // 5 - remove edge "b-c"
		graph.removeEdge("b", "c");
		System.out.println(graph);

        // 6 - remove node "c"
//        graph.removeNode("c");
//        System.out.println(graph);

        // 7 - are they adjacent?
//        System.out.println(graph.isAdjacent("b", "c")); // true
//        System.out.println(graph.isAdjacent("c", "d")); // false

        // 8 - get adjacent nodes
//        System.out.println(Arrays.toString(graph.getAdjacentNodesIndexes("b")));
//        System.out.println(Arrays.toString(graph.getAdjacentNodesIndexes("a")));
//        System.out.println(Arrays.toString(graph.getAdjacentNodesIndexes("c")));
    }
}
