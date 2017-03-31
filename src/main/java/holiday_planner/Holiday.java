package holiday_planner;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Holiday {

    private List<Destination> destinations;
    private List<Destination> route;
    private List<Destination> singleDestinations;
    private List<Destination> destinationsWithNextOne;

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
        separateDestinations();

        for (Destination destination : destinationsWithNextOne) {
            if (nextDestinationsIsSingle(destination)) {
                sortDestinationsWithSingleNext(destination);
            } else {
                sortDestinationsWithMultipleNext(destination);
            }
        }

        if (!singleDestinations.isEmpty()) {
            route.addAll(singleDestinations);
        }
    }

    private void separateDestinations() {
        if (destinations.isEmpty()) {
            throw new NullPointerException();
        }

        destinationsWithNextOne = filterDestinationsByHavingNext(true);
        singleDestinations = filterDestinationsByHavingNext(false);
    }

    private List<Destination> filterDestinationsByHavingNext(boolean hasNext) {
        return destinations
                .stream()
                .filter(destination -> destination.hasNextDestination() == hasNext)
                .distinct()
                .collect(Collectors.toList());
    }

    private boolean nextDestinationsIsSingle(Destination destination) {
        return singleDestinations.contains(destination.getNextDestination());
    }

    private void sortDestinationsWithSingleNext(Destination destination) {
        route.add(destination);
        route.add(destination.getNextDestination());
        singleDestinations.remove(destination.getNextDestination());
    }

    private void sortDestinationsWithMultipleNext(Destination destination) {
        if (route.contains(destination.getNextDestination())) {
            route.add(route.indexOf(destination.getNextDestination()), destination);
        } else {
            route.add(destination);
        }
    }

}
