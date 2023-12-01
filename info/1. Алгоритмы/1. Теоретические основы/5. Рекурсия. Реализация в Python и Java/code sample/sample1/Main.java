package sample1;

public class Main {

    public static void main(String[] args) {
        int number = 5;
        int sum = sumOfArithmeticProgression(number);
        System.out.println(sum);
    }

    public static int sumOfArithmeticProgression(int number) {
        if (number <= 0) {
            return 0;
        } else {
            return number + sumOfArithmeticProgression(number - 1);
        }
    }
}
