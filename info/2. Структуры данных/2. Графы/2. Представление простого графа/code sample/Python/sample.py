class Graph:

    class Node:
        def __init__(self, id, data):
            self.id = id
            self.data = data

    def __init__(self):
        self.node_list = []
        self.edge_list = []

    def find_node_by_id(self, id):
        for node in self.node_list:
            if node.id == id:
                return node
        return None

    def add_node(self, node_id, node_data=None):

        if self.find_node_by_id(node_id) is not None:
            raise Exception("Node with this id already exists")
        new_node = Graph.Node(node_id, node_data)
        self.node_list.append(new_node)

    def add_edge(self, node_id_a, node_id_b):
        node_from = self.find_node_by_id(node_id_a)
        node_to = self.find_node_by_id(node_id_b)
        if node_from is None or node_to is None:
            raise Exception("No node with this id")
        if node_from == node_to:
            raise Exception("Loop edge")
        self.edge_list.append((node_from, node_to))

    def remove_edge(self, node_id_a, node_id_b):
        node_from = self.find_node_by_id(node_id_a)
        node_to = self.find_node_by_id(node_id_b)

        if node_from is None or node_to is None:
            raise Exception("No node with this id")

        for i in range(len(self.edge_list)):
            edge = self.edge_list[i]
            if edge[0] == node_from and edge[1] == node_to:
                del (self.edge_list[i])
                return True
            elif edge[1] == node_from and edge[0] == node_to:
                del (self.edge_list[i])
                return True
        return False

    def remove_node(self, node_id):
        remove_node = self.find_node_by_id(node_id)
        if remove_node is None:
            raise Exception("No node with this id")
        remove_edge_list = []
        for edge in self.edge_list:
            if edge[0] == remove_node or edge[1] == remove_node:
                remove_edge_list.append(edge)
        for edge in remove_edge_list:
            self.edge_list.remove(edge)
        self.node_list.remove(remove_node)

    def adjacent(self, node_id_a, node_id_b):
        node_from = self.find_node_by_id(node_id_a)
        node_to = self.find_node_by_id(node_id_b)
        if node_from is None or node_to is None:
            raise Exception("No node with this id")
        for edge in self.edge_list:
            if edge[0] == node_from and edge[1] == node_to:
                return True
            if edge[1] == node_from and edge[0] == node_to:
                return True
        return False

    def neighbors(self, node_id):
        node_from = self.find_node_by_id(node_id)
        if node_from is None:
            raise Exception("No node with this id")
        neighbors_node_id = set()
        for edge in self.edge_list:
            if edge[0] == node_from:
                neighbors_node_id.add(edge[1].id)
            if edge[1] == node_from:
                neighbors_node_id.add(edge[0].id)
        return neighbors_node_id

    def __str__(self):
        result = "Nodes ["
        for node in self.node_list:
            result += str(node.id)+", "
        result = result[:-2] + "]\n"
        result += "Edges ["
        for edge in self.edge_list:
            result += str(edge[0].id)+" - "+str(edge[1].id)+", "
        result = result[:-2] + "]\n"
        return result


graph1 = Graph()

graph1.add_node("a")
graph1.add_node("b")
graph1.add_node("c")
graph1.add_node("d")
graph1.add_node("e")

graph1.add_edge("a", "e")
graph1.add_edge("a", "b")
graph1.add_edge("a", "c")
graph1.add_edge("a", "d")
graph1.add_edge("b", "c")

print(graph1)

# graph1.remove_edge("a", "b")
# print(graph1)

# graph1.remove_node("a")
# print(graph1)

# print(graph1.adjacent("a", "c"))
# print(graph1.adjacent("c", "d"))
# print(graph1.neighbors("c"))
