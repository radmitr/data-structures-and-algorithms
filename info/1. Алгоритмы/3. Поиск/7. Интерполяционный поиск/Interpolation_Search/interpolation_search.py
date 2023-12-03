
def interpolation_search(sequince, element):
    l = 0
    r = len(sequince)-1
    while sequince[l] < element and sequince[r] > element:
        if sequince[l] == sequince[r]:
            break
        index = (element - sequince[l]) * (l-r)//(sequince[l]-sequince[r]) + l
        if sequince[index] > element:
            r = index - 1
        elif sequince[index] < element:
            l = index + 1
        else:
            return index
    if sequince[l] == element:
        return l
    if sequince[r] == element:
        return r
    return -1


sequince = [-2, 0, 3, 5, 7, 9, 11, 15, 18]
element = 5

print(interpolation_search(sequince, element))
