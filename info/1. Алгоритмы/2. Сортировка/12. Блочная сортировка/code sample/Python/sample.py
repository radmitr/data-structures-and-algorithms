def insertion_sort(sequence):
    for i in range(1, len(sequence)):
        paste_element = sequence[i]
        while i > 0 and sequence[i-1] > paste_element:
            sequence[i] = sequence[i-1]
            i = i-1
        sequence[i] = paste_element


def find_min_max(sequence):
    if len(sequence) == 0:
        return [0, 0]
    min_max = [sequence[0], sequence[0]]
    for element in sequence:
        if element < min_max[0]:
            min_max[0] = element
        if element > min_max[1]:
            min_max[1] = element
    return min_max


def bucket_sort(sequence, n):
    buckets = []
    for i in range(n):
        buckets.append([])
    min_max = find_min_max(sequence)
    if(min_max[0] == min_max[1]):
        return
    for element in sequence:
        buckets[(n * (element - min_max[0])) //
                (min_max[1]-min_max[0]+1)].append(element)
    for bucket in buckets:
        if(len(bucket) <= 32):
            insertion_sort(bucket)
        else:
            bucket_sort(bucket, n)
    insert_index = 0
    for bucket in buckets:
        for element in bucket:
            sequence[insert_index] = element
            insert_index += 1


list_1 = [12, 2, 4, 7, 5, 10, 8, 9, 11, 9]


bucket_sort(list_1, 5)

print(list_1)
