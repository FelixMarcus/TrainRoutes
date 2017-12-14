package millne.felix.trains;

import java.util.List;
import java.util.Map;

class ExactRouteFinder {
    private final Map<String, Station> networkStations;

    ExactRouteFinder(Map<String, Station> networkStations) {
        this.networkStations = networkStations;
    }

    int findExactRoute(List<Station> route) {
        if (route == null || route.isEmpty()) {
            return 0;
        }

        Station departure = networkStations.get(route.get(0).name());
        if (departure == null) {
            throw new IllegalArgumentException("Network does not have " + route.get(0));
        }

        int totalDistance = 0;

        for (int destinationIndex = 0; destinationIndex < route.size() - 1; destinationIndex++) {
            totalDistance += getDistanceForLeg(route, destinationIndex);
        }

        return totalDistance;
    }

    private int getDistanceForLeg(List<Station> route, int legIndex) {
        Station departure = networkStations.get(route.get(legIndex).name());
        Station routeDestination = route.get(legIndex + 1);
        Station destination = networkStations.get(routeDestination.name());
        if (destination == null) {
            throw new IllegalArgumentException("Network does not have " + routeDestination);
        }

        if (!departure.hasRouteTo(destination)) {
            throw new IllegalArgumentException("No route found between " + departure + " and " + destination);
        }

        return departure.getDistanceTo(destination);
    }
}