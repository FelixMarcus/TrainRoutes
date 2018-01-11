package millne.felix.trains.network;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

public class AllRoutesSearch {
    private final Map<String, Station> stations;
    private final Station departure;
    private final Station destination;
    private int maxStops;
    private int maxDistance = Integer.MAX_VALUE;
    private int exactStops = -1;
    private boolean getMaxStopsChanged = false;


    public AllRoutesSearch(Map<String, Station> stations, Station departure, Station destination) {
        this.stations = stations;
        this.departure = departure;
        this.destination = destination;
        maxStops = stations.size()*100;
    }

    public Collection<List<Station>> getAllRoutes(){
        return Sets.newHashSet();
    }

    public Collection<List<Station>> search() {
        Set<List<Station>> routesToDestination = findRoutesToDestination(departure, 1, 0);
        routesToDestination.forEach(Collections::reverse);
        return routesToDestination;
    }

    private Set<List<Station>> findRoutesToDestination(Station startStation, int numOfStops, int lastDistance) {
        Station thisStop = stations.get(startStation.name());
        Collection<Station> routes = thisStop.routes();
        Set<List<Station>> foundRoutes = Sets.newHashSet();
        for(Station route: routes){

            foundRoutes.addAll(
                    getRoutesFor(numOfStops, lastDistance, thisStop, route)
            );
        }

        for(List<Station> foundRoute: foundRoutes){
            foundRoute.add(startStation);
        }

        return foundRoutes;
    }

    private Set<List<Station>> getRoutesFor(int numOfStops, int lastDistance, Station thisStop, Station route) {
        Set<List<Station>> newFoundRoutes = Sets.newHashSet();
        int currentDistance = lastDistance + thisStop.getDistanceTo(route);
        if(currentDistance >= maxDistance){
            return newFoundRoutes;
        }

        if (route.equals(destination) && isExactNumOfStops(numOfStops)) {
            newFoundRoutes.add(Lists.newArrayList(route));
        }
        int thisStopNumber = numOfStops + 1;
        if(thisStopNumber > maxStops){
            return newFoundRoutes;
        }

        findRoutesToDestination(
                route,
                thisStopNumber,
                currentDistance)
                .forEach(newFoundRoutes::add);
        return newFoundRoutes;
    }

    private boolean isExactNumOfStops(int numOfStops) {
        return exactStops == -1 || numOfStops == exactStops;
    }

    public AllRoutesSearch maxStops(int i) {
        this.maxStops = i;
        this.getMaxStopsChanged = true;
        return this;
    }

    public AllRoutesSearch limitDistance(int i) {
        this.maxDistance = i;
        if(!getMaxStopsChanged){
            maxStops = Integer.MAX_VALUE;
        }
        return this;
    }

    public AllRoutesSearch exactStops(int i) {
        this.exactStops = i;
        if(!getMaxStopsChanged){
            maxStops = exactStops + 1;
        }
        return this;
    }
}
