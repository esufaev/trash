package Labs;

import java.util.Scanner;

public class Lab2 {
    public static double calculateAverage(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return (double) sum / array.length;
    }

    public static void swapMaxAndFirst(int[] array) {
        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        int temp = array[0];
        array[0] = array[maxIndex];
        array[maxIndex] = temp;
    }

    public static void swapRows(int[][] matrix) {
        if (matrix.length % 2 == 0) {
            for (int i = 0; i < matrix.length; i += 2) {
                int[] temp = matrix[i];
                matrix[i] = matrix[i + 1];
                matrix[i + 1] = temp;
            }
        }
    }

    public static String insertAfter(String s, char y, char x) {
        return s.replace(String.valueOf(y), y + String.valueOf(x));
    }

    public static int countLetters(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                count++;
            }
        }
        return count;
    }

    public static String removeSubstrings(String s, String substr) {
        return s.replace(substr, "");
    }

    public static void findFirstAndLastOccurrence(String s, char x) {
        int first = s.indexOf(x);
        int last = s.lastIndexOf(x);
        if (first != -1) {
            System.out.println("Первое вхождение: " + first);
            System.out.println("Последнее вхождение: " + last);
        } else {
            System.out.println("Символ не найден");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Упражнение 1.1
        System.out.print("Введите размер массива: ");
        int n = scanner.nextInt();
        int[] array = new int[n];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        double average = calculateAverage(array);
        System.out.println("Среднее арифметическое: " + average);

        // Упражнение 1.2
        swapMaxAndFirst(array);
        System.out.println("Массив после перестановки:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Упражнение 1.3
        System.out.print("Введите количество строк и столбцов двумерного массива: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] matrix = new int[rows][cols];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        swapRows(matrix);
        System.out.println("Массив после обработки:");
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        // Упражнение 2.1
        System.out.print("Введите строку: ");
        scanner.nextLine(); // Очистка буфера
        String s = scanner.nextLine();
        System.out.print("Введите символ y: ");
        char y = scanner.next().charAt(0);
        System.out.print("Введите символ x: ");
        char x = scanner.next().charAt(0);
        String modifiedString = insertAfter(s, y, x);
        System.out.println("Строка после вставки: " + modifiedString);

        // Упражнение 2.2
        int letterCount = countLetters(s);
        System.out.println("Количество букв в строке: " + letterCount);

        // Упражнение 2.3
        System.out.print("Введите подстроку для удаления: ");
        scanner.nextLine();
        String substr = scanner.nextLine();
        String removedSubstr = removeSubstrings(s, substr);
        System.out.println("Строка после удаления подстроки: " + removedSubstr);

        // Упражнение 2.4
        System.out.print("Введите символ для поиска: ");
        char findX = scanner.next().charAt(0);
        findFirstAndLastOccurrence(s, findX);
    }
}
