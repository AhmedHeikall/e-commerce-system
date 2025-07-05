import java.time.LocalDate;

public class Store {
  public static void main(String[] args) {
      Customer customer = new Customer("Ahmed Heikal", 2000);
      
      //EXAMPLE PRODUCTS
      Product cheese = new ExpirableProduct("c11","Cheese", 100, 5, LocalDate.now().plusDays(200));
      Product biscuits = new ExpirableProduct("b13","Biscuits", 150, 3, LocalDate.now().plusDays(7));
      Product scratchCard = new NonExpirableProduct("NEP4","Scratch Card", 50, 100);
      ShippableProduct tv = new ShippableProduct("NEP1", "TV", 20, 2500, 40);

      ShoppingCart cart = new ShoppingCart();

       cart.addItem(cheese, 2);
       cart.addItem(biscuits, 1);
       cart.addItem(scratchCard, 1);
      //  cart.addItem(tv, 1);

      CheckoutService.checkout(customer, cart);
  }
}

// ** Checkout receipt **
// 2x Cheese 10
// 1x Biscuits 3
// 1x Scratch Card 100
// ----------------------
// Subtotal 113
// Shipping 0
// Amount 113
// Balance after payment: 1887
