
sequince = [5, 0, -2, 7, 3]


def gnome_sort(sequince):
    index = 1
    i = 0
    n = len(sequince)
    while i < n-1:
        if sequince[i] <= sequince[i+1]:
            i, index = index, index + 1
        else:
            sequince[i], sequince[i+1] = sequince[i+1], sequince[i]
            i = i - 1
            if i < 0:
                i, index = index, index + 1



gnome_sort(sequince)


