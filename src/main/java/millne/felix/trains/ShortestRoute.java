package millne.felix.trains;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ShortestRoute {
    private final Map<String, Station> trainNetwork;
    private final Map<Station, Integer> bestWorkingShortestPath = Maps.newHashMap();
    private final Map<Station, Integer> confirmedShortestDistances = Maps.newHashMap();
    private Station departure;
    private Station destination;

    public ShortestRoute(Map<String, Station> networkStations, Station departure, Station destination) {
        this.trainNetwork = networkStations;
        this.departure = departure;
        this.destination = destination;

        Station departureStation = trainNetwork.get(this.departure.name());

        Station nextNearestStation = findNextNearestStation(departureStation);

        confirmStationDistance(nextNearestStation);

        while(!confirmedShortestDistances.containsKey(this.destination)) {
            nextNearestStation = findNextNearestStation(nextNearestStation);
            confirmStationDistance(nextNearestStation);
        }
    }

    public int findShortestDistance() {
        return confirmedShortestDistances.get(destination);
    }

    private Station findNextNearestStation(Station departureStation) {
        Integer departureStationDistance = confirmedShortestDistances.getOrDefault(departureStation, 0);
        Collection<Station> routes = departureStation.routes();
        for (Station route : routes) {
            bestWorkingShortestPath.compute(route, (station, oldDistance) -> {
                Integer newDistance = departureStation.getDistanceTo(route) + departureStationDistance;
                return oldDistance == null || newDistance < oldDistance ?
                        newDistance :
                        oldDistance;
            });
        }

        Map.Entry<Station, Integer> nextClosestStationDistance =
                Collections.min(bestWorkingShortestPath.entrySet(),
                        Map.Entry.comparingByValue());

        return nextClosestStationDistance.getKey();
    }

    private void confirmStationDistance(Station nextNearestStation) {
        confirmedShortestDistances.put(
                nextNearestStation,
                bestWorkingShortestPath.get(nextNearestStation)
        );

        bestWorkingShortestPath.remove(nextNearestStation);
    }
}