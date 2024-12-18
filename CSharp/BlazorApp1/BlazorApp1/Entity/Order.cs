namespace BlazorApp1.Entity;

public class Order
{
    public string Id { get; set; }
    
    public string CustomerId { get; set; }
    
    public string GoodsId { get; set; }
    
    public string Date { get; set; }
    
    public string CourierId { get; set; }
    
    public virtual Courier Courier { get; set; }
    
    public virtual Customer Customer { get; set; }
    
    public virtual Goods Goods { get; set; }
}