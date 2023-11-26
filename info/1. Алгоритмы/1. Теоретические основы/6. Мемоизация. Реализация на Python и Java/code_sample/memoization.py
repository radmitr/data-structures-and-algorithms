
mem = dict()


def factorial(number):
    if number <= 1:
        return 1
    elif number in mem:
        return mem[number]
    else:
        fact = number * factorial(number - 1)
        mem[number] = fact
        return fact


print(factorial(5))
print(mem)
print(factorial(4))
