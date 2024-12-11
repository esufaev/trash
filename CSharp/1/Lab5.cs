using System;

class Lab
{
    static int Max(int x, int y) => x > y ? x : y;

    static void Swap(ref int x, ref int y)
    {
        (y, x) = (x, y);
    }

    static bool TryFactorial(int n, out long result)
    {
        result = 1;
        try
        {
            checked
            {
                for (int i = 1; i <= n; i++)
                {
                    result *= i; 
                }
                return true; 
            }
        }
        catch (OverflowException)
        {
            result = 0; 
            return false; 
        }
    }

    static long RecursiveFactorial(int n) => n == 1 ? 1 : n * RecursiveFactorial(n - 1);

    static int GCD(int a, int b)
    {
        while (b != 0)
        {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static int GCD(int a, int b, int c) => GCD(GCD(a, b), c);

    static int Fibonacci(int n) => n <= 2 ? 1 : Fibonacci(n - 1) + Fibonacci(n - 2);

    class Book
    {
        public string Title { get; set; }
        public string Author { get; set; }
        public string Publisher { get; set; }

        public Book(string title, string author, string publisher)
        {
            Title = title;
            Author = author;
            Publisher = publisher;
        }

        public override string ToString()
        {
            return $"Название: {Title}, Автор: {Author}, Издательство: {Publisher}";
        }
    }

    class BookContainer
    {
        private Book[] books;

        public BookContainer(Book[] books)
        {
            this.books = books;
        }

        public delegate int BookComparer(Book b1, Book b2);

        public void Sort(BookComparer comparer)
        {
            for (int i = 0; i < books.Length - 1; i++)
            {
                for (int j = 0; j < books.Length - i - 1; j++)
                {
                    if (comparer(books[j], books[j + 1]) > 0)
                    {
                        (books[j + 1], books[j]) = (books[j], books[j + 1]);
                    }
                }
            }
        }

        public void Print()
        {
            foreach (var book in books)
            {
                Console.WriteLine(book);
            }
        }
    }


    static void Main(string[] args)
    {
        Console.WriteLine("Упражнение 5.1");
        Console.WriteLine($"Наибольшее число: {Max(10, 20)}\n");

        Console.WriteLine("Упражнение 5.2");
        int a = 5, b = 10;
        Console.WriteLine($"До обмена: a = {a}, b = {b}");
        Swap(ref a, ref b);
        Console.WriteLine($"После обмена: a = {a}, b = {b}\n");

        Console.WriteLine("Упражнение 5.3");
        if (TryFactorial(5, out long result))
        {
            Console.WriteLine($"Факториал: {result}");
        }
        else
        {
            Console.WriteLine("Переполнение при вычислении факториала");
        }

        Console.WriteLine("\nУпражнение 5.4");
        Console.WriteLine($"Рекурсивный факториал: {RecursiveFactorial(5)}\n");

        Console.WriteLine("Домашнее задание 5.1");
        Console.WriteLine($"НОД (2 числа): {GCD(36, 24)}");
        Console.WriteLine($"НОД (3 числа): {GCD(36, 24, 12)}\n");

        Console.WriteLine("Домашнее задание 5.2");
        Console.WriteLine($"Число Фибоначчи (n=7): {Fibonacci(7)}");

        // ------------------------------------------

        var books = new Book[]
        {
            new Book("C# Programming", "John Doe", "TechBooks"),
            new Book("Advanced C#", "Jane Smith", "CodePress"),
            new Book("Introduction to Programming", "Alice Johnson", "TechBooks"),
            new Book("Design Patterns", "Eric Gamma", "O'Reilly")
        };

        var container = new BookContainer(books);

        Console.WriteLine("Исходный список книг:");
        container.Print();

        Console.WriteLine("\nСортировка по названию:");
        container.Sort((b1, b2) => string.Compare(b1.Title, b2.Title, StringComparison.Ordinal));
        container.Print();

        Console.WriteLine("\nСортировка по автору:");
        container.Sort((b1, b2) => string.Compare(b1.Author, b2.Author, StringComparison.Ordinal));
        container.Print();

        Console.WriteLine("\nСортировка по издательству:");
        container.Sort((b1, b2) => string.Compare(b1.Publisher, b2.Publisher, StringComparison.Ordinal));
        container.Print();
    }
}