package data_structures.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------------------------------------------------------------------
 * Бинарное дерево поиска
 * ------------------------------------------------------------------------------------------------
 * Бинарное дерево поиска (binary search tree, BST) — частный случай упорядоченного дерева. Его
 * особенностями являются следующие:
 * 1) Узел имеет не более двух дочерних узлов (отсюда название бинарное)
 * 2) Оба поддерева (левое и правое) также являются бинарными деревьями поиска
 * 3) У всех узлов левого поддерева произвольного узла значения ключей меньше, чем значение
 *    ключа самого узла
 * 4) У всех узлов правого поддерева произвольного узла значения ключей больше, чем значение
 *    ключа самого узла
 * Из определения бинарного дерева следует, что данные в каждом узле должны обладать
 * ключами, на которых определена операция сравнения.
 * ------------------------------------------------------------------------------------------------
 * Способы обхода дерева
 *
 * Так как дерево, это частный вид графа, то для обхода дерева реализуется теми же способами.
 * Дерево можно обходить как в глубину, так и в ширину. В качестве стартовой вершины всегда
 * выбирается корневой узел.
 * ------------------------------------------------------------------------------------------------
 * Обход дерева в глубину
 *
 * При обходе дерева в глубину очень эффективным оказывается следующий рекурсивный подход.
 * Сначала обрабатывается дочерняя вершина слева, текущая вершина, потом дочерняя вершина
 * справа. При таком обходе ключи дерева будут перебираться в возрастающем порядке.
 * Существуют и другие порядки обхода: левая, правая, узел и узел, левая, правая.
 * ------------------------------------------------------------------------------------------------
 * Обход дерева в ширину
 *
 * Обход дерева в ширину принципиально не отличается от обхода графа в ширину. Единственным
 * отличием будет факт постоянства выбора стартового узла (корневой узел).
 * ------------------------------------------------------------------------------------------------
 * Добавление элемента в дерево
 *
 * Проще всего реализовать добавление элемента используя следующий рекурсивный подход.
 * Если узла по ссылке нет, то формируем новый узел, добавляем и заканчиваем. Если есть, то
 * сравниваем ключ узла с ключом добавляемого. Если ключ добавляемого меньше то выполняем
 * переход по левому ребру текущего, если больше по правому.
 * ------------------------------------------------------------------------------------------------
 * Поиск элемента в дереве
 *
 * Наиболее оптимальным алгоритмом поиска является следующий рекурсивный подход — если
 * узла по ссылке нет, то заканчиваем (поиск неудачен). В противном случае проверяем значение ключа
 * текущего узла, если равен искомому то возвращаем данные (поиск успешен), если искомый ключ
 * меньше ключа узла то переходим по левому ребру, в противном случае по правому ребру.
 * ------------------------------------------------------------------------------------------------
 * Удаление узла
 *
 * При удалении узла следует рассматривать несколько случаев:
 * 1) У удаляемого узла нет дочерних узлов. В таком случае просто удаляем узел (не забывая
 *    удалить ребро у родительского).
 * 2) У удаляемого узла только один дочерний узел. Заменяем удаляемый узел на дочерний.
 * 3) У удаляемого узла два дочерних узла. В правом поддереве удаляемого узла ищем узел с
 *    минимальным значением ключа. Заменяем данные удаляемого ключа на данные найденного.
 *    Найденный узел удаляем.
 * ------------------------------------------------------------------------------------------------
 * Представление узла
 *
 * Наиболее простым способом представление узла является использование структуры где
 * хранится данные, ключ и ссылку (или указатель) на левый и правый узел.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/zznBMBouTKY">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class BinaryTree {

    private Node root;

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

//        @Override
//        public String toString() {
//            return String.valueOf(key);
//        }
    }

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
        root = deleteNodeRecursive(root, key);
    }

    private Node deleteNodeRecursive(Node node, int key) {
        if (node == null) {
            return null;
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
                node.rightNode = deleteNodeRecursive(node.rightNode, smallestNode.key);
                return node;
            }
        }
        if (key < node.key) {
            node.leftNode = deleteNodeRecursive(node.leftNode, key);
            return node;
        } else {
            node.rightNode = deleteNodeRecursive(node.rightNode, key);
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
        while (true) {
            while (nodes.size() > 0) {
                Node node = nodes.remove(0);
//                sb.append(node).append("\t");
                sb.append(node).append("\s\s\s");
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

    //==========================================================================
    public static void main(String[] args) {
        BinaryTree biTree = new BinaryTree();

        biTree.addNode(6, "Pear");
        biTree.addNode(4, "Apple");
        biTree.addNode(0, "Plum");
        biTree.addNode(5, "Orange");
        biTree.addNode(9, "Lemon");
        biTree.addNode(7, "Cherry");
        biTree.addNode(12, "Apricot");

        System.out.println(biTree.findByKey(12));
        System.out.println();

        System.out.println(biTree);
        System.out.println(biTree.size());
        System.out.println();

        biTree.deleteNode(4);
        System.out.println(biTree);
        System.out.println(biTree.size());
    }
}
