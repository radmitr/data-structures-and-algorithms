
class BinaryTree:
    class Node:
        def __init__(self, key, data=None, left_node=None, right_node=None):
            self.key = key
            self.data = data
            self.left_node = left_node
            self.right_node = right_node

        def __str__(self):
            return str(self.key)

    def __init__(self):
        self.root = None

    def add(self, key, data=None):

        def _add(key, data, node):
            if node is None:
                return BinaryTree.Node(key, data)
            if node.key == key:
                node.data = data
                return node
            if node.key > key:
                node.left_node = _add(key, data, node.left_node)
            else:
                node.right_node = _add(key, data, node.right_node)
            return node

        self.root = _add(key, data, self.root)

    def get(self, key):
        def _get(key, node):
            if node is None:
                return None
            if node.key == key:
                return node.data
            if node.key > key:
                return _get(key, node.left_node)
            else:
                return _get(key, node.right_node)
        return _get(key, self.root)

    def remove(self, key):

        def find_smallest_node(node):
            if node.left_node is None:
                return node
            else:
                return find_smallest_node(node.left_node)

        def _remove(key, node):
            if node is None:
                return node
            if node.key == key:
                if node.left_node is None and node.right_node is None:
                    return None
                if node.left_node is None:
                    return node.right_node
                if node.right_node is None:
                    return node.left_node
                if node.left_node is not None and node.right_node is not None:
                    smallest_node = find_smallest_node(node.right_node)
                    node.key = smallest_node.key
                    node.data = smallest_node.data
                    node.right_node = _remove(
                        smallest_node.key, node.right_node)
                    return node
            if node.key > key:
                node.left_node = _remove(key, node.left_node)
                return node
            if node.key < key:
                node.right_node = _remove(key, node.right_node)
                return node

        self.root = _remove(key, self.root)

    def size(self):
        def _size(node):
            if node is None:
                return 0
            return _size(node.left_node) + 1 + _size(node.right_node)
        return _size(self.root)

    def __str__(self):
        def bfs(node_list):
            result = ""
            if len(node_list) == 0:
                return result
            next_level = []
            for node in node_list:
                if node is not None:
                    result += str(node.key)+"\t"
                    next_level.append(node.left_node)
                    next_level.append(node.right_node)
            result += "\n" + bfs(next_level)
            return result
        return bfs([self.root])


binary_tree = BinaryTree()

binary_tree.add(6, "Pear")
binary_tree.add(4, "Apple")
binary_tree.add(0, "Plum")
binary_tree.add(5, "Orange")
binary_tree.add(9, "Lemon")
binary_tree.add(7, "Cherry")
binary_tree.add(12, "Apricot")

print(binary_tree)
print(binary_tree.size())

print(binary_tree.get(4))

binary_tree.remove(6)

print(binary_tree)
print(binary_tree.size())
