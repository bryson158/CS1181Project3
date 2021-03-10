public class Customer {
    private final double arrival;
    private final int items;
    private final double timeShopping;
    private double checkoutTime;

    public Customer(double arrival, int items, double timeShopping) {
        this.arrival = arrival;
        this.items = items;
        this.timeShopping = (timeShopping * items);
        checkoutTime = timeShopping + arrival;
    }

    //Get set methods for the customer
    public double getArrival() {
        return arrival;
    }

    public int getItems() {
        return items;
    }

    public double getTimeShopping() {
        return timeShopping;
    }

    public double getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(double checkoutTime) {
        this.checkoutTime = checkoutTime;
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
