package millne.felix.trains;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Map;

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

    public boolean hasRoute(Station departure, Station destination) {
        if(departure == null || destination == null
                || !hasStation(departure) || !hasStation(destination)){
            return false;
        }

        Station networkDeparture = stations.get(departure.name());
        Station networkDestination = stations.get(destination.name());

        return networkDeparture.hasRouteTo(networkDestination);
    }
}
