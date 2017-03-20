package holiday_planner;


public class Destination {

    private String destinationName;

    private Destination nextDestination;

    public Destination(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public Destination getNextDestination() {
        return nextDestination;
    }

    public void setNextDestination(Destination nextDestination) {
        this.nextDestination = nextDestination;
    }

    public boolean hasNextDestination() {
        return nextDestination != null;
    }

}