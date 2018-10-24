/**
 * Class to contain plane data
 *
 * @author Joseph Brooksbank
 */
public class Plane {

    /**
     * The ID given to the aircraft
     */
    String aircraftID;
    /**
     * The time which the aircraft arrives
     */
    int arrivalTime;
    /**
     * The landing priority given in the data file
     */
    int baseLandingPriority;
    /**
     * The "real" landing priority, taking into account both landingpriority and arrival time
     */
    double landingPriority;

    /**
     * Default constructor of a plane object
     *
     * @param aircraftID          The ID of the aircraft
     * @param arrivalTime         The time which the aircraft arrives
     * @param baseLandingPriority The landing priority given in the data file
     */
    public Plane(String aircraftID, int arrivalTime, int baseLandingPriority) {
        this.aircraftID = aircraftID;
        this.arrivalTime = arrivalTime;
        this.baseLandingPriority = baseLandingPriority;
        this.landingPriority = Double.parseDouble(baseLandingPriority + "." + arrivalTime);
    }

    public String toString() {
        return aircraftID + "   " + arrivalTime + "   " + baseLandingPriority;
    }
}
