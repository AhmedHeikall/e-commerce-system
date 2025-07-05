import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
   public static void shipItems(List<Shippable> items) {
        if (items.isEmpty()) return;

        System.out.println("** Shipment notice **");

        double totalWeight = 0;
        Map<String, Integer> counts = new LinkedHashMap<>();

         for (Shippable item : items) {
            String name = item.getName();
            counts.put(name, counts.getOrDefault(name, 0) + 1);
            totalWeight += item.getWeight();
        }

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getValue() + "x " + entry.getKey());
        }

        System.out.printf("Total package weight: %.1fkg\n", totalWeight);
    }

    public static double calculateShippingFee(double weight) {
        return weight * 30; // shipping fee: 30 per kg
    }
}

