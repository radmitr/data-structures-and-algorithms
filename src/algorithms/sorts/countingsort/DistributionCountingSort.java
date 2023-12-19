package algorithms.sorts.countingsort;

/**
 * ------------------------------------------------------------------------------------------------
 * Сортировка распределяющим подсчетом
 * ------------------------------------------------------------------------------------------------
 * Описание сути алгоритма
 *
 * Сортировка распределяющим подсчетом является разновидностью сортировки подсчетом.
 * Используется для сортировки массивов данных, ключи сортировки которых представимы целыми
 * числами и их значения лежат в относительно узком диапазоне. Например, если нужно
 * отсортировать массив котов по возрасту, то этот алгоритм покажет очень высокое быстродействие.
 * Важным и положительным моментом является то, что это устойчивый алгоритм сортировки.
 * ------------------------------------------------------------------------------------------------
 * Сведение об алгоритме
 *
 * Сложность по времени в наихудшем случае O(n)
 * Требуется (n + диапазон ключей) дополнительной памяти
 * ------------------------------------------------------------------------------------------------
 * Описание алгоритма
 *
 * 1) Определяем минимальное и максимальное значение ключей сортировки в сортируемой
 *    последовательности (в дальнейшем sort) (обозначим их как min и max соответственно).
 *    Объявляем вспомогательную последовательность (в дальнейшем support), длинна которой
 *    вычисляется как max-min+1. Заполняем ее нулями.
 *
 * 2) Выполняем проход по sort, добавляем единицу к значению support[element-min] где element это
 *    значение ключа сортировки текущего элемента в sort.
 *
 * 3) Объявляем дополнительную переменную size, значение которой равно длине сортируемой
 *    последовательности. Выполняем проход по вспомогательной последовательности в обратном
 *    порядке. Устанавливаем текущий элемент равный разности size и текущего элемента.
 *    Уменьшаем size на значение текущего элемента.
 *
 * 4) Создаем последовательность размер которой совпадает с размером сортируемой
 *    последовательности.
 *
 * 5) Выполняем проход по сортируемой последовательности, размещаем текущий элемент на
 *    индекс, вычисляемый как support[element-min]. Увеличиваем значение в support[element-min] на
 *    единицу.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/RKhNpG7_fhI">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class DistributionCountingSort {

    public static void main(String[] args) {
        Cat cat1 = new Cat("Vaska", 2);
        Cat cat2 = new Cat("Umka", 12);
        Cat cat3 = new Cat("Luska", 6);
        Cat cat4 = new Cat("Kuzia", 4);
        Cat cat5 = new Cat("Murka", 5);
        Cat cat6 = new Cat("Barsik", 6);
        Cat[] cats = new Cat[] { cat1, cat2, cat3, cat4, cat5, cat6 };

        Cat[] result = distributionCountingSort(cats);
        for (Cat cat : result) {
            System.out.println(cat);
        }
    }

    public static int[] findMinMaxKey(Cat[] cats) {
        int minKey = cats[0].getAge();
        int maxKey = cats[0].getAge();
        for (Cat cat : cats) {
            if (cat.getAge() < minKey) {
                minKey = cat.getAge();
            }
            if (cat.getAge() > maxKey) {
                maxKey = cat.getAge();
            }
        }
        return new int[] { minKey, maxKey };
    }

    public static Cat[] distributionCountingSort(Cat[] cats) {
        int[] minMaxKey = findMinMaxKey(cats);
        int minKey = minMaxKey[0];
        int maxKey = minMaxKey[1];
        int n = maxKey - minKey + 1;
        int[] support = new int[n];
        for (Cat element : cats) {
            support[element.getAge() - minKey]++;
        }
        int size = cats.length;
        for (int i = support.length - 1; i >= 0; i--) {
            size -= support[i];
            support[i] = size;
        }
        Cat[] result = new Cat[cats.length];
        for (Cat cat : cats) {
            result[support[cat.getAge() - minKey]] = cat;
            support[cat.getAge() - minKey]++;
        }
        return result;
    }
}

class Cat {

    private String name;
    private int age;

    public Cat(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat [name=" + name + ", age=" + age + "]";
    }
}
