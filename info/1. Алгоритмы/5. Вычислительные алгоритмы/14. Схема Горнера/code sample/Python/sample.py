def gorner_scheme(poly_coff, x):
    result = poly_coff[0]
    for i in range(len(poly_coff) - 1):
        result = result * x + poly_coff[i + 1]
    return result

x = 2
poly_coff = [5, 1, -3, 2, 5]

print(gorner_scheme(poly_coff,x))