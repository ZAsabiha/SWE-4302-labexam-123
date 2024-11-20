import java.io.IOException;
import java.util.Scanner;

public class IceCreamShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Order order = new Order();

        
        Flavor mintChocolateChip = new Flavor("Mint Chocolate Chip", 2.80);
        Flavor chocolateFudge = new Flavor("Chocolate Fudge", 3.00);
        Topping sprinkles = new Topping("Sprinkles", 0.30);
        Topping marshmallow = new Topping("Marshmallow", 0.70);

        System.out.println("Welcome to the Ice Cream Shop!");

    
        System.out.println("After adding flavors...");
        order.addFlavor(mintChocolateChip, 2);
        order.addFlavor(chocolateFudge, 1);

    
        System.out.println("After adding toppings...");
        order.addTopping(sprinkles, 1);
        order.addTopping(marshmallow, 2);

        System.out.println("Waffle cone? (yes/no)");
        String servingChoice = scanner.nextLine();
        order.chooseWaffleCone(servingChoice.equalsIgnoreCase("yes"));

       
        try {
            InvoiceGenerator.generateInvoice(order, "invoice.txt");
            System.out.println("Invoice saved to invoice.txt");
        } catch (IOException e) {
            System.out.println("Error generating invoice: " + e.getMessage());
        }

        scanner.close();
    }
}
