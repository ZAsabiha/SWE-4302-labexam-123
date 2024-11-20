import java.util.*;

public class Order {
    private List<Flavor> flavors = new ArrayList<>();
    private Map<Topping, Integer> toppings = new HashMap<>();
    private boolean isWaffleCone;
    private static final double WAFFLE_CONE_PRICE = 5.00;
    private static final double TAX_RATE = 0.08;

    public void addFlavor(Flavor flavor, int scoops) {
        for (int i = 0; i < scoops; i++) {
            flavors.add(flavor);
        }
    }

    public void addTopping(Topping topping, int times) {
        toppings.put(topping, toppings.getOrDefault(topping, 0) + times);
    }

    public void chooseWaffleCone(boolean isWaffleCone) {
        this.isWaffleCone = isWaffleCone;
    }

    public double calculateSubtotal() {
        double subtotal = 0;
        for (Flavor flavor : flavors) {
            subtotal += flavor.getPricePerScoop();
        }
        for (Map.Entry<Topping, Integer> entry : toppings.entrySet()) {
            subtotal += entry.getKey().getPrice() * entry.getValue();
        }
        if (isWaffleCone) {
            subtotal += WAFFLE_CONE_PRICE;
        }
        return subtotal;
    }

    public double calculateTax() {
        return calculateSubtotal() * TAX_RATE;
    }

    public double calculateTotal() {
        return calculateSubtotal() + calculateTax();
    }
}