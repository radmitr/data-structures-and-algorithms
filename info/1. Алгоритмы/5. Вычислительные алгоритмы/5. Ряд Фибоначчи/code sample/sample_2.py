def fibonacci_sequince(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fibonacci_sequince(n-1)+fibonacci_sequince(n-2)


print(fibonacci_sequince(50))
