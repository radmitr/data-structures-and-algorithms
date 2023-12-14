package datastructures.graphs;

import java.util.*;

/**
 * ------------------------------------------------------------------------------------------------
 * Способы представления простого графа
 * ------------------------------------------------------------------------------------------------
 * Распространенные способы представления простого графа
 *
 * Для создания структуры данных, с помощью которой можно представить простой граф, в информатике
 * чаще всего используется несколько подходов:
 *   ● Массив (список) ребер
 *   ● Массив (список) смежности
 *   ● Матрица смежности
 *
 * Вне зависимости от представления, должны поддерживаться следующие базовые операции:
 *   ● Добавление и удаление вершины
 *   ● Добавление и удаление ребра
 *   ● Проверка на смежность вершин
 *   ● Получение всех смежных вершин для данной
 *   ● Получение данных хранимых вершиной
 *   ● Установка новых данных хранимых в вершине
 * ================================================================================================
 * Массив (список) ребер
 *
 * Массив ребер. Используется массив или список, который хранит пары смежных вершин.
 * Таким образом наличие такой пары означает, что две вершины связаны.
 *
 * Для упрощения реализации можно отдельно хранить массив вершин и в списке ребер хранить
 * ссылки на соответствующую вершину. В таком случае значительно упроститься добавление вершин в
 * граф (достаточно просто добавить ее в массив) и также просто реализуется добавление и удаление
 * ребра (достаточно просто добавить или удалить пару из массива ребер). В то же время такая
 * реализация плохо подходит для определения, есть ли связь между двумя узлами (нужно перебрать
 * все элементы массива ребер) и довольно затратна по памяти.
 *
 * Для определения смежности двух вершин достаточно перебрать массив (список) ребер. Если
 * вершины смежные, то есть ребро их связывающее (по определению). Недостатком такого подхода
 * является необходимость полного перебора списка ребер.
 *
 * Для поиска всех вершин, смежных данной, нужно выделить все ребра (в которых встречается
 * данная вершина) и собрать множество вершин, являющихся инцидентными данному ребру, кроме
 * данной.
 *
 * Пожалуй одним из самых простых представлений вершины графа является структура, которая
 * хранит однозначный идентификатор вершины (порядковый номер, уникальное имя), и поле для
 * хранения данных связанных с этой вершиной.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/Yvp0-Og2T28">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class GraphEdgeList {

    private List<Node> nodeList = new ArrayList<>();
    private List<Edge> edgeList = new ArrayList<>();

    private class Node {
        final String id;
        Object data;

        public Node(String id) {
            this.id = id;
        }

        public Node(String id, Object data) {
            this.id = id;
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(id, node.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return id;
        }
    }

    private class Edge {
        final Node node1;
        final Node node2;

        public Edge(Node node1, Node node2) {
            this.node1 = node1;
            this.node2 = node2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return Objects.equals(node1, edge.node1) && Objects.equals(node2, edge.node2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(node1, node2);
        }

        @Override
        public String toString() {
            return node1 + "-" + node2;
        }
    }

    public Node findNodeById(String id) {
        for (Node node : nodeList) {
            if (node.id.equals(id)) {
                return node;
            }
        }
        return null;
    }

    public void addNode(String id, Object data) throws IllegalAccessException {
        if (findNodeById(id) != null) {
            throw new IllegalAccessException("Node with this ID already exists");
        }
        Node newNode = new Node(id, data);
        nodeList.add(newNode);
    }

    public void addNode(String id) throws IllegalAccessException {
        addNode(id, null);
    }

    public void addEdge(String id1, String id2) throws IllegalAccessException {
        Node node1 = findNodeById(id1);
        Node node2 = findNodeById(id2);
        if (node1 == null || node2 == null) {
            throw new IllegalAccessException("No node with this ID");
        }
        if (node1 == node2) {
            throw new IllegalAccessException("Loop edge");
        }
        edgeList.add(new Edge(node1, node2));
    }

    public boolean removeEdge(String id1, String id2) throws IllegalAccessException {
        Node node1 = findNodeById(id1);
        Node node2 = findNodeById(id2);
        if (node1 == null || node2 == null) {
            throw new IllegalAccessException("No node with this ID");
        }
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            if (edge.node1 == node1 && edge.node2 == node2) {
                edgeList.remove(i);
                return true;
            } else if (edge.node2 == node1 && edge.node1 == node2) {
                edgeList.remove(i);
                return true;
            }
        }
        return false;
    }

    public void removeNode(String id) throws IllegalAccessException {
        Node nodeForRemove = findNodeById(id);
        if (nodeForRemove == null) {
            throw new IllegalAccessException("No node with this ID");
        }
        List<Edge> edgesForRemove = new ArrayList<>();
        for (Edge edge : edgeList) {
            if (edge.node1 == nodeForRemove || edge.node2 == nodeForRemove) {
                edgesForRemove.add(edge);
            }
        }
        for (Edge edge : edgesForRemove) {
            edgeList.remove(edge);
        }
    }

    public boolean isAdjacent(String id1, String id2) throws IllegalAccessException {
        Node node1 = findNodeById(id1);
        Node node2 = findNodeById(id2);
        if (node1 == null || node2 == null) {
            throw new IllegalAccessException("No node with this ID");
        }
        for (Edge edge : edgeList) {
            if (edge.node1 == node1 && edge.node2 == node2) {
                return true;
            }
            if (edge.node2 == node1 && edge.node1 == node2) {
                return true;
            }
        }
        return false;
    }

    public Set<Node> getAdjacentNodes(String id) throws IllegalAccessException {
        Node node = findNodeById(id);
        if (node == null) {
            throw new IllegalAccessException("No node with this ID");
        }
        Set<Node> neighbors = new HashSet<>();
        for (Edge edge : edgeList) {
            if (edge.node1 == node) {
                neighbors.add(edge.node2);
            } else if (edge.node2 == node) {
                neighbors.add(edge.node1);
            }
        }
        return neighbors;
    }

    public Set<String> getAdjacentNodesIds(String id) throws IllegalAccessException {
        Node node = findNodeById(id);
        if (node == null) {
            throw new IllegalAccessException("No node with this ID");
        }
        Set<String> neighborsIds = new HashSet<>();
        for (Edge edge : edgeList) {
            if (edge.node1 == node) {
                neighborsIds.add(edge.node2.id);
            } else if (edge.node2 == node) {
                neighborsIds.add(edge.node1.id);
            }
        }
        return neighborsIds;
    }

    @Override
    public String toString() {
        return "Nodes " + nodeList + "\n" +
                "Edges " + edgeList;
    }

    //==================================================================================
    public static void main(String[] args) throws IllegalAccessException {
        GraphEdgeList graph1 = new GraphEdgeList();

        // 1 - add nodes
        graph1.addNode("a");
        graph1.addNode("b");
        graph1.addNode("c");
        graph1.addNode("d");
        graph1.addNode("e");

        // 2 - add edges
        graph1.addEdge("a", "e");
        graph1.addEdge("a", "b");
        graph1.addEdge("a", "c");
        graph1.addEdge("a", "d");
        graph1.addEdge("b", "c");

        // 3 - show graph
        System.out.println(graph1);

        // 4 - remove edge "a-b"
//        graph1.removeEdge("a", "b");
//        System.out.println(graph1);

        // 5 - remove node "a"
//        graph1.removeNode("a");
//        System.out.println(graph1);

        // 6 - are they adjacent?
        System.out.println(graph1.isAdjacent("a", "c"));
        System.out.println(graph1.isAdjacent("c", "a"));
        System.out.println(graph1.isAdjacent("c", "d"));

        // 7 - get adjacent nodes
        System.out.println(graph1.getAdjacentNodes("a"));
        System.out.println(graph1.getAdjacentNodes("c"));
        System.out.println(graph1.getAdjacentNodes("d"));
    }
}
