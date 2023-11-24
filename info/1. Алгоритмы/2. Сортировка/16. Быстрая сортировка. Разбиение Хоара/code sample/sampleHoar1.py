def quick_sort(sequince, lo_index=None, hi_index=None):
    if lo_index is None:
        lo_index = 0
    if hi_index is None:
        hi_index = len(sequince)-1
    if lo_index >= hi_index:
        return None
    h = partition(sequince, lo_index, hi_index)
    quick_sort(sequince, lo_index, h-1)
    quick_sort(sequince, h+1, hi_index)


def partition(sequince, lo_index, hi_index):
    support_element = sequince[lo_index]
    i = lo_index + 1
    j = hi_index
    while True:
        while i < hi_index and sequince[i] < support_element:
            i += 1
        while sequince[j] > support_element:
            j -= 1
        if i >= j:
            break
        sequince[i], sequince[j] = sequince[j], sequince[i]
        i += 1
        j -= 1
    sequince[lo_index], sequince[j] = sequince[j], sequince[lo_index]
    return j


sequince = [0, 5, -2, 7, 3]

quick_sort(sequince)

print(sequince)
