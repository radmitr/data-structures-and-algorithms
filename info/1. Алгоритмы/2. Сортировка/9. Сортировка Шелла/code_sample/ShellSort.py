sequince = [5, 0, -2, 7, 3]


def shell_sort(sequince):
    n = len(sequince)
    step = n//2
    while step > 0:
        for i in range(step, n):
            j = i
            while j >= step and sequince[j] < sequince[j-step]:
                sequince[j], sequince[j-step] = sequince[j-step], sequince[j]
                j = j - step
        step = step // 2


print(sequince)
shell_sort(sequince)
print(sequince)
