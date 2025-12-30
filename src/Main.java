import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите первое число: ");
        int numOne = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число: ");
        int numTwo = new Scanner(System.in).nextInt();
        System.out.println("Сумма чисел: " + (numOne + numTwo));
        System.out.println("Разность чисел: " + (numOne - numTwo));
        System.out.println("Произведение чисел: " + (numOne * numTwo));
        System.out.println("Частное чисел: " + (double)(numOne / numTwo));
    }
}