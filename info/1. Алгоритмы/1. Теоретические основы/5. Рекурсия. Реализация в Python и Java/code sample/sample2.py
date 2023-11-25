import sys

sys.setrecursionlimit(10000)

print(sys.getrecursionlimit())


def factorial(number):
    if number <= 1:
        return 1
    else:
        return number * factorial(number-1)


print(factorial(9000))
