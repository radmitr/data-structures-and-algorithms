class FibonacciSearch:
    def __init__(self):
        self.i = 0
        self.p = 0
        self.q = 0
        self.stop = False

    def get_fibonacci_number(self, k):
        first_number = 0
        second_number = 1
        n = 0
        while n < k:
            temp_number = second_number
            second_number = first_number + second_number
            first_number = temp_number
            n = n + 1
        return first_number

    def start_init(self, sequince):
        self.stop = False
        k = 0
        n = len(sequince)
        while (self.get_fibonacci_number(k+1) < len(sequince)):
            k = k+1
        m = self.get_fibonacci_number(k+1)-(n+1)
        self.i = self.get_fibonacci_number(k) - m
        self.p = self.get_fibonacci_number(k-1)
        self.q = self.get_fibonacci_number(k-2)

    def up_index(self):
        if self.p == 1:
            self.stop = True
        self.i = self.i + self.q
        self.p = self.p - self.q
        self.q = self.q - self.p

    def down_index(self):
        if self.q == 0:
            self.stop = True
        self.i = self.i-self.q
        temp = self.q
        self.q = self.p - self.q
        self.p = temp

    def search(self, sequince, element):
        self.start_init(sequince)
        result_index = -1
        while not self.stop:
            if self.i < 0:
                self.up_index()
            elif self.i >= len(sequince):
                self.down_index()
            elif sequince[self.i] == element:
                result_index = self.i
                break
            elif element < sequince[self.i]:
                self.down_index()
            elif element > sequince[self.i]:
                self.up_index()
        return result_index


sequince = [-2, 0, 3, 5, 7, 9, 11, 15, 18, 21]


fs = FibonacciSearch()

element = 7

print(fs.search(sequince, element))
