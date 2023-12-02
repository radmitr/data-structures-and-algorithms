sequince = [-2, 0, 3, 5, 7, 9, 11, 15, 18]


def ternary_search(sequince, element):
    l = 0
    r = len(sequince)-1
    while l <= r:
        h = (r-l)//3
        m1 = l+h
        m2 = r-h
        if sequince[m1] == element:
            return m1
        if sequince[m2] == element:
            return m2
        if sequince[m1] < element < sequince[m2]:
            l = m1+1
            r = m2-1
        elif element < sequince[m1]:
            r = m1-1
        else:
            l = m2+1
    return None

find = ternary_search(sequince,8)
print(find)
