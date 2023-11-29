def get_number_of_digits(number):
    i = 1
    while(number >= 10**i):
        i = i+1
    return i


def get_digit(number, i):
    return number % (10**(i+1))//(10**i)


def max_number_of_digits(numbers):
    number_of_digits = 1
    for number in numbers:
        current_digits = get_number_of_digits(number)
        if current_digits > number_of_digits:
            number_of_digits = current_digits
    return number_of_digits


def counting_sort(sequince, position):
    min_key = min([get_digit(x, position) for x in sequince])
    max_key = max([get_digit(x, position) for x in sequince])
    n = max_key - min_key + 1
    support = [0 for i in range(n)]
    for element in sequince:
        support[get_digit(element, position)-min_key] += 1
    size = len(sequince)
    for i in range(n-1, -1, -1):
        size -= support[i]
        support[i] = size
    result = [None for i in range(len(sequince))]
    for element in sequince:
        result[support[get_digit(element, position)-min_key]] = element
        support[get_digit(element, position)-min_key] += 1
    return result


def radix_sort(sequince):
    number_of_digits = max_number_of_digits(sequince)
    for i in range(number_of_digits):
        sequince = counting_sort(sequince, i)
    return sequince


numbers = [121, 5, 24, 9, 32]

result = radix_sort(numbers)

print(result)
