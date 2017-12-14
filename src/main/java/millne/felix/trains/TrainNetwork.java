package millne.felix.trains;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TrainNetwork {

    private Map<String, Station> stations = Maps.newHashMap();

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

        Station networkDeparture = stations.get(departure.name());
        Station networkDestination = stations.get(destination.name());

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

        Set<Station> allFoundStations = Sets.newHashSet(stations.get(departure.name()));

        return hasComplexRouteToStation(departure, destination, allFoundStations);
    }

    private boolean hasComplexRouteToStation(Station departure, Station destination, Set<Station> allFoundStations) {
        Collection<Station> routes = stations.get(departure.name()).routes();

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

        Station networkDeparture = stations.get(departure.name());
        Station networkDestination = stations.get(destination.name());

        return networkDeparture.hasRouteTo(networkDestination);
    }

    public int findExactRoute(List<Station> route) {
        return 0;
    }
}
