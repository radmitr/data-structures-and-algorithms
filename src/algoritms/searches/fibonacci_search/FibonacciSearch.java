package algoritms.searches.fibonacci_search;

/**
 * ------------------------------------------------------------------------------------------------
 * Поиск Фибоначчи
 * ------------------------------------------------------------------------------------------------
 * Сведение о алгоритме
 *
 * Алгоритм поиска Фибоначчи.
 * Сложность по времени в наихудшем случае O(ln(n))
 * Затраты памяти O(n)
 * ------------------------------------------------------------------------------------------------
 * Принцип работы алгоритма
 *
 * 1) Последовательность сортируется в возрастающем порядке. Если последовательность уже
 *    отсортирована, то этот пункт можно пропустить.
 *
 * 2) Производится начальная инициализация. Нужно найти такое число k, что F(k + 1) ≥ N + 1.
 *    После чего нужно ввести следующие значения:
 *      M = F(k + 1) - (N + 1), i = F(k) - M, p = F(k - 1), q = F(k - 2).
 *
 * 3) Проверить корректность индекса. Если индекс меньше нуля, перейти к 5. Если индекс больше
 *    или равен N, перейти к 4. Выполнить сравнение K < Ki, если да то перейти к 4. Если K > Ki,
 *    перейти к 5. K = Ki, тогда вернуть i, поиск успешен.
 *
 * 4) Выполнить проверку q = 0. Если да, поиск неудачен, закончить выполнение.
 *    В противном случае установить:
 *      i = i - q
 *      выполнить обмен (p, q) = (q, p - q)
 *      Перейти к 3.
 *
 * 5) Выполнить проверку p = 1. Если да, поиск неудачен, закончить выполнение.
 *    В противном случае установить:
 *      i = i + q
 *      p = p - q
 *      q = q - p
 *      Перейти к 3.
 *
 * Тут N — число элементов в последовательности. K — искомый элемент. Ki — элемент
 *         последовательности расположенный на i — индексе. F(k) — k — й элемент
 *         в последовательности Фибоначчи
 * ------------------------------------------------------------------------------------------------
 * Последовательность Фибоначчи
 *
 * Последовательность Фибоначчи {Fn} — последовательность, которая задаётся линейным
 * рекуррентным соотношением:
 *   F0 = 0, F1 = 1, Fn = Fn−1 + Fn−2, n≥2, n∈ℤ
 *
 * Числа Фибоначчи - элементы числовой последовательности Фибоначчи:
 *   0, 1, 1, 2, 3, 5, 8, 13, 21...
 *
 * Т.е. нулевое число это 0, первое 1, каждое последующее вычисляется как сумма двух
 * предыдущих.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/x_mQ41D_Uao">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class FibonacciSearch {

    private int i;
    private int p;
    private int q;
    private boolean stop = false;

    public FibonacciSearch() {
    }

    private void init(int[] sequence) {
        stop = false;
        int k = 0;
        int n = sequence.length;
        while (getFibonacciNumber(k + 1) < n + 1) {
            k += 1;
        }
        int m = (int) (getFibonacciNumber(k + 1) - (n + 1));
        i = (int) (getFibonacciNumber(k) - m);
        p = (int) getFibonacciNumber(k - 1);
        q = (int) getFibonacciNumber(k - 2);
    }

    public long getFibonacciNumber(int k) {
        long firstNumber = 0;
        long secondNumber = 1;
        for (int i = 0; i < k; i++) {
            long temp = secondNumber;
            secondNumber += firstNumber;
            firstNumber = temp;
        }
        return firstNumber;
    }

    private void upIndex() {
        if (p == 1) {
            stop = true;
        }
        i = i + q;
        p = p - q;
        q = q - p;
    }

    private void downIndex() {
        if (q == 0) {
            stop = true;
        }
        i = i - q;
        int temp = q;
        q = p - q;
        p = temp;
    }

    public int search(int[] sequence, int element) {
        init(sequence);
        int n = sequence.length;
        int resultIndex = -1;
        while (!stop) {
            if (i < 0) {
                upIndex();
            } else if (i >= n) {
                downIndex();
            } else if (sequence[i] == element) {
                resultIndex = i;
                break;
            } else if (element < sequence[i]) {
                downIndex();
            } else if (element > sequence[i]) {
                upIndex();
            }
        }
        return resultIndex;
    }

    //==========================================================================
    public static void main(String[] args) {
        int[] sequence = { -2, 0, 3, 5, 7, 9, 11, 15, 18, 21 };
        int element = 7;

        FibonacciSearch fs = new FibonacciSearch();

        int index = fs.search(sequence, element);
        System.out.println(index + " -> " + element);
    }
}
