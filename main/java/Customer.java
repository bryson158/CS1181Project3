import java.text.DecimalFormat;

public class Customer {
    private final double arrival;
    private final int items;
    private final double timeShopping;
    private double checkoutTime;
    private double departureTime;

    public Customer(double arrival, int items, double timeShopping) {
        DecimalFormat df = new DecimalFormat("#####.##");
        this.arrival = arrival;
        this.items = items;
        //Time spent shopping converted to seconds
        this.timeShopping = (Double.parseDouble(df.format((timeShopping * items))));
        //Customer arrives at registers
        this.checkoutTime = (Double.parseDouble(df.format(timeShopping + arrival)));
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

    public double getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(double departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "arrival=" + arrival +
                ", items=" + items +
                ", timeShopping=" + timeShopping +
                " checkoutTime=" + checkoutTime +
                '}';
    }
}