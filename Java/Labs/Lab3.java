package Labs;

import java.util.Scanner;

public class Lab3 {
    public static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Задача 1.А
        System.out.print("Введите текст сообщения: ");
        String message = scanner.nextLine();
        System.out.print("Введите подстроку для поиска: ");
        String substring = scanner.next();
        System.out.println("Слова, содержащие подстроку \"" + substring + "\":");
        printWordsContainingSubstring(message, substring);

        // Задача 1.Б
        System.out.print("Введите символ для удаления слов: ");
        char endChar = scanner.next().charAt(0);
        String updatedMessage = removeWordsEndingWith(message, endChar);
        System.out.println("Сообщение после удаления слов: " + updatedMessage);

        // Задача 2
        System.out.print("Введите первое слово: ");
        String word1 = scanner.next();
        System.out.print("Введите второе слово: ");
        String word2 = scanner.next();
        int occurrences = countFirstLetterOccurrences(word1, word2);
        System.out.println("Количество вхождений первой буквы первого слова во втором: " + occurrences);

        // Задача 3
        System.out.print("Введите строку для замены: ");
        scanner.nextLine();
        String userString = scanner.nextLine();
        String modifiedString = capitalizeVowelStartingWords(userString);
        System.out.println("Строка после обработки: " + modifiedString);
    }

    // Задача 1.А
    public static void printWordsContainingSubstring(String message, String substring) {
        String[] words = message.split("[\\s,.;!?]+");
        for (String word : words) {
            if (word.contains(substring)) {
                System.out.println(word);
            }
        }
    }

    // Задача 1.Б
    public static String removeWordsEndingWith(String message, char endChar) {
        String[] words = message.split("[\\s,.;!?]+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.endsWith(String.valueOf(endChar))) {
                result.append(word).append(" ");
            }
        }
        return result.toString().trim();
    }

    // Задача 2
    public static int countFirstLetterOccurrences(String word1, String word2) {
        if (word1.isEmpty() || word2.isEmpty()) return 0;
        char firstLetter = word1.charAt(0);
        int count = 0;
        for (char c : word2.toCharArray()) {
            if (c == firstLetter) {
                count++;
            }
        }
        return count;
    }

    // Задача 3
    public static String capitalizeVowelStartingWords(String userString) {
        String[] words = userString.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty() && isVowel(word.charAt(0))) {
                word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
            }
            result.append(word).append(" ");
        }
        return result.toString().trim();
    }


}
