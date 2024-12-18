namespace BlazorApp1.Entity;

public class StoreBranch
{
    public string Id { get; set; }
    
    public string OrderId { get; set; }
    
    public string Name { get; set; }
    
    public virtual Order Order { get; set; }
}