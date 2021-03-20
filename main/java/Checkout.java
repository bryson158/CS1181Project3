import java.util.ArrayList;

public class Checkout {
    //Include distinction between the express and non express lanes
    private ArrayList<Double> checkoutTimes= new ArrayList<>();
    private Boolean isExpress;

    public Checkout(Boolean isExpress) {
        this.isExpress = isExpress;
    }

    //Returns the size of the array list
    public int getCheckoutLineSize (){
        return checkoutTimes.size();
    }

    //Removes a time from the arrayList
    public void removeTimeFromCheckoutLine (double time){
        checkoutTimes.remove(time);
        checkoutTimes.sort(Double::compareTo);
    }

    public void addCustomerToCheckoutLine(Customer c){
        double finalCheckoutTime = 0;
        if(checkoutTimes.size() != 0){
            for(int i = 0; i < checkoutTimes.size(); i++){
                
            }
        }
    }

    public boolean getIsExpress(){
        return isExpress;
    }


}