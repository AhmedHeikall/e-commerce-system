/**
 * product (abstract) is a base class 
 * --> nonExpirableProduct
 * --> ExpirableProduct
 */

import java.time.LocalDate;

abstract class Product {
  private String id;
  private String name;
  private int quantity;
  private float price;
  
  //constructor
  public Product (String id, String name, int quantity, float price ){
      this.id = id;
      this.name = name;
      this.quantity = quantity;
      this.price = price;
  }
  
  //is abstract because not all products have the same logic for expiration
  public abstract boolean isExpired();

  public String getId() {return id;}
  public String getName() {return name;}
  public int getQuantity() {return quantity;}
  public float getPrice() {return price;}

  //display product information
  public void displayProduct() {
    System.out.println("ProductId: " + id + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity);
  }
  
  // one product is out of stock throw exception (not just print error)
  public void reduceQuantity(int amount) {
    if (quantity < amount) {
      throw new IllegalArgumentException("Sorry! product is out of stock.");
    }

    quantity -= amount;
  }
  
}

class ExpirableProduct extends Product {
  private LocalDate expirDate;

  public ExpirableProduct(String id, String name, int quantity, float price, LocalDate expirDate){
    super(id, name, quantity, price);
    this.expirDate = expirDate;
  }

  @Override
  public boolean isExpired() {
    // localdate represent date without time.
    return expirDate.isBefore(LocalDate.now()); 
  }

  public LocalDate getExpirDate(){
    return expirDate;
  } 
}

class NonExpirableProduct extends Product {

  public NonExpirableProduct(String id, String name, int quantity, float price) {
    super(id, name, quantity, price);
  }
  
  @Override
  public boolean isExpired() {
    return false;
  }
  
}

// Any object that can be shipped must be able to tell its name and weight
interface Shippable  {
  float getWeight();
  String getName();
}

// ShippableProduct is a Product and a Shippable thing.
class ShippableProduct extends Product implements Shippable {
  private float weight;

  public ShippableProduct(String id, String name, int quantity, float price, float weight) {
      super(id, name, quantity, price);
      this.weight = weight;
  }

  @Override
  public boolean isExpired() {
    return false;
  }

  @Override
  public float getWeight() {
    return weight;
  }

  @Override
  public String getName() {
    return super.getName();
  }
  
}

