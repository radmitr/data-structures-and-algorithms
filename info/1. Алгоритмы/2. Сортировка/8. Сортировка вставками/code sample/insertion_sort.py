
list_1 = [5, 0, -2, 7, 3]

for i in range(1, len(list_1)):
    paste_element = list_1[i]
    while i > 0 and list_1[i-1] > paste_element:
        list_1[i] = list_1[i-1]
        i = i-1
    list_1[i] = paste_element


print(list_1)
