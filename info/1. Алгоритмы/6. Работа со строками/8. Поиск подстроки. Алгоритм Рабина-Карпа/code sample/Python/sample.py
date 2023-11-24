def gorner_scheme(text):
    result = ord(text[0])
    base = 31
    for i in range(len(text) - 1):
        result = result * base + ord(text[i + 1])
    return result

def calculate_hash(text):
    q = 2147483647
    return gorner_scheme(text) % q

def search_text(text, sub_text):
    base = 31
    q = 2147483647
    sub_hash = calculate_hash(sub_text)
    m = len(sub_text)
    current_hash = calculate_hash(text[0:m])
    i = 0
    while True:
        if sub_hash == current_hash:
            if sub_text == text[i:i+m]:
                return i
        if i + m >= len(text):
            break
        current_hash = ((current_hash - ord(text[i]) * base ** (m-1)) * base + ord(text[i + m])) % q
        i = i + 1
    return None


text = "Awersome apple"
sub_text = "some"
print(search_text(text, sub_text))