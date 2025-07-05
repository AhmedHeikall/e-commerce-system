public class CheckoutService {
    public static void checkout(Customer customer, ShoppingCart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty.");
        }

        for (CartItem item : cart.getItems()) {
            if (item.isExpired()) {
                throw new IllegalStateException("Product " + item.getProduct().getName() + " is expired.");
            }
        }

        double subtotal = cart.getSubtotal();
        double totalWeight = cart.getTotalWeight();
        double shipping = ShippingService.calculateShippingFee(totalWeight);
        double totalAmount = subtotal + shipping;

        if (customer.getBalance() < totalAmount) {
            throw new IllegalStateException("Insufficient balance.");
        }

        // Reduce product quantities
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        customer.calculateCustomerBalance(totalAmount);

        // Shipping
        ShippingService.shipItems(cart.getShippableItems());

        // Print receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s %.0f\n", item.getQuantity(), item.getProduct().getName(), item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f\n", subtotal);
        System.out.printf("Shipping %.0f\n", shipping);
        System.out.printf("Amount %.0f\n", totalAmount);
        System.out.printf("Balance after payment: %.0f\n", customer.getBalance());
    }
} 
  

