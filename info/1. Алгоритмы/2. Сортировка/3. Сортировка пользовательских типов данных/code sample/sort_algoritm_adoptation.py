class Cat:

    def __init__(self, name, age):
        self.name = name
        self.age = age

    def __str__(self):
        return "Cat[name = {}, age = {}]".format(self.name, self.age)


cat_1 = Cat("Vaska", 6)
cat_2 = Cat("Barsik", 2)
cat_3 = Cat("Umka", 12)
cat_4 = Cat("Kuzia", 4)


cats = [cat_1, cat_2, None, cat_3, cat_4]


def compare_cat(cat_a, cat_b):
    if cat_a is not None and cat_b is None:
        return 1
    if cat_a is None and cat_b is not None:
        return -1
    if cat_a is None and cat_b is None:
        return 0
    if cat_a.age > cat_b.age:
        return 1
    if cat_a.age < cat_b.age:
        return -1
    return 0


for i in range(0, len(cats)-1):
    min_index = i
    for j in range(i+1, len(cats)):
        if compare_cat(cats[min_index], cats[j]) > 0:
            min_index = j
    if min_index != i:
        temp = cats[i]
        cats[i] = cats[min_index]
        cats[min_index] = temp


for cat in cats:
    print(cat)
