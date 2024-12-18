using BlazorApp1.Entity;

namespace BlazorApp1;

using Microsoft.EntityFrameworkCore;

public class MyContext : DbContext
{
    public DbSet<Car> Car => Set<Car>();
    
    public DbSet<Courier> Courier => Set<Courier>();
    
    public DbSet<Customer> Customer => Set<Customer>();
    
    public DbSet<Factory> Factory => Set<Factory>();
    
    public DbSet<Goods> Goods => Set<Goods>();
    
    public DbSet<Order> Order => Set<Order>();
    
    public DbSet<StoreBranch> StoreBranch => Set<StoreBranch>();
    
    public DbSet<Suppliers> Suppliers => Set<Suppliers>();
    
    public DbSet<Supply> Supply => Set<Supply>();
    
    public MyContext(DbContextOptions<MyContext> contextOptions) : base(contextOptions) { }
 
    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        optionsBuilder.UseNpgsql(@"Server=localhost;Port=5432;Database=postgres;User Id=postgres;Password=1331;");
    }
    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Car>(entity =>
        {
            entity.ToTable("car", "public");

            entity.Property(x => x.Id).HasColumnName("id").HasDefaultValueSql("generate_random_string()");

            entity.Property(x => x.Model).HasColumnName("model");
            entity.Property(x => x.Plate).HasColumnName("plate");
            
        });
        
        modelBuilder.Entity<Courier>(entity =>
        {
            entity.ToTable("courier", "public");
        });
        
        modelBuilder.Entity<Customer>(entity =>
        {
            entity.ToTable("customer", "public");
        });
        
        modelBuilder.Entity<Factory>(entity =>
        {
            entity.ToTable("factory", "public");
        });
        
        modelBuilder.Entity<Goods>(entity =>
        {
            entity.ToTable("goods", "public");

            entity.Property(x => x.Id).HasDefaultValueSql("generate_random_string()");
        });
        
        modelBuilder.Entity<Order>(entity =>
        {
            entity.ToTable("order", "public");
        });
        
        modelBuilder.Entity<StoreBranch>(entity =>
        {
            entity.ToTable("store_branch", "public");
        });
        
        modelBuilder.Entity<Suppliers>(entity =>
        {
            entity.ToTable("suppliers", "public");
        });
        
        modelBuilder.Entity<Supply>(entity =>
        {
            entity.ToTable("supply", "public");
        });
    }
}