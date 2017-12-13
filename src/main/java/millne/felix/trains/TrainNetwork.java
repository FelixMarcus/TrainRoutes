package millne.felix.trains;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

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

        Set<Station> allFoundStations = Sets.newHashSet();
        Collection<Station> routes = stations.get(departure.name()).routes();
        allFoundStations.addAll(routes);

        boolean hasAnyRoutes = routes.stream()
                .map(Station::name)
                .map((String routeName) -> stations.get(routeName).hasRouteTo(destination))
                .anyMatch(Boolean::booleanValue);

        return hasAnyRoutes;
    }

    private boolean hasSingleStepRoute(Station departure, Station destination) {

        Station networkDeparture = stations.get(departure.name());
        Station networkDestination = stations.get(destination.name());

        return networkDeparture.hasRouteTo(networkDestination);
    }
}
