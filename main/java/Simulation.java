import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Simulation {
    public static void main(String[] args){
        readInData();
    }

    //Reads in the information from the text file
    public static void readInData(){
        File textFile = new File("arrival.txt");

        //Catches the exception for not finding the file
        try {
            Scanner input = new Scanner(new FileReader(textFile));
            while (input.hasNext()){
                System.out.println(input.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("The file was not found");
            System.exit(1);
        }
    }

    //Runs the sim
    public static void SimClock(){
        double simTime = 0.00;
        Queue events = new PriorityQueue();
        ArrayList<Customer> customersInStore = new ArrayList<>();
    }
}
