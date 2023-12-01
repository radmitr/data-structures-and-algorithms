def median(sequince, lo, mid, hi):
    if sequince[lo] <= sequince[mid]:
        if sequince[mid] <= sequince[hi]:
            return mid
    else:
        if sequince[lo] <= sequince[hi]:
            return lo
    return hi


def tukey_median(sequince, lo, hi):
    part = len(sequince)//3
    median_a = median(sequince, lo, lo+part//2, lo+part)
    median_b = median(sequince, lo+part+1, lo+(3*part)//2+1, lo+2*part)
    median_c = median(sequince, lo+2*part+1, lo+(5*part)//2+1, hi)
    return median(sequince, median_a, median_b, median_c)


def insertion_sort(sequince, start_index, end_index):
    for i in range(start_index, end_index+1):
        paste_element = sequince[i]
        while i > start_index and sequince[i-1] > paste_element:
            sequince[i] = sequince[i-1]
            i = i-1
        sequince[i] = paste_element


def quick_sort(sequince, lo_index=None, hi_index=None):
    if lo_index is None:
        lo_index = 0
    if hi_index is None:
        hi_index = len(sequince)-1
    if hi_index-lo_index <= 32:
        insertion_sort(sequince, lo_index, hi_index)
        return None
    mid = tukey_median(sequince, lo_index, hi_index)
    sequince[lo_index], sequince[mid] = sequince[mid], sequince[lo_index]
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
