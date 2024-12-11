using booking_classes;
using System;

public class Program
{
    static void Main(string[] args)
    {
        //3

        AccountType accountType = AccountType.Savings;
        Console.WriteLine($"Тип счета: {accountType}");

        BankAccount account = new BankAccount
        {
            AccountNumber = "12531",
            Type = AccountType.Current,
            Balance = 1000.55m
        };
        Console.WriteLine($"Номер счета: {account.AccountNumber}, Тип: {account.Type}, Баланс: {account.Balance}");

        Worker worker = new Worker
        {
            Name = "Иван Иванов",
            University = University.KAI
        };
        Console.WriteLine($"Имя: {worker.Name}, ВУЗ: {worker.University}");

        //5

        int max(int a, int b)
        {
            if (a < b) { return a; } else { return b; }
        }

        void swap(ref int a, ref int b)
        {
            int temp = a;
            a = b;
            b = temp;
        }

        bool factorial(in int b, out int a)
        {
            try
            {
                int res = 1;
                checked
                {
                    for (int i = 2; i <= b; i++)
                    {
                        res *= i;
                    }
                }
                a = res;
            }
            catch (OverflowException e)
            {
                a = -1;
                return false;
            }
            return true;
        }

        int factorial_rec(int n)
        {
            if (n < 2) return 1;
            else return n * factorial_rec(n - 1);
        }

        int gcd(int a, int b)
        {
            if (a == b)
            {
                return a;
            }
            else
            {
                int new_num = Math.Abs(a - b);
                return gcd(b, new_num);
            }
        }

        int gcd3(int a, int b, int c)
        {
            return gcd(gcd(a, b), c);
        }

        int fib(int numbers_left)
        {
            if (numbers_left <= 2)
            {
                return 1;
            }
            else
            {
                return fib(numbers_left - 1) + fib(numbers_left - 2);
            }
        }

        int test_fact = 1;
        factorial(5, out test_fact);
        Console.WriteLine(test_fact);

        Console.WriteLine(gcd3(14, 21, 105));
        Console.WriteLine(fib(5));

        //7 и 8

        var account7 = new BankAccount78(12345, 1000, AccountType.Current);
        Console.WriteLine(account7.ToString());

        var building = new Building("SkyScraper", 200, 50, 1000);
        Console.WriteLine(building.ToString());

        Console.ReadLine();

        //9

        var account9 = new BankAccount9("12345678", AccountType.Current, 1000);
        account9.Deposit(500);
        account9.Withdraw(300);

        Console.WriteLine("История транзакций:");
        foreach (BankTransaction transaction in account9.GetTransactions())
        {
            Console.WriteLine($"Дата: {transaction.Date}, Сумма: {transaction.Amount}");
        }

        //12

        Book book1 = new Book("War and peace", "Leo Tolstoy", "Astra publishers");
        Book book2 = new Book("Crime and punishment", "Fyodor Dostoyevskiy", "Beta publishers");
        Book book3 = new Book("1984", "George Orwell", "Gamma publisher");

        BookShelf my_shelf = new BookShelf([book1, book2, book3]);

        my_shelf.sortBy("title");
        Console.WriteLine(my_shelf);
        Console.WriteLine();
        my_shelf.sortBy("author");
        Console.WriteLine(my_shelf);
        Console.WriteLine();
        my_shelf.sortBy("publisher");
        Console.WriteLine(my_shelf);
        Console.WriteLine();
    }
}
