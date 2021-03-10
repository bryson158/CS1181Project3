import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
        double simTime = 0.00;

        //Queue that tracks the events by time that they will happen
        Queue events = new PriorityQueue();

        //List for the customers in the store.
        ArrayList<Customer> customersInStore = new ArrayList<>();

        //List of all customers for the day.
        ArrayList<Customer> allCustomersForDay = new ArrayList<>();
        allCustomersForDay = readInCustomers();

        //Sorts the customers by the time that they arrive at the store
        //allCustomersForDay.sort(Comparator.comparingDouble(Customer::getArrival));

        for (Customer c: allCustomersForDay) {
            events.add(c.getArrival());
            events.add(c.getCheckoutTime());
        }



        /*
        for (Customer c: allCustomersForDay) {
            System.out.println(c.toString());
        } */

        for(int i = 1; i < 11; i++){
            for(int j =0; j < 12; j++){
                for (int p=0; p<i; p++, j++){

                }
            }
        }
    }
}
