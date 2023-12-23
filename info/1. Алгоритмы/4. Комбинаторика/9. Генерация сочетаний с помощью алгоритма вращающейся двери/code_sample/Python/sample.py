def check_first_element(c, k):
    j = 1
    if k % 2 != 0:
        if c[0]+1 < c[1]:
            c[0] += 1
            step = 2
        else:
            j = 1
            step = 4
    else:
        if c[0] > 0:
            c[0] -= 1
            step = 2
        else:
            j = 1
            step = 5
    return (step, j)


def decreas_element(c, j):
    if c[j] > j:
        c[j] = c[j-1]
        c[j-1] = j-1
        step = 2
    else:
        j += 1
        step = 5
    return (step, j)


def enlargement_element(c, j, k):
    if c[j]+1 <= c[j+1]:
        c[j-1] = c[j]
        c[j] += 1
        step = 2
    else:
        j += 1
        if j >= k:
            step = -1
        else:
            step = 4
    return (step, j)


def print_combination(k, n):
    c = [i for i in range(k)]
    c.append(n-1)
    step = 2
    j = 1
    while True:
        if step == 2:
            print(c[:k])
            step = 3
        elif step == 3:
            (step, j) = check_first_element(c, k)
        elif step == 4:
            (step, j) = decreas_element(c, j)
        elif step == 5:
            (step, j) = enlargement_element(c, j, k)
        else:
            break


print_combination(3, 5)
