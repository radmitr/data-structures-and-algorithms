package algorithms.combinatorics.combinations;

/**
 * !!! Не корректно работает !!!
 * Эту программу выдал ИИ от Bing. Необходимо доработать.
 */
public class CombinationsRevolvingDoor2 {

    // функция, которая генерирует все сочетания из n элементов по k с помощью алгоритма вращающейся двери
    public static void generateCombinations(int n, int k) {
        // создаем массив дверей размером n
        boolean[] doors = new boolean[n];
        // открываем k самых левых дверей
        for (int i = 0; i < k; i++) {
            doors[i] = true;
        }
        // пока не получим последнее сочетание
        while (true) {
            // выводим текущее сочетание на экран
            printCombination(doors);
            // находим индекс активной двери
            int active = findActiveDoor(doors);
            // если активная дверь не найдена, то завершаем алгоритм
            if (active == -1) {
                break;
            }
            // переключаем активную дверь
            switchActiveDoor(doors, active);
            // выравниваем двери
            alignDoors(doors, active);
        }
    }

    // функция, которая выводит текущее сочетание на экран
    public static void printCombination(boolean[] doors) {
        // создаем пустую строку для хранения сочетания
        String combination = "";
        // перебираем все двери
        for (int i = 0; i < doors.length; i++) {
            // если дверь открыта, то добавляем соответствующий элемент в строку
            if (doors[i]) {
                combination += (i + 1) + " ";
            }
        }
        // выводим строку на экран
        System.out.println(combination);
    }

    // функция, которая находит индекс активной двери
    public static int findActiveDoor(boolean[] doors) {
        // перебираем все двери справа налево
        for (int i = doors.length - 1; i >= 0; i--) {
            // если дверь открыта и не соседствует с другой открытой дверью справа, то возвращаем ее индекс
            if (doors[i] && (i == doors.length - 1 || !doors[i + 1])) {
                return i;
            }
        }
        // если не нашли активную дверь, то возвращаем -1
        return -1;
    }

    // функция, которая переключает активную дверь
    public static void switchActiveDoor(boolean[] doors, int active) {
        // если активная дверь не самая правая, то перемещаем ее на одну позицию вправо
        if (active < doors.length - 1) {
            doors[active] = false;
            doors[active + 1] = true;
        }
        // иначе перемещаем ее на одну позицию влево
        else {
            doors[active] = false;
            doors[active - 1] = true;
        }
    }

    // функция, которая выравнивает двери
    public static void alignDoors(boolean[] doors, int active) {
        // открываем все двери слева от активной двери
        for (int i = 0; i < active; i++) {
            doors[i] = true;
        }
        // закрываем все двери справа от активной двери
        for (int i = active + 1; i < doors.length; i++) {
            doors[i] = false;
        }
    }

    // функция для тестирования
    public static void main(String[] args) {
        // задаем количество элементов и размер сочетаний
        int n = 5;
        int k = 3;
        // вызываем функцию generateCombinations и выводим результат на экран
        generateCombinations(n, k);
    }
}
