using System;

public class BankAccount78
{
    private int accountNumber;
    private double balance;
    private AccountType type;

    public BankAccount78(int number, double balance, AccountType type)
    {
        accountNumber = number;
        this.balance = balance;
        this.type = type;
    }

    public int AccountNumber => accountNumber;
    public double Balance => balance;
    public AccountType Type => type;

    public override string ToString()
    {
        return $"Номер счета: {accountNumber}\n" +
               $"Баланс: {balance}\n" +
               $"Вид: {type}";
    }

    private static int nextAccountId = 1;

    public BankAccount78(double balance, AccountType type)
    {
        accountNumber = nextAccountId++;
        this.balance = balance;
        this.type = type;
    }

    public void Deposit(double amount)
    {
        balance += amount;
    }

    public bool Withdraw(double amount)
    {
        if (balance >= amount)
        {
            balance -= amount;
            return true;
        }
        return false;
    }

    //8

    public void getMoneyFrom(ref BankAccount78 other, double quantity)
    {
        balance += quantity;
        other.balance -= quantity;
    }

}

public class Building
{
    private static int nextBuildingId = 1;
    private int buildingId;
    private string name;
    private double height;
    private int floors;
    private int apartments;

    public Building(string name, double height, int floors, int apartments)
    {
        buildingId = nextBuildingId++;
        this.name = name;
        this.height = height;
        this.floors = floors;
        this.apartments = apartments;
    }

    public int BuildingId => buildingId;
    public string Name => name;
    public double Height => height;
    public int Floors => floors;
    public int Apartments => apartments;

    public double AverageFloorHeight => height / floors;

    public int ApartmentsPerFloor => apartments / floors;

    public override string ToString()
    {
        return $"Building ID: {buildingId}\n" +
               $"Name: {name}\n" +
               $"Height: {height} m\n" +
               $"Floors: {floors}\n" +
               $"Apartments: {apartments}\n" +
               $"Average floor height: {height / floors} m\n" +
               $"Apartments per floor: {apartments / floors}";
    }
}
