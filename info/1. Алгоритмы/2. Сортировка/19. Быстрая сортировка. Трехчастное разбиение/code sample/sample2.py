def quick_sort(sequince, lo_index=None, hi_index=None):
    if lo_index is None:
        lo_index = 0
    if hi_index is None:
        hi_index = len(sequince)-1
    if lo_index >= hi_index:
        return None
    h = partition(sequince, lo_index, hi_index)
    quick_sort(sequince, lo_index, h[0])
    quick_sort(sequince, h[1], hi_index)


def partition(sequince, lo_index, hi_index):
    support_element = sequince[lo_index]
    i = lo_index + 1
    g = hi_index + 1
    p = lo_index
    j = hi_index
    while True:
        while i < hi_index and sequince[i] < support_element:
            i += 1
        while sequince[j] > support_element:
            j -= 1
        if i >= j:
            if i == j and sequince[i] == support_element:
                p += 1
                sequince[i], sequince[p] = sequince[p], sequince[i]
            break
        sequince[i], sequince[j] = sequince[j], sequince[i]
        if sequince[i] == support_element:
            p += 1
            sequince[i], sequince[p] = sequince[p], sequince[i]
        if sequince[j] == support_element:
            g -= 1
            sequince[j], sequince[g] = sequince[g], sequince[j]
        i += 1
        j -= 1
    for k in range(lo_index, p+1):
        sequince[k], sequince[j] = sequince[j], sequince[k]
        j -= 1
    for k in range(hi_index, g+1, -1):
        sequince[i], sequince[k] = sequince[k], sequince[i]
        i += 1
    return [j, i]


sequince = [0, -1, -2, 0, 7, 3]
quick_sort(sequince)
print(sequince)
