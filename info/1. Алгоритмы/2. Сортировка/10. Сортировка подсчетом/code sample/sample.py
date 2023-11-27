def counting_sort(sequince):
    min_value = min(sequince)
    max_value = max(sequince)
    support = [0 for i in range(max_value-min_value+1)]
    for element in sequince:
        support[element-min_value] += 1
    index = 0
    for i in range(len(support)):
        for element in range(support[i]):
            sequince[index] = i+min_value
            index += 1
    return None


sequince = [5, 0, -2, 7, 3, -2]
print(sequince)

counting_sort(sequince)
print(sequince)
