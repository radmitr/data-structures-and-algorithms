def text_to_number(text):
    radix = 256
    result = 0
    n = len(text)
    for i in range(n):
        result += ord(text[i]) * radix ** (n - 1 - i)
    return result

class HashService:

    def __init__(self, key_range, collision_level):
        self.prime_number = {10:7,20:19,50:47,100:97,250:241,500:499,1000:997,2500:2477,5000:4999,10000:9973}
        self.m = key_range // collision_level
        for k in self.prime_number.keys():
            if self.m < k:
                self.m = k
                break
    
    def generate_hash(self, text):
        return text_to_number(text) % self.m


text = "Hello world"

hash_service = HashService(2500,4)
print(hash_service.generate_hash(text))