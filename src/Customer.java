public class Customer {
  private String username;
  private float balance;

  public Customer(String username, float balance) {
    this.username = username;
    this.balance = balance;
  }

  public boolean calculateCustomerBalance (float amount) {
    if (balance >= amount) {
      balance -= amount;
      return true;
    }
    return false;
  }

  public float getBalance() {
    return balance;
  }

  public String getName() {
    return username;
  }
  
}
