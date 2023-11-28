class Cat:
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def __str__(self):
        return "Cat[name = {}, age = {}]".format(self.name, self.age)


def get_min_max_key(sequince):
    min_key = min(sequince, key=lambda c: c.age).age
    max_key = max(sequince, key=lambda c: c.age).age
    return(min_key, max_key)


def counting_sort(sequince):
    min_max = get_min_max_key(sequince)
    min_key = min_max[0]
    max_key = min_max[1]
    n = max_key - min_key + 1
    support = [0 for i in range(n)]
    for element in sequince:
        support[element.age-min_key] += 1
    size = len(sequince)
    for i in range(n-1, -1, -1):
        size -= support[i]
        support[i] = size
    result = [None for i in range(len(sequince))]
    for element in sequince:
        result[support[element.age-min_key]] = element
        support[element.age-min_key] += 1
    return result


cat1 = Cat("Vaska", 2)
cat2 = Cat("Umka", 12)
cat3 = Cat("Luska", 6)
cat4 = Cat("Kuzia", 4)
cat5 = Cat("Murka", 5)
cat6 = Cat("Barsik", 6)

cats = [cat1, cat2, cat3, cat4, cat5, cat6]

result = counting_sort(cats)

for element in result:
    print(element)
