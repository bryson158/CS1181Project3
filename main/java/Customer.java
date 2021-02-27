public class Customer {
    private final double arrival;
    private final int items;
    private final int timeShopping;

    public Customer(double arrival, int items, int timeShopping) {
        this.arrival = arrival;
        this.items = items;
        this.timeShopping = timeShopping * items;
    }
}
