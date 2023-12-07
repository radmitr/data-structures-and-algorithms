class Node:
    def __init__(self, data=None, next=None, prev = None):
        self.data = data
        self.next = next
        self.prev = prev

    def __str__(self):
        return str(self.date)


class DoublyLinkedList:

    def __init__(self):
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail
        self.tail.prev = self.head
        self.length = 0
    
    def add_first(self,value):
        add_node = Node(value, self.head.next, self.head)
        self.head.next.prev = add_node
        self.head.next = add_node
        self.length += 1
    
    def add_last(self, value):
        add_node = Node(value, self.tail, self.tail.prev)
        self.tail.prev.next = add_node
        self.tail.prev = add_node
        self.length += 1

    
    def remove_first(self):
        if self.is_empty():
            return
        remove_node = self.head.next
        self.head.next = remove_node.next
        remove_node.next.prev = self.head
        self.length -= 1


    def remove_last(self):
        if self.is_empty():
            return
        remove_node = self.tail.prev
        self.tail.prev = remove_node.prev
        remove_node.prev.next = remove_node.next
        self.length -= 1
    
    def remove_by_value(self, value):
        if self.is_empty():
            return
        current_node = self.head.next
        while current_node!=self.tail and current_node.data != value:
            current_node = current_node.next
        if current_node == self.tail:
            return
        current_node.prev.next = current_node.next
        current_node.next.prev = current_node.prev
        self.length -= 1

    def get_node_by_index(self, index):
        if index < 0 or index >= self.length:
            raise IndexError()
        if index > self.length // 2:
            number = self.length - 1
            current_node = self.tail.prev
            while number != index:
                current_node = current_node.prev
                number -=1
        else:
            number = 0
            current_node = self.head.next
            while number != index:
                current_node = current_node.next
                number +=1
        return current_node
    
    def get_by_index(self, index):
        if index < 0 or index >= self.length:
            raise IndexError()
        return self.get_node_by_index(index).data
    
    def remove_by_index(self, index):
        if index < 0 or index >= self.length:
            raise IndexError()
        remove_node = self.get_node_by_index(index)
        remove_node.next.prev = remove_node.prev
        remove_node.prev.next = remove_node.next
        remove_node.prev = None
        remove_node.next = None
        self.length -= 1

    def insert_by_index(self, index, value):
        if index < 0 or index >= self.length:
            raise IndexError()
        current_node = self.get_node_by_index(index)
        add_node = Node(value, current_node, current_node.prev)
        current_node.prev.next = add_node
        current_node.prev = add_node
        self.length += 1

    def set_by_index(self, index, value):
        if index < 0 or index >= self.length:
            raise IndexError()
        current_node = self.get_node_by_index(index)
        current_node.data = value

    
    def get_length(self):
        current_node = self.head.next
        length = 0
        while current_node != self.tail:
            length += 1
            current_node = current_node.next
        return length
    
    def is_empty(self):
        if self.head.next == self.tail:
            return True
        else:
            return False
    

    def clear(self):
        for i in range(self.length):
            self.remove_first()
        self.length = 0


    def __str__(self):
        current_node = self.head.next
        result = "["
        while current_node != self.tail:
            result += str(current_node.data) + " "
            current_node = current_node.next
        return result + "]"
    

my_list = DoublyLinkedList()
my_list.add_first(3)
my_list.add_last(7)
my_list.add_last(11)
my_list.add_last(13)
my_list.add_last(17)
my_list.add_last(19)

print(my_list)

my_list.set_by_index(4, -2)
print(my_list)

my_list.clear()
print(my_list)

