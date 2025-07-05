import java.util.ArrayList;
import java.util.List;

class CartItem {
  Product product;
  int quantity;

  public CartItem(Product product, int quantity) {
    if (quantity > product.getQuantity()) {
      throw new IllegalArgumentException("not more than available product quantity.");
    }
    this.product = product;
    this.quantity = quantity;
  }

  public float getTotalPrice() {
    return product.getPrice() * quantity;
  }

  public boolean isShippable() {
    return product instanceof Shippable;
  }

  public boolean isExpired() {
    return product.isExpired();
  }

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }
}

class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public float getSubtotal() {
        float subtotal = 0;
        for (CartItem item : items) {
          subtotal += item.getTotalPrice(); 
        }
        return subtotal;
    }

    public float getTotalWeight() {
        float totalWeight = 0;
        for (CartItem item : items) {
          if (item.isShippable()) {
            Shippable product = (Shippable) item.getProduct();
            totalWeight += product.getWeight() * item.getQuantity();
          }
        }
        return totalWeight;
    }

    public List<Shippable> getShippableItems() {
        List<Shippable> list = new ArrayList<>();
        for (CartItem item : items) {
            if (item.isShippable()) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    list.add((Shippable) item.getProduct());
                }
            }
        }
        return list;
    }
}