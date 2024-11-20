import java.io.*;
import java.util.Map;

public class InvoiceGenerator {
    public static void generateInvoice(Order order, String fileName, String customMessage) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
        writer.println("Ice Cream Shop Invoice");
        writer.println("=======================");
        writer.println("Flavors:");
        for (Map.Entry<Flavor, Integer> entry : order.getFlavors().entrySet()) {
            writer.printf("%s - %d scoop(s): $%.2f%n", entry.getKey().getName(), entry.getValue(),
                    entry.getKey().getPrice() * entry.getValue());
        }

        writer.println("\nToppings:");
        for (Map.Entry<Topping, Integer> entry : order.getToppings().entrySet()) {
            writer.printf("%s - %d time(s): $%.2f%n", entry.getKey().getName(), entry.getValue(),
                    entry.getKey().getPrice() * entry.getValue());
        }

        writer.printf("%nSubtotal: $%.2f%n", order.calculateSubtotal());
        writer.printf("Tax: $%.2f%n", order.calculateSubtotal() * 0.08);
        writer.printf("Total Amount Due: $%.2f%n", order.calculateTotal());

        if (customMessage != null && !customMessage.isEmpty()) {
            writer.println("\nMessage from the shop:");
            writer.println(customMessage);
        }

        writer.println("=======================");
        writer.println("Thank you for visiting our ice cream shop!");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
