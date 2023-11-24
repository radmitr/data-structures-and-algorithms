class HibbardStep:
    def __init__(self, sequince):
        self.i = 1
        while 2**self.i - 1 < len(sequince):
            self.i += 1

    def next_step(self):
        self.i -= 1
        step = 2**self.i - 1
        return step


class SedgewickStep:
    def __init__(self, sequince):
        self.i = 0
        number = 9*(2**self.i - 2**(self.i//2))+1
        while number < len(sequince):
            self.i += 1
            if self.i % 2 == 0:
                number = 9*(2**self.i - 2**(self.i//2))+1
            else:
                number = 8*2**self.i - 6 * 2**(self.i+1)//2 + 1

    def next_step(self):
        self.i -= 1
        if(self.i < 0):
            return 0
        if self.i % 2 == 0:
            step = 9*(2**self.i - 2**(self.i//2))+1
        else:
            step = 8*2**self.i - 6 * 2**(self.i+1)//2 + 1
        return step


class KnuthStep:
    def __init__(self, sequince):
        self.i = 1
        while (3**self.i - 1)//2 < len(sequince)//3:
            self.i += 1

    def next_step(self):
        step = (3**self.i - 1)//2
        self.i -= 1
        return step



def shell_sort(sequince, step_implements_class):
    n = len(sequince)
    step_generator = step_implements_class(sequince)
    step = step_generator.next_step()
    while step > 0:
        for i in range(step, n):
            j = i
            while j >= step and sequince[j] < sequince[j-step]:
                sequince[j], sequince[j-step] = sequince[j-step], sequince[j]
                j = j - step
        step = step_generator.next_step()


sequince = [5, 0, -2, 7, 3]
print(sequince)
shell_sort(sequince, KnuthStep)
print(sequince)

