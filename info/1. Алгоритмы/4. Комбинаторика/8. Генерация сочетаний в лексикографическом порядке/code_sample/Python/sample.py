def print_combination(k, n):
    comb = [i+1 for i in range(k)]
    while True:
        print(comb)
        m = -1
        for i in range(k-1, -1, -1):
            if comb[i] <= n-k+i:
                m = i
                comb[i] += 1
                break
        if m == -1:
            break
        for i in range(m+1, k):
            comb[i] = comb[i-1]+1


print_combination(3, 5)
