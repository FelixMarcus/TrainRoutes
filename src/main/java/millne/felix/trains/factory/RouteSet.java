package millne.felix.trains.factory;

public class RouteSet {

    private final String departure;
    private final String destination;
    private final int distance;

    RouteSet(String departure, String destination, int distance) {
        this.departure = departure;
        this.destination = destination;
        this.distance = distance;
    }

    String getDeparture() {
        return departure;
    }

    String getDestination() {
        return destination;
    }

    int getDistance() {
        return distance;
    }
}
