namespace BlazorApp1.Entity;

public class Courier
{
    public string Id { get; set; }
    
    public string Surname { get; set; }
    
    public string Name { get; set; }
    
    public string Patronymic { get; set; }
    
    public string CarId { get; set; }
    
    public virtual Car Car { get; set; }
}