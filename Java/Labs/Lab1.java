package Labs;

import java.util.Scanner;

public class Lab1 {
    public static int min(int a, int b) {
        return (a < b) ? a : b;
    }

    public static int f(int x) {
        return (x % 2 != 0) ? 0 : x / 2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Задача 1
        System.out.print("Введите координаты точки (x, y): ");
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();

        System.out.print("Выберите случай (а или б): ");
        char option = scanner.next().charAt(0);

        switch (option) {
            case 'а':
                if (x >= 0 && x * x + y * y < 81) {
                    System.out.println("Да");
                } else if (x >= 0 && x * x + y * y == 81) {
                    System.out.println("На границе");
                } else {
                    System.out.println("Нет");
                }
                break;

            case 'б':
                double rOuter = 100;
                double rInner = 25;
                if (x >= -10 && x <= 10 && y >= 0 && y * y + x * x < rOuter && y * y + x * x > rInner) {
                    System.out.println("Да");
                } else if (x >= -10 && x <= 10 && (y * y + x * x == rOuter || y * y + x * x == rInner)) {
                    System.out.println("На границе");
                } else {
                    System.out.println("Нет");
                }
                break;

            default:
                System.out.println("Неверный вариант. Выберите 'а' или 'б'.");
        }
        // Задача 2
        System.out.print("Введите A: ");
        int A = scanner.nextInt();
        System.out.print("Введите B: ");
        int B = scanner.nextInt();
        if (A < B) {
            for (int i = B - 1; i >= A; i--) {
                System.out.println(i * i * i);
            }
        }

        // Задача 3
        int n = 5;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j >= 1; j--) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }

        // Задача 4
        int X = 3;
        int Y = 4;
        int z = min(3 * X, 2 * Y) + min(X - Y, X + Y);
        System.out.println("z = " + z);

        // Задача 5
        int[] numbers = {1, 2, 3, 4, 5, 6};
        for (int num : numbers) {
            System.out.println(f(num));
        }
    }
}
