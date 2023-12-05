
class BinaryHeap:
    class Node:
        def __init__(self, key, data):
            self.key = key
            self.data = data

        def __str__(self):
            return "Node[key = {}, data = {}]".format(self.key, self.data)

    def __init__(self):
        self.nodes = []

    def add(self, key, data):
        self.nodes.append(BinaryHeap.Node(key, data))
        i = len(self.nodes) - 1
        self.sift_up(i)

    def extract(self):
        if len(self.nodes) == 0:
            return None
        if len(self.nodes) == 1:
            return self.nodes.pop()
        result = self.nodes[0]
        self.nodes[0] = self.nodes.pop()
        self.sift_down(0)
        return result

    def insert_and_extract(self, key, data):
        if len(self.nodes == 0):
            self.nodes.append(BinaryHeap.Node(key, data))
            return None
        result = self.nodes[0]
        self.nodes[0] = BinaryHeap.Node(key, data)
        self.sift_down(0)
        return result

    def delete_node(self, key):
        i = self.find_key_index(key)
        if i is None:
            return
        if len(self.nodes == 1):
            self.nodes.pop()
            return
        self.nodes[i] = self.nodes.pop()
        self.heap_recovery(i)

    def change_key(self, old_key, new_key):
        i = self.find_key_index(old_key)
        if i is None:
            return
        self.nodes[i].key = new_key
        self.heap_recovery(i)

    def heap_recovery(self, i):
        n = len(self.nodes)
        if (i-1)//2 >= 0 and self.nodes[i].key > self.nodes[(i-1)//2].key:
            self.sift_up(i)
            return
        self.sift_down(i)

    def find_key_index(self, key):
        result = None
        for i in range(len(self.nodes)):
            if self.nodes[i].key == key:
                return i
        return result

    def sift_up(self, i):
        while i > 0:
            j = (i-1)//2
            if self.nodes[i].key > self.nodes[j].key:
                self.nodes[i], self.nodes[j] = self.nodes[j], self.nodes[i]
            else:
                break
            i = j

    def sift_down(self, i):
        n = len(self.nodes)
        while True:
            left_j = 2 * i + 1
            right_j = 2 * i + 2
            largest = i
            if left_j <= n - 1 and self.nodes[left_j].key > self.nodes[largest].key:
                largest = left_j
            if right_j <= n - 1 and self.nodes[right_j].key > self.nodes[largest].key:
                largest = right_j
            if largest != i:
                self.nodes[i], self.nodes[largest] = self.nodes[largest], self.nodes[i]
                i = largest
            else:
                break

    def __str__(self):
        result = ""
        for node in self.nodes:
            result += str(node) + "\n"
        return result


heap = BinaryHeap()

heap.add(6, "Orange")
heap.add(7, "Apple")
heap.add(3, "Plum")
heap.add(4, "Lemon")
heap.add(5, "Pear")
heap.add(9, "Cherry")
heap.add(12, "Banana")

print(heap)

while True:
    my_date = heap.extract()
    if (my_date is None):
        break
    print(my_date)