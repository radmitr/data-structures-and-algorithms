sequince = [5, 0, -2, 7, 3]


def coctail_sort(sequince):
    left = 0
    right = len(sequince) - 1
    control = right
    while left < right:
        for i in range(left, right):
            if sequince[i] > sequince[i+1]:
                sequince[i], sequince[i+1] = sequince[i+1], sequince[i]
                control = i
        right = control
        for i in range(right, left, -1):
            if sequince[i] < sequince[i-1]:
                sequince[i], sequince[i-1] = sequince[i-1], sequince[i]
                control = i
        left = control


coctail_sort(sequince)

print(sequince)
