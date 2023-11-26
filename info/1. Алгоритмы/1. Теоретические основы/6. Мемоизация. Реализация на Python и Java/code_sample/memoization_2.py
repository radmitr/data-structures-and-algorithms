import functools
import math


@functools.lru_cache(1024)
def is_prime(number):
    for n in range(2, int(math.sqrt(number)+1)):
        if number % n == 0:
            return False
    return True


print(is_prime(2147483647))
