def counting_white_space_cyclically(text):
    n = 0
    for symbol in text:
        if symbol == " ":
            n = n+1
    return n


def counting_white_space_recursively(text):
    if len(text) == 0:
        return 0
    n = 0
    if text[0] == " ":
        n = 1
    return n + counting_white_space_recursively(text[1:])


def counting_white_space_tail_recursion(text, count=0):
    if len(text) == 0:
        return count
    n = 0
    if text[0] == " ":
        n = 1
    return counting_white_space_tail_recursion(text[1:], count+n)


text = "one two three"

print(counting_white_space_cyclically(text))
print(counting_white_space_recursively(text))
print(counting_white_space_tail_recursion(text))
