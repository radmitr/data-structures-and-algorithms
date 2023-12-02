def linear_search(sequince, element):
    for i in range(len(sequince)):
        if sequince[i] == element:
            return i
    return -1


sequince = [-2, 0, 3, 5, 7, 9, 11, 15, 18, 21]
element = 5

print(linear_search(sequince, element))
