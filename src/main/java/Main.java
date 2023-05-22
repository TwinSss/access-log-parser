import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите первое число: ");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число: ");
        int secondNumber = new Scanner(System.in).nextInt();

        System.out.printf("Сумма чисел: %s + %s = %s%n", firstNumber, secondNumber, (firstNumber + secondNumber));
        System.out.printf("Разность чисел: %s - %s = %s%n", firstNumber, secondNumber, (firstNumber - secondNumber));
        System.out.printf("Произведение чисел: %s * %s = %s%n", firstNumber, secondNumber, (firstNumber * secondNumber));
        System.out.printf("Частное чисел: %s / %s = %s%n", (double)firstNumber, (double)secondNumber, (double)(firstNumber / secondNumber));

    }
}
