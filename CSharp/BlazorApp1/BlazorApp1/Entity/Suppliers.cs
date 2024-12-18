namespace BlazorApp1.Entity;

public class Suppliers
{
    public string Id { get; set; }
    
    public string SupplyId { get; set; }
    
    public string PhoneNumber { get; set; }
    
    public virtual Supply Supply { get; set; }
}