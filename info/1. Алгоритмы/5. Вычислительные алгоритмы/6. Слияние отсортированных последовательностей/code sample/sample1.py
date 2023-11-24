def merge(first_sequince, second_sequince):
    result = [0 for x in range(len(first_sequince)+len(second_sequince))]
    l = 0
    r = 0
    for i in range(len(result)):
        if l >= len(first_sequince):
            result[i] = second_sequince[r]
            r += 1
        elif r >= len(second_sequince):
            result[i] = first_sequince[l]
            l += 1
        elif first_sequince[l] < second_sequince[r]:
            result[i] = first_sequince[l]
            l += 1
        else:
            result[i] = second_sequince[r]
            r += 1
    return result


first_sequince = [1, 3, 6, 9]
second_sequince = [2, 4, 4, 7]

result = merge(first_sequince, second_sequince)

print(result)
