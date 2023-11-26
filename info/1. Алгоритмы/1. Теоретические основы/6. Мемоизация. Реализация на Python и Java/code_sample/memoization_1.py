

def get_memoization_function(fun):
    mem = dict()

    def mem_fun(*arg):
        if arg in mem:
            return mem[arg]
        else:
            value = fun(*arg)
            mem[arg] = value
            return value
    return mem_fun

@get_memoization_function
def factorial(n):
    if n <= 1:
        return 1
    else:
        return n*factorial(n-1)


print(factorial(5))
print(factorial(5))

