
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
    j = lo_index
    for i in range(lo_index + 1, hi_index + 1):
        if sequince[i] < support_element:
            j += 1
            sequince[i], sequince[j] = sequince[j], sequince[i]
    sequince[lo_index], sequince[j] = sequince[j], sequince[lo_index]
    return j


sequince = [0, 5, -2, 7, 3]

quick_sort(sequince)

print(sequince)
