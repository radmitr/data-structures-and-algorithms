class Node:
    def __init__(self, data=None, next=None, prev = None):
        self.data = data
        self.next = next
        self.prev = prev

    def __str__(self):
        return str(self.date)


class Queue:

    def __init__(self):
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail
        self.tail.prev = self.head
        self.length = 0
    
    def enqueue(self,value):
        add_node = Node(value, self.head.next, self.head)
        self.head.next.prev = add_node
        self.head.next = add_node
        self.length += 1
    
    
    def dequeue(self):
        if self.is_empty():
            return
        remove_node = self.tail.prev
        self.tail.prev = remove_node.prev
        remove_node.prev.next = remove_node.next
        return_value = remove_node.data
        remove_node.next = None
        remove_node.prev = None
        self.length -= 1
        return return_value
    
    def peek(self):
        if self.is_empty():
            return
        remove_node = self.tail.prev
        return remove_node.data
  
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
            self.dequeue()
        self.length = 0

    def __str__(self):
        current_node = self.head.next
        result = "["
        while current_node != self.tail:
            result += str(current_node.data) + " "
            current_node = current_node.next
        return result + "]"
    

my_queue = Queue()

my_queue.enqueue(3)
my_queue.enqueue(5)
my_queue.enqueue(7)
my_queue.enqueue(11)

print(my_queue)

print(my_queue.dequeue())

print(my_queue)

my_queue.clear()

print(my_queue)
