def merge(sequince, support, ls, le, rs, re):
    for i in range(ls, re+1):
        support[i] = sequince[i]
    l = ls
    r = rs
    for i in range(ls, re+1):
        if l > le:
            sequince[i] = support[r]
            r += 1
        elif r > re:
            sequince[i] = support[l]
            l += 1
        elif support[l] < support[r]:
            sequince[i] = support[l]
            l += 1
        else:
            sequince[i] = support[r]
            r += 1
    return None


def merge_sort(sequince):
    support = sequince[::]
    n = len(support)
    size = 1
    while size < n:
        j = 0
        while j < n-size:
            merge(sequince, support, j, j+size-1, j+size, min(j+2*size-1, n-1))
            j += 2*size
        size = size * 2
    return None


sequince = [5, 0, -2, 7, 3]

merge_sort(sequince)
print(sequince)
