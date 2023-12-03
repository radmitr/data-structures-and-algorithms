mem = dict()


def fibonacci_sequince(n):
    if n in mem:
        return mem[n]
    if n == 0:
        mem[n] = 0
    elif n == 1:
        mem[n] = 1
    else:
        mem[n] = fibonacci_sequince(n-1)+fibonacci_sequince(n-2)
    return mem[n]


print(fibonacci_sequince(500))
