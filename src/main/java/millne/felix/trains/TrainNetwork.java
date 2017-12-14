package millne.felix.trains;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.Collectors;

public class TrainNetwork {

    private final Map<String, Station> stations = Maps.newHashMap();
    private final ExactRoute exactRoute = new ExactRoute(stations);

    public boolean hasStation(Station station) {
        return stations.containsKey(station.name());
    }

    public void addStation(Station station) {
        if(station == null){
            throw new IllegalArgumentException("TrainNetwork cannot have a null station in it");
        }

        stations.put(station.name(), new Station(station.name()));
    }

    public void addRoute(String departure, String destination, int distance) {
        addRoute(new Station(departure), new Station(destination), distance);
    }

    public void addRoute(Station departure, Station destination, int distance) {
        if(!stations.containsKey(departure.name())){
            addStation(departure);
        }

        if(!stations.containsKey(destination.name())){
            addStation(destination);
        }

        Station networkDeparture = station(departure);
        Station networkDestination = station(destination);

        networkDeparture.addRouteTo(networkDestination, distance);
    }

    public boolean hasRoute(Station departure, final Station destination) {
        if(departure == null || destination == null
                || !hasStation(departure) || !hasStation(destination)){
            return false;
        }

        if(hasSingleStepRoute(departure, destination)){
            return true;
        }

        Set<Station> allFoundStations = Sets.newHashSet(station(departure));

        return hasComplexRouteToStation(departure, destination, allFoundStations);
    }

    private boolean hasComplexRouteToStation(Station departure, Station destination, Set<Station> allFoundStations) {
        Collection<Station> routes = station(departure).routes();

        Collection<Station> unprocessedStations = routes.stream()
                .filter(station -> !allFoundStations.contains(station))
                .collect(Collectors.toSet());

        if(unprocessedStations.isEmpty()){
            return false;
        }

        boolean hasAnyRoutes = unprocessedStations.stream()
                .map(Station::name)
                .map(stations::get)
                .map((Station station) -> station.hasRouteTo(destination))
                .anyMatch(Boolean::booleanValue);
        
        if(hasAnyRoutes){
            return true;
        }
        
        allFoundStations.addAll(routes);
        
        return unprocessedStations.stream()
                .map(Station::name)
                .map(stations::get)
                .map(Station::routes)
                .flatMap(Collection::stream)
                .map((station -> hasComplexRouteToStation(station, destination, allFoundStations)))
                .anyMatch(Boolean::booleanValue);
    }

    private boolean hasSingleStepRoute(Station departure, Station destination) {

        Station networkDeparture = station(departure);
        Station networkDestination = station(destination);

        return networkDeparture.hasRouteTo(networkDestination);
    }

    protected Station station(Station departure) {
        return stations.get(departure.name());
    }

    public int findExactRoute(List<Station> route) {
        return exactRoute.exactRouteDistance(route);
    }

    public int findShortestDistance(Station departure, Station destination) {
        return new ShortestRoute(stations, departure, destination).findShortestDistance();
    }
}
