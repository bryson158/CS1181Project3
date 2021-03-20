import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Simulation {
    public static void main(String[] args) throws IOException {
        /*for(int i = 0; i < 11; i++){
            simClock(i);
        }*/

        simClock(4);
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
    public static void simClock(int expressRegisters) throws IOException {

        //Queue that tracks the events by time that they will happen
        //PriorityQueue<Double> events = new PriorityQueue(double times, Comparable);

        //List for the customers in the store.
        ArrayList<Customer> customersInStore = new ArrayList<>();

        //List of all customers for the day.
        ArrayList<Customer> allCustomersForDay = new ArrayList<>();
        allCustomersForDay = readInCustomers();

        //Sorts the customers by the time that they arrive at the store
        //allCustomersForDay.sort(Comparator.comparingDouble(Customer::getArrival));

        /*for (Customer c: allCustomersForDay) {
            System.out.println(c.toString());
        }*/
        //TODO- try changing to relative path
        File outputFile = new File("G:\\CS1181\\Lab\\Projects\\Project 3\\src\\main\\java\\Output"+ expressRegisters  +".txt");
        try{
            if(outputFile.createNewFile()){
                System.out.println(outputFile + " created");
            }
        }
        catch (IOException e){
            System.out.println(e);
        }

        PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));

        double simTime = 0.0;

        //Tracks the time that customers should finish shopping by
        //PriorityQueue<Double> customersDoneShoppingTime = new PriorityQueue<Double>();

        //Queue for tracking the next time coming up
        PriorityQueue<Double> allTimes = new PriorityQueue<>();

        for (Customer c: allCustomersForDay) {
            allTimes.add(c.getArrival());
            allTimes.add(c.getCheckoutTime());
        }

        //List of customers being sorted by their times of arriving at a register
        //ArrayList<Customer> customersSortedByCheckoutTime = allCustomersForDay;

        //customersSortedByCheckoutTime.sort(Comparator.comparingDouble(Customer::getCheckoutTime));

        //List of registers
        ArrayList<Checkout> registers = new ArrayList<>();

        //Creates all of the registers needed for the sim
        for(int i = 0; i < expressRegisters; i++){
            registers.add(new Checkout(true));
        }

        while(registers.size() < 12){
            registers.add(new Checkout(false));
        }

        //loop for increasing the time in the sim
        while(allCustomersForDay.size() > 0){
            for (int i = 0; i < allCustomersForDay.size(); i++ ) {
                //Checks if the arrival of the indexed customer is the next event
                if(allCustomersForDay.get(i).getArrival() == allTimes.peek()){
                    simTime = allTimes.poll();

                    outputWriter.write(simTime + ": Customer Arrival " + i + "\n");

                    break;

                }

                //Checks if the indexed customer's checkout time is the current time
                else if(allCustomersForDay.get(i).getCheckoutTime() == allTimes.peek()){
                    simTime = allTimes.poll();

                    outputWriter.write(simTime + ": Finished Shopping Customer " + i+1 +"\n");

                    //This logic block is used if the customer isn't elegible for express lane
                    if(allCustomersForDay.get(i).getItems() > 12){
                        outputWriter.write("More than 12, Chose Lane ");

                        PriorityQueue<Integer> checkoutLengths = new PriorityQueue<>();

                        for(int p = 0; p < registers.size(); p++){
                            //Will not add the register to the queue of line length since the register is an express lane
                            if(registers.get(p).getIsExpress()){
                                continue;
                            }
                            else {
                                checkoutLengths.add(registers.get(p).getCheckoutLineSize());
                            }
                        }

                        for(int p = 0; p < registers.size(); p++){
                            //Since customer has more than 12 items they cannot use an express lane and express lanes
                            //should be ignored
                            if(registers.get(p).getIsExpress()){
                                continue;
                            }
                            else {
                                //Only used if the length of the line is 0

                                //
                                if (registers.get(p).getCheckoutLineSize() == checkoutLengths.peek() &&
                                        checkoutLengths.peek() == 0) {
                                    //Adds the time the customer will be finished checking out to the queues
                                    allCustomersForDay.get(i).setDepartureTime(allCustomersForDay.get(i).getCheckoutTime() +
                                            (allCustomersForDay.get(i).getItems() * .05) + 2);
                                    allTimes.add(allCustomersForDay.get(i).getDepartureTime());
                                    outputWriter.write(p + " (" + registers.get(p).getCheckoutLineSize() + ")\n");
                                    registers.get(p).addCustomerToCheckoutLine(allCustomersForDay.get(i));
                                    outputWriter.flush();

                                    allTimes.poll();

                                    break;
                                }
                                //Used if this register is the one with the shortest line
                                else if (registers.get(p).getCheckoutLineSize() == checkoutLengths.peek()) {
                                    outputWriter.write(p + " (" + registers.get(p).getCheckoutLineSize() + ")\n");
                                    registers.get(p).addCustomerToCheckoutLine(allCustomersForDay.get(i));
                                    outputWriter.flush();

                                    allTimes.poll();
                                    break;
                                }
                            }
                        }


                    }
                    else {
                        outputWriter.write("12 or fewer items ");

                        PriorityQueue<Integer> checkoutLengths = new PriorityQueue<>();

                        for(int p = 0; registers.size() > p; p++){
                            checkoutLengths.add(registers.get(p).getCheckoutLineSize());
                        }

                        boolean done = false;

                        while (!done) {
                            for (int p = 0; registers.size() > p; p++) {
                                for (int j = 0; j < expressRegisters; j++) {
                                    //Only uses this if the line is empty
                                    if (registers.get(j).getCheckoutLineSize() == 0) {
                                        allTimes.add(allCustomersForDay.get(j).getCheckoutTime() + (
                                                allCustomersForDay.get(i).getItems() * .1) + 1);
                                        registers.get(j).addCustomerToCheckoutLine(allCustomersForDay.get(i));

                                        outputWriter.write(j + " (" + checkoutLengths.peek() + ")" + "\n");
                                        outputWriter.flush();
                                        done = true;
                                        break;
                                    }
                                    else if (registers.get(j).getCheckoutLineSize() == checkoutLengths.peek()) {
                                        registers.get(j).addCustomerToCheckoutLine(allCustomersForDay.get(i));

                                        //Writes to the output file the register picked and the length of the line
                                        outputWriter.write(j + " (" + checkoutLengths.peek() + ")" + "\n");
                                        outputWriter.flush();
                                        done = true;
                                        break;
                                    }
                                }
                                if (checkoutLengths.peek() == 0) {

                                }
                            }

                            for (int j = 0; j < expressRegisters; j++) {
                                //Only uses this if the line is empty
                                if (registers.get(j).getCheckoutLineSize() == 0) {
                                    allTimes.add(allCustomersForDay.get(j).getCheckoutTime() + (
                                            allCustomersForDay.get(i).getItems() * .1) + 1);
                                    registers.get(j).addCustomerToCheckoutLine(allCustomersForDay.get(i));

                                    outputWriter.write(j + " (" + checkoutLengths.peek() + ")" + "\n");
                                    outputWriter.flush();
                                    done = true;
                                    break;
                                }
                                else if (registers.get(j).getCheckoutLineSize() == checkoutLengths.peek()) {
                                    registers.get(j).addCustomerToCheckoutLine(allCustomersForDay.get(i));

                                    //Writes to the output file the register picked and the length of the line
                                    outputWriter.write(j + " (" + checkoutLengths.peek() + ")" + "\n");
                                    outputWriter.flush();
                                    done = true;
                                    break;
                                }
                            }
                        }
                    }
                }

                //Checks if the next time in the queue is a time for a checkout

            }
        }
    }
}