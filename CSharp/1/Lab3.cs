
public enum AccountType
{
    Current,
    Savings
}
struct BankAccount
{
    public string AccountNumber { get; set; }
    public AccountType Type { get; set; }
    public decimal Balance { get; set; }
}

enum University
{
    KGU,
    KAI,
    KHTI
}

struct Worker
{
    public string Name { get; set; }
    public University University { get; set; }
}
