namespace BlazorApp1.Entity;

public class Factory
{
    public string Id { get; set; }
    
    public string SuppliersId { get; set; }
    
    public virtual Suppliers Suppliers { get; set; }
}