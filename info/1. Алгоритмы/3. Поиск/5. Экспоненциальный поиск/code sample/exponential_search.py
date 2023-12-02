
def binary_search(sequence, required_element, start, end):
    while start <= end:
        m = (start + end)//2
        element = sequence[m]
        if element == required_element:
            return m
        if element < required_element:
            start = m + 1
        else:
            end = m - 1
    return None


def exponential_search(sequence, required_element):
    border = 1
    while border < len(sequence)-1 and sequence[border] < required_element:
        border = border * 2
    if border > len(sequence)-1:
        border = len(sequence)-1
    return binary_search(sequence, required_element, border//2, border)


sequince = [-2, 0, 3, 5, 7, 9, 11, 15, 18]
required_element = 5

print(exponential_search(sequince, required_element))
