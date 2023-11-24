class Graph:
    class Node:
        def __init__(self, node_id, node_data=None):
            self.node_id = node_id
            self.node_color = 0  # 0 - white, 1 - black
            self.node_data = node_data
            self.adjacent_nodes = set()

        def __str__(self):
            result = ""
            result += "node [id = "+str(self.node_id)+", data =  " + \
                str(self.node_data)+", color = "+str(self.node_color)+"]"
            return result

    def __init__(self):
        self.nodes = {}

    def find_node_by_id(self, node_id):
        return self.nodes.get(node_id)

    def add_node(self, node_id, node_data):
        if self.find_node_by_id(node_id) is not None:
            raise Exception("node already exists")
        new_node = self.Node(node_id, node_data)
        self.nodes[node_id] = new_node

    def del_node(self, node_id):
        delete_node = self.find_node_by_id(node_id)
        if delete_node is None:
            raise Exception("node does not exists")
        for nodes in delete_node.adjacent_nodes:
            nodes.adjacent_nodes.remove(delete_node)
        del (self.nodes[node_id])

    def add_edge(self, node_id_from, node_id_to):
        node_from = self.find_node_by_id(node_id_from)
        node_to = self.find_node_by_id(node_id_to)
        if node_from is None or node_to is None:
            raise Exception("node does not exist")
        node_from.adjacent_nodes.add(node_to)
        node_to.adjacent_nodes.add(node_from)

    def del_edge(self, node_id_from, node_id_to):
        node_from = self.find_node_by_id(node_id_from)
        node_to = self.find_node_by_id(node_id_to)
        if node_from is None or node_to is None:
            raise Exception("node does not exist")
        node_from.adjacent_nodes.remove(node_to)
        node_to.adjacent_nodes.remove(node_from)

    def is_adjacent_nodes(self, node_id_from, node_id_to):
        node_from = self.find_node_by_id(node_id_from)
        node_to = self.find_node_by_id(node_id_to)
        if node_from is None or node_to is None:
            raise Exception("node does not exist")
        return node_from in node_to.adjacent_nodes and node_to in node_from.adjacent_nodes

    def get_adjacent_nodes(self, node_id):
        node = self.find_node_by_id(node_id)
        if node is None:
            raise Exception("node does not exists")
        return node.adjacent_nodes

    def dfs(self, start_node_id):
        if type(start_node_id) is not self.Node:
            node = self.find_node_by_id(start_node_id)
            if node is None:
                raise Exception("node does not exists")
        else:
            node = start_node_id
        if node.node_color == 1:
            return
        node.node_color = 1
        for adj_node in node.adjacent_nodes:
            self.dfs(adj_node)

    def paint_nodes_to_white(self):
        for node_id in self.nodes:
            node = self.nodes.get(node_id)
            node.node_color = 0

    def is_connected_graph(self):
        result = True
        self.paint_nodes_to_white()
        for node_id in self.nodes:
            self.dfs(node_id)
            break
        for node_id in self.nodes:
            if self.nodes.get(node_id).node_color == 0:
                result = False
                break
        self.paint_nodes_to_white()
        return result

    def __str__(self):
        result = ""
        for node_id in self.nodes:
            node = self.nodes.get(node_id)
            result += str(node.node_id)+" -> "
            for adj_node in node.adjacent_nodes:
                result += str(adj_node.node_id) + "  "
            result += "\n"
        return result


graph_1 = Graph()

graph_1.add_node("a", None)
graph_1.add_node("b", None)
graph_1.add_node("c", None)
graph_1.add_node("d", None)
graph_1.add_node("e", None)

graph_1.add_edge("a", "b")
graph_1.add_edge("a", "c")
graph_1.add_edge("a", "e")
graph_1.add_edge("a", "d")
graph_1.add_edge("c", "b")


print(graph_1)

print(graph_1.is_connected_graph())

graph_1.del_edge("a","d")

print(graph_1.is_connected_graph())


