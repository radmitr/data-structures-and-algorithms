def get_permutation(sequince):
    n = len(sequince)
    a = sequince[::]
    b = [i for i in range(n)]
    c = [0 for i in range(n+1)]
    k = 1
    j = 1
    while True:
        yield a
        k = 1
        while c[k] == k:
            c[k] = 0
            k = k + 1
        if k == n:
            return
        c[k] += 1
        a[0], a[b[k]] = a[b[k]], a[0]
        j = 1
        k = k-1
        while j < k:
            b[j], b[k] = b[k], b[j]
            j = j+1
            k = k-1


sequince = ["a", "b", "c"]

for p in get_permutation(sequince):
    print(p)
