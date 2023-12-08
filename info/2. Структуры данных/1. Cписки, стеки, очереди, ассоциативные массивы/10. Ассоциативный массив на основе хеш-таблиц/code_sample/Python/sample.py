class HashTable:

    class Node:
        def __init__(self, key, value):
            self.key = key
            self.value = value

    def __init__(self, load_factor=0.75):
        self.capacity = 16
        self.size = 0
        self.load_factor = load_factor
        self.hash_table = [[] for i in range(self.capacity)]

    def put(self, key, value):
        n = hash(key) % self.capacity
        for node in self.hash_table[n]:
            if node.key == key:
                node.value = value
                break
        else:
            self.hash_table[n].append(HashTable.Node(key, value))
            self.size += 1
        if self.size > self.capacity * self.load_factor:
            self.size_up()

    def get(self, key):
        n = hash(key) % self.capacity
        for node in self.hash_table[n]:
            if node.key == key:
                return node.value
        return None

    def delete(self, key):
        n = hash(key) % self.capacity
        for i in range(len(self.hash_table[n])):
            if self.hash_table[n][i].key == key:
                del self.hash_table[n][i]
                self.size -= 1

    def size_up(self):
        self.capacity *= 2
        new_hash_table = [[] for i in range(self.capacity)]
        for node_list in self.hash_table:
            for node in node_list:
                n = hash(node.key) % self.capacity
                new_hash_table[n].append(node)
        self.hash_table = new_hash_table

    def __str__(self):
        result = "{"
        for node_list in self.hash_table:
            for node in node_list:
                result += str(node.key)+":"+str(node.value)+", "
        result = result[:-2]+"}"
        return result


h_table = HashTable()

h_table.put("one", 1)
h_table.put("ten", 10)
h_table.put("two", 2)
h_table.put("five", 5)
h_table.put("four", 4)

h_table.delete("ten")

print(h_table)
print(h_table.get("one"))
