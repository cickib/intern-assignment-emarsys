package holiday_planner;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    void planRoute() {
        if (destinations.isEmpty()) {
            throw new NullPointerException();
        }

        List<Destination> destinationsWithNextOne = destinations.stream().filter(
                Destination::hasNextDestination).distinct().collect(Collectors.toList());
        List<Destination> singleDestinations = destinations.stream().filter(
                destination -> !destination.hasNextDestination()).distinct().collect(Collectors.toList());

        for (Destination destination : destinationsWithNextOne) {
            if (singleDestinations.contains(destination.getNextDestination())) {
                route.add(destination);
                route.add(destination.getNextDestination());
                singleDestinations.remove(destination.getNextDestination());
            } else {
                if (route.contains(destination.getNextDestination())) {
                    route.add(route.indexOf(destination.getNextDestination()), destination);
                } else {
                    route.add(destination);
                }
            }
        }

        if (!singleDestinations.isEmpty()) {
            route.addAll(singleDestinations);
        }
    }

}
