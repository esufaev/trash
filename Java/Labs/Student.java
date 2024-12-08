package Labs;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {
    private String surname;
    private String name;
    private LocalDate birthDate;
    private char gender;

    public Student() {
        this.surname = "";
        this.name = "";
        this.birthDate = LocalDate.now();
        this.gender = '-';
    }

    public Student(String surname, String name, LocalDate birthDate, char gender) {
        this.surname = surname;
        this.name = name;
        this.birthDate = birthDate;
        setGender(gender);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        if (gender == 'М' || gender == 'Ж') {
            this.gender = gender;
        } else {
            System.out.println("Некорректный ввод пола. Используйте 'М' для мужского или 'Ж' для женского.");
            this.gender = '-';
        }
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public void printInfo() {
        System.out.println("Фамилия: " + surname);
        System.out.println("Имя и инициалы: " + name.charAt(0) + ".");
        System.out.println("Возраст: " + getAge() + " лет");
        System.out.println("Пол: " + (gender == 'М' ? "Мужской" : gender == 'Ж' ? "Женский" : "Неопределён"));
    }

    public void inputInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите фамилию: ");
        this.surname = scanner.nextLine();

        System.out.print("Введите имя: ");
        this.name = scanner.nextLine();

        System.out.print("Введите дату рождения (в формате yyyy-MM-dd): ");
        String birthDateString = scanner.nextLine();
        this.birthDate = LocalDate.parse(birthDateString, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Введите пол (М/Ж): ");
        setGender(scanner.next().charAt(0));
    }

    public void saveToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(surname + "," + name + "," + birthDate + "," + gender + "\n");
        }
    }

    public static Student loadFromFile(String data) {
        String[] parts = data.split(",");
        return new Student(
                parts[0],
                parts[1],
                LocalDate.parse(parts[2]),
                parts[3].charAt(0)
        );
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = "students.txt";
        System.out.print("Введите количество студентов: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        Student[] students = new Student[count];

        for (int i = 0; i < count; i++) {
            System.out.println("\nВвод данных для студента " + (i + 1));
            students[i] = new Student();
            students[i].inputInfo();
            students[i].saveToFile(fileName);
        }


        List<Student> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Student.loadFromFile(line));
            }
        }

        System.out.println("\nИнформация о студентах:");
        for (Student student : list) {
            student.printInfo();
            System.out.println();
        }
    }
}