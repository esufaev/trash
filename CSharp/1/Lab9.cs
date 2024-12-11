using System;
using System.Collections;

public class BankTransaction
{
    public DateTime Date { get; }
    public decimal Amount { get; }

    public BankTransaction(decimal amount)
    {
        Date = DateTime.Now;
        Amount = amount;
    }
}

public class BankAccount9
{
    private string accountNumber;
    private AccountType type;
    private decimal balance;
    private Queue transactions;

    public BankAccount9(string accountNumber, AccountType type, decimal initialBalance)
    {
        this.accountNumber = accountNumber;
        this.type = type;
        balance = initialBalance;
        transactions = new Queue();
    }

    public void Deposit(decimal amount)
    {
        balance += amount;
        AddTransaction(amount);
    }

    public void Withdraw(decimal amount)
    {
        if (amount > balance)
        {
            throw new InvalidOperationException("Недостаточно средств на счете.");
        }
        balance -= amount;
        AddTransaction(-amount);
    }

    private void AddTransaction(decimal amount)
    {
        BankTransaction transaction = new BankTransaction(amount);
        transactions.Enqueue(transaction);
    }

    public IEnumerable GetTransactions()
    {
        return transactions;
    }
}