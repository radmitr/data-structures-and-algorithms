def merge(sequince, ls, le, rs, re):
    result = sequince[::]
    l = ls
    r = rs
    for i in range(ls, re+1):
        if l > le:
            result[i] = sequince[r]
            r += 1
        elif r > re:
            result[i] = sequince[l]
            l += 1
        elif sequince[l] < sequince[r]:
            result[i] = sequince[l]
            l += 1
        else:
            result[i] = sequince[r]
            r += 1
    return result


sequince = [1, 3, 6, 9, 2, 4, 4, 7]

result = merge(sequince, 0, 3, 4, 7)

print(result)
