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


def merge_sort(sequince, support=None, start_index=None, stop_index=None):
    if support is None:
        support = sequince[::]
    if start_index is None:
        start_index = 0
    if stop_index is None:
        stop_index = len(sequince)-1
    if stop_index <= start_index:
        return None
    h = start_index + (stop_index-start_index)//2
    merge_sort(sequince, support, start_index, h)
    merge_sort(sequince, support, h+1, stop_index)
    merge(sequince, support, start_index, h, h+1, stop_index)


sequince = [0, 5, -2, 7, 3]

merge_sort(sequince)
print(sequince)
