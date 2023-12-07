class Queue:
    def __init__(self):
        self.data = [None for i in range(16)]
        self.head = 0
        self.tail = 0
        self.size = 0

    def enqueue(self, value):
        if (self.tail + 1) % len(self.data) == self.head:
            self.increase_size()
        self.data[self.tail] = value
        self.tail = (self.tail + 1) % len(self.data)
        self.size += 1

    def increase_size(self):
        self.capacity = self.capacity * 4/3 + 1
        new_date = [None for i in range(self.capacity)]
        add_index = 0
        while True:
            if (self.head % len(self.data) == self.tail):
                break
            new_date[add_index] = self.data[self.head]
            add_index += 1
            self.head = (self.head + 1) % len(self.data)
        self.data = new_date
        self.head = 0
        self.tail = add_index
        self.size = self.tail

    def dequeue(self):
        if self.head == self.tail:
            return None
        return_value = self.data[self.head]
        self.head = (self.head + 1) % len(self.data)
        self.size -= 1
        return return_value

    def peek(self):
        if self.head == self.tail:
            return None
        return self.data[self.head]

    def trim_to_size(self):
        new_date = [None for i in range(self.size + 1)]
        add_index = 0
        while True:
            if (self.head % len(self.data) == self.tail):
                break
            new_date[add_index] = self.data[self.head]
            add_index += 1
            self.head = (self.head + 1) % len(self.data)
        self.data = new_date
        self.head = 0
        self.tail = add_index

    def __str__(self):
        result = "["
        i = self.head
        while i != self.tail:
            result += str(self.data[i])+", "
            i = (i+1) % len(self.data)
        result = result[:-2] + "]"
        return result


queue = Queue()

queue.enqueue("Hello")
queue.enqueue("Python")
queue.enqueue("world")

print(queue)

print(queue.dequeue())

print(queue)
