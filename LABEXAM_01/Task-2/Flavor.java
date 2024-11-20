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