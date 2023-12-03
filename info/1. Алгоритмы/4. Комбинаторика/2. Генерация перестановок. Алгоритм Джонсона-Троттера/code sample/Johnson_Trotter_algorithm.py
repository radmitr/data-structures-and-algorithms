
def find_max_mobile_element(permutation, direction):
    index = -1
    for i in range(len(permutation)):
        next_element_index = i + direction[i]
        if next_element_index >= 0 and next_element_index < len(permutation):
            if permutation[i] > permutation[next_element_index]:
                if index == -1:
                    index = i
                else:
                    if permutation[i] > permutation[index]:
                        index = i
    return index


def change_direction(permutation, direction, mobile_element):
    for i in range(len(permutation)):
        if permutation[i] > mobile_element:
            direction[i] = direction[i]*(-1)


def swap_element(permutation, direction, i, j):
    permutation[i], permutation[j] = permutation[j], permutation[i]
    direction[i], direction[j] = direction[j], direction[i]


def permutation_generator(n):
    permutation = list(range(1, n+1))
    direction = [-1]*n
    print(permutation)
    mobile_element_index = find_max_mobile_element(permutation, direction)
    while mobile_element_index != -1:
        mobile_element = permutation[mobile_element_index]
        next_index = mobile_element_index + direction[mobile_element_index]
        swap_element(permutation, direction, mobile_element_index, next_index)
        change_direction(permutation, direction, mobile_element)
        print(permutation)
        mobile_element_index = find_max_mobile_element(permutation, direction)


permutation_generator(3)
