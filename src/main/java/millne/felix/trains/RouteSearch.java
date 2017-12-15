package millne.felix.trains;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

public class RouteSearch {
    private final Map<String, Station> stations;
    private final Station departure;
    private final Station destination;
    private int maxStops;
    private int maxDistance = Integer.MAX_VALUE;
    private int exactStops = -1;


    public RouteSearch(Map<String, Station> stations, Station departure, Station destination) {
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

            int currentDistance = lastDistance + thisStop.getDistanceTo(route);
            if(currentDistance >= maxDistance){
                continue;
            }

            if(route.equals(destination)){
                if(isExactNumOfStops(numOfStops)) {
                    foundRoutes.add(Lists.newArrayList(route));
                }
            }
            int thisStopNumber = numOfStops + 1;
            if(thisStopNumber > maxStops){
                continue;
            }

            findRoutesToDestination(
                    route,
                    thisStopNumber,
                    currentDistance)
                    .forEach(foundRoutes::add);
        }

        for(List<Station> foundRoute: foundRoutes){
            foundRoute.add(startStation);
        }

        return foundRoutes;
    }

    private boolean isExactNumOfStops(int numOfStops) {
        return exactStops == -1 || numOfStops == exactStops;
    }

    public RouteSearch maxStops(int i) {
        this.maxStops = i;
        return this;
    }

    public RouteSearch limitDistance(int i) {
        this.maxDistance = i;
        return this;
    }

    public RouteSearch exactStops(int i) {
        this.exactStops = i;
        this.maxStops = i;
        return this;
    }
}
