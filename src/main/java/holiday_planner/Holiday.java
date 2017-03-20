package holiday_planner;


import java.util.ArrayList;
import java.util.List;

public class Holiday {

    private List<Destination> destinations;
    private List<Destination> route;

    public Holiday() {
        destinations = new ArrayList<>();
        route = new ArrayList<>();
    }

    public List<Destination> getRoute() {
        return route;
    }

    void addDestination(Destination destination) {
        destinations.add(destination);
    }

}
