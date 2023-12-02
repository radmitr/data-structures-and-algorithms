def modified_linear_search(sequince, element):
    sequince.append(element)
    i = 0
    while sequince[i] != element:
        i = i+1
    sequince.pop()
    if i != len(sequince):
        return i
    return -1


sequince = [-2, 0, 3, 5, 7, 9, 11, 15, 18, 21]
element = 5

print(modified_linear_search(sequince, element))

print(sequince)
