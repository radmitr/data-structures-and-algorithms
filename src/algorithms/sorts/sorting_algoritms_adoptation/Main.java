package algorithms.sorts.sorting_algoritms_adoptation;

/**
 * ------------------------------------------------------------------------------------------------
 * Адаптация алгоритмов сортировки. Сортировка пользовательских типов данных.
 * ------------------------------------------------------------------------------------------------
 * Постановка задачи
 *
 * Большинство примеров алгоритмов сортировки демонстрируются для
 * последовательностей целых чисел. Однако очень часто возникают вопросы:
 *   ● Как адаптировать эти алгоритмы сортировки для сортировки
 *     пользовательских типов данных? Т.е. есть например класс Cat, как
 *     отсортировать последовательность объектов этого класса?
 *   ● Если в последовательности есть пустые ссылки (null, None и т.д.), как это учесть?
 * ------------------------------------------------------------------------------------------------
 * Описание способа адаптации
 *
 * В большинстве алгоритмов сортировки используется сравнение между собой двух
 * элементов последовательности. Для целых чисел для этого используют операторы
 * сравнения (такие как <, >, == и т.д). Для пользовательских типов данных чаще всего
 * такие операторы не применимы.
 * Одним из возможных решений является написание функции (метода), которая в
 * качестве параметров принимает два объекта пользовательского класса, а в качестве
 * результата своей работы возвращает целое число. В таком случае в алгоритме
 * сортировки можно использовать результат работы этой функции.
 * Правила работы такой функции сравнения можно сформулировать следующим
 * образом:
 *   ● Если первый объект больше второго, то вернуть любое положительное число.
 *   ● Если первый объект меньше второго, то вернуть любое отрицательное число.
 *   ● Если объекты равны, то вернуть ноль.
 *
 * Внимание!! Критерий сортировки вы определяете сами именно в этой
 * функции. Если нужна поддержка несколько критериев сортировки, то следует
 * написать несколько таких функций.
 * ------------------------------------------------------------------------------------------------
 * Как учесть наличие «пустых» объектов в последовательности
 *
 * В случае, если нужно учитывать наличие «пустых» объектов в последовательности,
 * то перед сравнением полей объекта стоит сравнить между собой именно ссылки на
 * объекты которые хранятся в последовательности.
 * Одним (но не единственным) способом сравнения может стать например такой:
 *   1) Если 1-й объект не «пустой», а второй «пустой» вернуть положительное число.
 *   2) Если 1-й объект «пустой», а второй не «пустой» вернуть отрицательное число.
 *   3) Если 1-й объект «пустой», и второй «пустой» вернуть 0.
 *
 * После этих проверок выполнить сравнение по значениям нужных полей.
 *
 * Внимание! Это стоит делать, только при вероятности нахождения «пустых»
 * объектов в последовательностях.
 * ------------------------------------------------------------------------------------------------
 * Постановка задачи
 *
 * 1) Создать пользовательский тип данных Cat (чаще всего это класс).
 *    Наделить его полями name, age.
 *
 * 2) Создать последовательность хранящую несколько объектов этого типа и
 *    также несколько «пустых» объектов (ссылки типа null, None в зависимости от
 *    используемого языка программирования).
 *
 * 3) Написать функцию сравнения для объектов этого типа. В качестве
 *    критерия сравнения выберем возраст.
 *
 * 4) Используя любой алгоритм сортировки (например сортировку выбором
 *    рассмотренную недавно) и эту функцию провести сортировку
 *    последовательности.
 * ------------------------------------------------------------------------------------------------
 * <a href="https://youtu.be/V3tyc5PIGv8">Ссылка на видео</a>
 * ------------------------------------------------------------------------------------------------
 */
public class Main {

    public static void main(String[] args) {
        Cat cat1 = new Cat("Vaska", 6);
        Cat cat2 = new Cat("Barsik", 2);
        Cat cat3 = new Cat("Umka", 12);
        Cat cat4 = new Cat("Kuzia", 4);

        Cat[] cats = new Cat[] { cat1, cat2, null, cat3, cat4 };

        for (int i = 0; i < cats.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < cats.length; j++) {
                if (compareCat(cats[minIndex], cats[j]) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Cat temp = cats[i];
                cats[i] = cats[minIndex];
                cats[minIndex] = temp;
            }
        }

        for (Cat cat : cats) {
            System.out.println(cat);
        }
    }

    public static int compareCat(Cat a, Cat b) {
        if (a != null && b == null) {
            return 1;
        }
        if (a == null && b != null) {
            return -1;
        }
        if (a == null && b == null) {
            return 0;
        }
        if (a.getAge() > b.getAge()) {
            return 1;
        }
        if (a.getAge() < b.getAge()) {
            return -1;
        }
        return 0;
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