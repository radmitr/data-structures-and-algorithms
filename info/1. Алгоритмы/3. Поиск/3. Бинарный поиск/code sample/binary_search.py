sequence = [-2, 0, 3, 5, 7, 9, 11, 15, 18]



def find_element(sequence, required_element):
    l = 0
    r = len(sequence) - 1
    while l <= r:
        m = (l + r)//2
        element = sequence[m]
        if element == required_element:
            return m
        if element < required_element:
            l = m + 1
        else:
            r = m - 1
    return -1


print(find_element(sequence, 18))


