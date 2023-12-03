
def fibonacci_sequince(n):
    result = 0
    next_element = 1
    index = 0
    while index < n:
        next_element, result = next_element+result, next_element
        index += 1
    return result


for i in range(10):
    print(i, " -> ", fibonacci_sequince(i))
