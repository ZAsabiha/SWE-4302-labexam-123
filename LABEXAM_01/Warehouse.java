package Lab_07.LABEXAM_01;

public class Warehouse {
	public int id;
	public String name;
	public String location;

	public Warehouse(int i, String name, String location) {
		id=i;
		this.name=name;
		this.location=location;
	}

}
public class Flavor {
    private String name;
    private double pricePerScoop;

    public Flavor(String name, double pricePerScoop) {
        this.name = name;
        this.pricePerScoop = pricePerScoop;
    }

    public String getName() {
        return name;
    }

    public double getPricePerScoop() {
        return pricePerScoop;
    }
}
public class Topping {
    private String name;
    private double price;

    public Topping(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
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
import java.io.*;

public class InvoiceGenerator {
    public static void generateInvoice(Order order, String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Ice Cream Shop Invoice");
            for (Flavor flavor : order.getFlavors()) {
                writer.printf("%s - 1 scoop: $%.2f%n", flavor.getName(), flavor.getPricePerScoop());
            }
            for (Map.Entry<Topping, Integer> entry : order.getToppings().entrySet()) {
                writer.printf("%s - %d time(s): $%.2f%n", entry.getKey().getName(), entry.getValue(),
                        entry.getKey().getPrice() * entry.getValue());
            }
            writer.printf("Subtotal: $%.2f%n", order.calculateSubtotal());
            writer.printf("Tax: $%.2f%n", order.calculateTax());
            writer.printf("Total Amount Due: $%.2f%n", order.calculateTotal());
        }
    }
}
import java.io.*;

public class InvoiceGenerator {
    public static void generateInvoice(Order order, String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Ice Cream Shop Invoice");
            for (Flavor flavor : order.getFlavors()) {
                writer.printf("%s - 1 scoop: $%.2f%n", flavor.getName(), flavor.getPricePerScoop());
            }
            for (Map.Entry<Topping, Integer> entry : order.getToppings().entrySet()) {
                writer.printf("%s - %d time(s): $%.2f%n", entry.getKey().getName(), entry.getValue(),
                        entry.getKey().getPrice() * entry.getValue());
            }
            writer.printf("Subtotal: $%.2f%n", order.calculateSubtotal());
            writer.printf("Tax: $%.2f%n", order.calculateTax());
            writer.printf("Total Amount Due: $%.2f%n", order.calculateTotal());
        }
    }
}
import java.util.Scanner;

public class IceCreamShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Order order = new Order();

        System.out.println("Welcome to the Ice Cream Shop!");
        // Add flavors and toppings to order (use scanner to take input from user)

        System.out.println("Generate invoice? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            try {
                InvoiceGenerator.generateInvoice(order, "invoice.txt");
                System.out.println("Invoice saved to invoice.txt");
            } catch (IOException e) {
                System.out.println("Error generating invoice: " + e.getMessage());
            }
        }
    }
}



