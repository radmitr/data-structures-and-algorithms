class Node:
    def __init__(self, data=None, next=None):
        self.data = data
        self.next = next

    def __str__(self):
        return str(self.date)


class Stack:

    def __init__(self):
        self.head = None

    def push(self, value):
        new_node = Node(value)
        new_node.next = self.head
        self.head = new_node

    def pop(self):
        if self.head is None:
            return
        value = self.head.data
        self.head = self.head.next
        return value

    def peek(self):
        if self.head is None:
            return
        value = self.head.data
        return value

    def size(self):
        length = 0
        current_node = self.head
        while current_node is not None:
            length += 1
            current_node = current_node.next
        return length

    def __str__(self):
        result = ""
        current_node = self.head
        while current_node is not None:
            result += str(current_node.data)+" -> "
            current_node = current_node.next
        else:
            result += "None"
        return result


stack = Stack()

stack.push(7)
stack.push(5)
stack.push(3)
print(stack)

val = stack.pop()

print(val)
print(stack)
