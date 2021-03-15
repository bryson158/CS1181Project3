import java.util.LinkedList;
import java.util.Queue;

public class Checkout {
    //Include distinction between the express and non express lanes
    private Queue<Customer> checkoutLineQueue = new LinkedList();
    private final Boolean isExpress;
    private double checkoutTime;
    private boolean isOpen;

    public Checkout(Boolean isExpress, boolean isOpen) {
        this.isExpress = isExpress;
        this.isOpen = isOpen;
    }

    public int checkoutLineLength(){
        return checkoutLineQueue.size();
    }

    //This is the method the constructor uses to determine the amount of time per item it will take to checkout a
    // customer
    public void setCheckoutTime(boolean isExpress) {
        if(isExpress){
            //Time in minutes
            this.checkoutTime = 0.1;
        }
        else {
            //Time in minutes
            this.checkoutTime = 0.05;
        }
    }

    public void addCustomerToCheckoutLineQueue(Customer c){
        checkoutLineQueue.add(c);
    }
}
