import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * main class for Programming Assignment04, which handles flight traffic and min priority queues
 *
 * @author Joseph Brooksbank
 */
public class main {

    /**
     * Main method for programming assignment04
     *
     * @param args                   Input and Output file locations
     * @throws FileNotFoundException If file argument is specified incorrectly
     * @throws Heap.HeapError        If there are any problems with the heap (shouldn't be thrown)
     */
    public static void main(String[] args) throws FileNotFoundException, Heap.HeapError, IOException {
        /* Scanner to read plane data from file */
        Scanner stdin = new Scanner(new FileInputStream(args[0]));

        Writer stdout = new FileWriter(args[1]);
        /* number of runways available for planes to land on */
        int numRumways = stdin.nextInt();

        /* Plane data read from file that has not been used in the queue yet */
        ArrayList<Plane> prePlanes = new ArrayList<>();
        while (stdin.hasNext()) {
            String aircraftID = stdin.next();
            int arrivalTime = stdin.nextInt();
            int landingPriority = stdin.nextInt();
            prePlanes.add(new Plane(aircraftID, arrivalTime, landingPriority));
        }

        /* Heap for min priority queue */
        Heap planes = new Heap();
        /* variables for timing and controlling the loop */
        int time = 0;
        int planesToAdd = prePlanes.size();
        /* main while loop of the program, manages queue and prints to file */
        while (planesToAdd > 0 || planes.heapSize > 0) {
            for (Plane aPlane : prePlanes) {
                if (aPlane.arrivalTime == time) {
                    planes.insert(aPlane);
                    planesToAdd--;
                }
            }

            /* if there are planes in the queue, send them to the runways */
            if (planes.heapSize >= 1) {
                for (int i = 0; i < numRumways; i++) {
                    Plane landingPlane = planes.extractMin();
                    stdout.write(landingPlane + "   " + time + "\n");

                }
            }

            time++;
        }

        stdout.flush();
        stdout.close();


    }
}
