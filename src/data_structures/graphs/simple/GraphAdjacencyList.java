package data_structures.graphs.simple;

import java.util.*;

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
 * Представление в виде списков смежности
 *
 * Представление графа в виде списка смежности подразумевает хранение списка вершин, в тоже время
 * сама вершина хранит список ссылок на смежные к ней вершины. Такой алгоритм довольно оптимален
 * для определения смежности двух вершин и поиска всех вершин смежных данной.
 *
 * 1. Для добавления вершины достаточно просто добавить ее в список вершин.
 *
 * 2. Для добавления ребра нужно найти инцидентные ему вершины. Для найденных вершин в списке
 * смежных вершин добавить вершину с противоположной стороны ребра.
 *
 * 3. При удалении вершины нужно перейти к смежным вершинам (ссылки на них есть в списке
 * смежных вершин). Для каждой смежной вершины в списке вершин удалить ссылку на удаляемую.
 * После этого удалять саму вершину.
 *
 * 4. При удалении ребра нужно перейти к вершинам на концах ребра. Для каждой вершины в списке
 * вершин удалить ссылку на удаляемую.
 *
 * 5. Проверка смежности вершин выполняется очевидным образом — переходим к одной из вершин
 * ребра и ищем в ее списке смежных вершин вторую вершину ребра. Если она есть то эти вершины
 * смежные.
 *
 * 6. Для получения всех вершин смежных данной достаточно просто вернуть ее список смежных
 * вершин.
 *
 * Для этого представления графа для описания вершины графа используется структура которая
 * хранит однозначный идентификатор вершины (порядковый номер, уникальное имя), и поле для
 * хранения данных связанных с этой вершиной и список ссылок на смежные с ней вершины.
 *
 * Для описания структуры данных представляющей такой граф, можно использовать несколько
 * подходов. Первый это действительно список вершин, второй это ассоциативный массив вершин. Во
 * втором случае возможны два варианта, если вершины хешируемые, то сама вершина выступает
 * ключем, а значение это список смежных с ней вершин. Если вершина не хешируема, то тогда ключ
 * это уникальный идентификатор, значение - вершина которая содержит список смежных вершин.
 * ------------------------------------------------------------------------------------------------
 * Представление в виде списков смежности стоит применять для разреженных графов (число
 * ребер гораздо меньше квадрата количества вершин). Представление в виде матрица смежности
 * стоит использовать для плотных графов (количество ребер сопоставимо с квадратом количества
 * вершин).
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/Yvp0-Og2T28">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class GraphAdjacencyList {

    private final Map<String, Node> nodes = new HashMap<>();

    private class Node {
        final String id;
        Object data;
        List<Node> adjacentNodes = new ArrayList<>();

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

    @Override
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
        GraphAdjacencyList graph = new GraphAdjacencyList();

        // 1 - add nodes
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addNode("d");
        graph.addNode("e");

        // 2 - add edges
        graph.addEdge("a", "b");
        graph.addEdge("a", "e");
        graph.addEdge("a", "d");
        graph.addEdge("a", "c");
        graph.addEdge("c", "b");

        // 3 - show graph
        System.out.println(graph);

        // 4 - remove edge "b-c"
//        graph.removeEdge("b", "c");
//        System.out.println(graph);

        // 5 - remove node "b"
//        graph.removeNode("b");
//        System.out.println(graph);

        // 6 - are they adjacent?
        System.out.println(graph.isAdjacent("b", "c"));

        // 7 - get adjacent nodes
        System.out.println(Arrays.toString(graph.getAdjacentNodesIds("a")));
    }
}
