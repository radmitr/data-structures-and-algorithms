
list_1 = [5, 0, -2, 7, 3]

for i in range(0, len(list_1)-1):
    min_index = i
    for j in range(i+1, len(list_1)):
        if list_1[min_index] > list_1[j]:
            min_index = j
    if min_index != i:
        temp = list_1[i]
        list_1[i] = list_1[min_index]
        list_1[min_index] = temp

print(list_1)
