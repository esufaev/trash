namespace BlazorApp1.Entity;

public class Supply
{
    public string Id { get; set; }
    
    public string StoreBranchId { get; set; }
    
    public string Data { get; set; }
    
    public virtual StoreBranch StoreBranch { get; set; }
}