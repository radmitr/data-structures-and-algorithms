arr = [5, 0, -2, 7, 3]


def sift_up(arr, i):
    while i > 0:
        j = (i-1)//2
        if arr[i] > arr[j]:
            arr[i], arr[j] = arr[j], arr[i]
        else:
            break
        i = j


def sift_down(arr, i, last_index):
    while True:
        left_j = 2 * i + 1
        right_j = 2 * i + 2
        j = i
        if left_j < last_index and arr[left_j] > arr[j]:
            j = left_j
        if right_j < last_index and arr[right_j] > arr[j]:
            j = right_j
        if j != i:
            arr[i], arr[j] = arr[j], arr[i]
            i = j
        else:
            break


def heapify(arr):
    for i in range(len(arr)):
        sift_up(arr, i)


def heap_sort(arr):
    heapify(arr)
    last_index = len(arr) - 1
    while last_index > 0:
        arr[0], arr[last_index] = arr[last_index], arr[0]
        sift_down(arr, 0, last_index)
        last_index -= 1

print(arr)
print()
heap_sort(arr)
print(arr)