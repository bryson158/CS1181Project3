import java.text.DecimalFormat;

public class Customer {
    private final double arrival;
    private final int items;
    private final double timeShopping;

    public Customer(double arrival, int items, double timeShopping) {
        this.arrival = arrival;
        this.items = items;
        this.timeShopping = (timeShopping * items);
    }

    public double getArrival() {
        return arrival;
    }

    public int getItems() {
        return items;
    }

    public double getTimeShopping() {
        return timeShopping;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "arrival=" + arrival +
                ", items=" + items +
                ", timeShopping=" + timeShopping +
                '}';
    }
}
