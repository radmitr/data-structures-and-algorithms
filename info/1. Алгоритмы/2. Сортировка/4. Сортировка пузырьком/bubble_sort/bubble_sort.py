
list_1 = [5, 0, -2, 7, 3]

sorted_index = len(list_1)
while True:
    number_of_swap = 0
    for i in range(0, sorted_index-1):
        if list_1[i] > list_1[i+1]:
            list_1[i], list_1[i+1] = list_1[i+1], list_1[i]
            number_of_swap += 1
    sorted_index -= 1
    if number_of_swap == 0:
        break

print(list_1)
