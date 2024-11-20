import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class IceCreamShopTest {

    @Test
    void testAddFlavorWithValidQuantity() {
        Order order = new Order();
        Flavor chocolateFudge = new Flavor("Chocolate Fudge", 3.00);

        order.addFlavor(chocolateFudge, 3);

        assertEquals(3, order.getFlavors().get(chocolateFudge));
    }

    @Test
    void testAddFlavorWithInvalidQuantity() {
        Order order = new Order();
        Flavor mintChocolateChip = new Flavor("Mint Chocolate Chip", 2.80);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            order.addFlavor(mintChocolateChip, 0); 
        });

        assertEquals("Quantity of scoops must be greater than zero.", exception.getMessage());
    }

    @Test
    void testCalculateSubtotal() {
        Order order = new Order();
        Flavor strawberrySwirl = new Flavor("Strawberry Swirl", 2.75);
        Topping sprinkles = new Topping("Sprinkles", 0.30);

        order.addFlavor(strawberrySwirl, 2); 
        order.addTopping(sprinkles, 3);      

        double expectedSubtotal = (2 * 2.75) + (3 * 0.30);
        assertEquals(expectedSubtotal, order.calculateSubtotal(), 0.001);
    }

    @Test
    void testCalculateTotalWithTax() {
        Order order = new Order();
        Flavor pistachioDelight = new Flavor("Pistachio Delight", 3.25);
        Topping chocolateChips = new Topping("Chocolate Chips", 0.50);

        order.addFlavor(pistachioDelight, 1); 
        order.addTopping(chocolateChips, 4);  

        double subtotal = (1 * 3.25) + (4 * 0.50); 
        double expectedTotal = subtotal + (subtotal * 0.08); 

        assertEquals(expectedTotal, order.calculateTotal(), 0.001);
    }

    @Test
    void testInvoiceGeneration() throws IOException {
        Order order = new Order();
        Flavor mintChocolateChip = new Flavor("Mint Chocolate Chip", 2.80);
        Topping marshmallow = new Topping("Marshmallow", 0.70);

        order.addFlavor(mintChocolateChip, 2);
        order.addTopping(marshmallow, 1);
        order.chooseWaffleCone(true);

        String customMessage = "Thank you for your order!";
        String fileName = "test_invoice.txt";
        InvoiceGenerator.generateInvoice(order, fileName, customMessage);

    
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String content = reader.lines().reduce("", (acc, line) -> acc + line + "\n");
            assertTrue(content.contains("Mint Chocolate Chip - 2 scoop(s): $5.60"));
            assertTrue(content.contains("Marshmallow - 1 time(s): $0.70"));
            assertTrue(content.contains("Subtotal: $11.30")); 
            assertTrue(content.contains("Total Amount Due: $12.20")); 
            assertTrue(content.contains("Thank you for your order!"));
        }
    }
}
