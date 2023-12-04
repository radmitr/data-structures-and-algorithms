
def left_shift(sequince, k):
    temp = sequince[0]
    for i in range(k):
        sequince[i] = sequince[i+1]
    sequince[k] = temp


def print_all_permutation(sequince):
    k = len(sequince)-1
    n = k
    print(sequince)
    while k > 0:
        left_shift(sequince, k)
        if sequince[k] != k:
            print(sequince)
            k = n
        else:
            k = k-1


sequince = [0, 1, 2]

print_all_permutation(sequince)
