package millne.felix.trains.factory;

public class RouteSet {

    private final String departure;
    private final String destination;
    private final int distance;

    public RouteSet(String departure, String destination, int distance) {
        //TODO Make inputs nonnull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteSet routeSet = (RouteSet) o;

        if (distance != routeSet.distance) return false;
        if (!departure.equals(routeSet.departure)) return false;
        return destination.equals(routeSet.destination);

    }

    @Override
    public int hashCode() {
        int result = departure.hashCode();
        result = 31 * result + destination.hashCode();
        result = 31 * result + distance;
        return result;
    }
}
