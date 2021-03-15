import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Simulation {
    public static void main(String[] args) throws IOException {
        simClock();
    }

    //Reads in the information from the text file
    public static ArrayList<Customer> readInCustomers() throws IOException {
        //For some reason this was requiring an absolute path in order to run the program. I'm not entirely sure why
        //TODO - Remove the absolute path and add a relative path and test it.
        Scanner input = new Scanner(new FileReader("G:\\CS1181\\Lab\\Projects\\Project 3\\src\\main\\java\\arrival.txt"));

        ArrayList<Customer> customers = new ArrayList<>();

        //Creates a new customer for each line of text the file has.
        while (input.hasNext()){
            double arrivalTime = input.nextDouble();
            int numItems = input.nextInt();
            double selectionTime = input.nextDouble();

            customers.add(new Customer(arrivalTime, numItems, selectionTime));
        }
        return customers;
    }

    //Runs the sim.
    public static void simClock() throws IOException {

        //Queue that tracks the events by time that they will happen
        //PriorityQueue<Double> events = new PriorityQueue(double times, Comparable);

        //List for the customers in the store.
        ArrayList<Customer> customersInStore = new ArrayList<>();

        //List of all customers for the day.
        ArrayList<Customer> allCustomersForDay = new ArrayList<>();
        allCustomersForDay = readInCustomers();

        //Sorts the customers by the time that they arrive at the store
        allCustomersForDay.sort(Comparator.comparingDouble(Customer::getArrival));

        /*for (Customer c: allCustomersForDay) {
            System.out.println(c.toString());
        }*/

        try{
            //TODO- try changing to relative path
            File outputFile = new File("G:\\CS1181\\Lab\\Projects\\Project 3\\src\\main\\java\\Output.txt");

            if(outputFile.createNewFile()){
                System.out.println(outputFile + " created");
            }
        }
        catch (IOException e){
            System.out.println(e);
        }

        double simTime = 0.0;

        //Tracks the time that customers should finish shopping by
        PriorityQueue<Double> customersDoneShoppingTime = new PriorityQueue<Double>();

        //Queue for tracking the next time coming up
        PriorityQueue<Double> allTimes = new PriorityQueue<>();

        for (Customer c: allCustomersForDay) {
            allTimes.add(c.getArrival());
            allTimes.add(c.getCheckoutTime());
        }

        ArrayList<Customer> customersSortedByCheckoutTime = allCustomersForDay;

        customersSortedByCheckoutTime.sort(Comparator.comparingDouble(Customer::getCheckoutTime));

        //TODO- Make loop for the registers being created.

        //loop for increasing the time in the sim
        while (simTime < allCustomersForDay.get(allCustomersForDay.size()-1).getCheckoutTime() + 1){
            //TODO- evaluate if this if statement is needed.
            /*if(customersInStore.size() < 1){
                customersInStore.add(allCustomersForDay.get(0));
                customersDoneShoppingTime.add(allCustomersForDay.get(0).getTimeShopping());
                simTime = customersInStore.get(0).getArrival();
                allCustomersForDay.remove(0);

                System.out.println("Sim time changed to " + simTime);
            }*/

            simTime = allTimes.peek();
            System.out.println("Time now " + simTime);
            allTimes.remove(simTime);

            if(allCustomersForDay.get(0).getArrival() == simTime){
                //Removes the customers from the all customers list sorted by the earliest arrival time
                customersInStore.add(allCustomersForDay.get(0));
                allCustomersForDay.remove(0);
            }
            else if(customersSortedByCheckoutTime.get(0).getCheckoutTime() == simTime){
                customersSortedByCheckoutTime.
                //Removes the customer from the checkout time list sorted by the earliest checkout times
                customersSortedByCheckoutTime.remove(0);
            }
        }
    }
}
