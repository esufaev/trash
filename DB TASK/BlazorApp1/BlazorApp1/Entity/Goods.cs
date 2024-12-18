namespace BlazorApp1.Entity;

public class Goods
{
    public string Id { get; set; }
    
    public string FactroyId { get; set; }
    
    public string Name { get; set; }
    
    public int Price { get; set; }
    
    public virtual Factory Factory { get; set; }
}