def find_max_index(permutation):
    for i in range(len(permutation) - 2, -1, -1):
        if permutation[i] < permutation[i+1]:
            return i
    return -1


def find_index_bigger_element(permutation, element):
    for i in range(len(permutation) - 1, -1, -1):
        if permutation[i] > element:
            return i
    return -1


def swap(permutation, i, j):
    permutation[i], permutation[j] = permutation[j], permutation[i]


def reverse_permutation(permutation, index):
    n = len(permutation)
    permutation = permutation[:index+1:] + permutation[n - 1:index:-1]
    return permutation


def permutation_generator(n):
    permutation = list(range(1, n+1))
    print(permutation)
    index = find_max_index(permutation)
    while index != -1:
        element = permutation[index]
        swap_index = find_index_bigger_element(permutation, element)
        swap(permutation, index, swap_index)
        permutation = reverse_permutation(permutation, index)
        print(permutation)
        index = find_max_index(permutation)


permutation_generator(3)
