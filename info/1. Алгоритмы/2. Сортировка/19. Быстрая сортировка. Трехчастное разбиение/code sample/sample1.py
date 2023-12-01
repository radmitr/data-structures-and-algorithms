def quick_sort(sequince, lo_index=None, hi_index=None):
    if lo_index is None:
        lo_index = 0
    if hi_index is None:
        hi_index = len(sequince)-1
    if lo_index >= hi_index:
        return None
    h = partition(sequince, lo_index, hi_index)
    quick_sort(sequince, lo_index, h[0]-1)
    quick_sort(sequince, h[1]+1, hi_index)


def partition(sequince, lo_index, hi_index):
    support_element = sequince[lo_index]
    i = lo_index + 1
    gt = hi_index
    lt = lo_index
    while i <= gt:
        if sequince[i] < support_element:
            sequince[i], sequince[lt] = sequince[lt], sequince[i]
            i += 1
            lt += 1
        elif sequince[i] > support_element:
            sequince[i], sequince[gt] = sequince[gt], sequince[i]
            gt -= 1
        else:
            i += 1
    return [lt, gt]


sequince = [0, 5, -2, 7, 0, 3]

quick_sort(sequince)

print(sequince)
