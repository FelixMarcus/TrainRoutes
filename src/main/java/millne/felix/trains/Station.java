package millne.felix.trains;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

/**
 * Created by FelixMarcus on 13/12/2017.
 */
public class Station {

    private final Map<Station, Integer> destinations = Maps.newHashMap();

    private final String name;

    /**
     * @param name - The name of station. Must not be null. Primary identifier for equality between stations.
     * @throws IllegalArgumentException - on name value of @null
     */
    public Station(String name) {
        if (Strings.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Station name cannot be null");
        }
        this.name = name;
    }

    /**
     * The station's name.
     * @return The name of the station as set on construction.
     */
    public String name() {
        return name;
    }

    /**
     * Determines if station has knowledge of a route to the destination station.
     *
     * @param toStation
     * @return @true if the station has a route to the given destination station, else @false if not or if destination is null
     */
    public boolean hasRouteTo(Station toStation) {
        if (toStation == null) {
            return false;
        }

        return destinations.containsKey(toStation);
    }

    /**
     * Sets a new route to the given @destination with given @distance
     *
     * @param destination - Target station of new route
     * @param distance    - Distance to @destination
     */
    public void addRouteTo(Station destination, int distance) {
        if(destination == null){
            throw new IllegalArgumentException("Cannot set station route destination to nowhere");
        }

        destinations.put(destination, distance);
    }

    /**
     * Retrieves the previously set distance to @destination
     *
     * @param destination
     * @return distance to the given destination
     *
     * @throws IllegalArgumentException if there is no route to destination
     */
    public int getDistanceTo(Station destination) {
        if(!destinations.containsKey(destination)){
            throw new IllegalArgumentException("No route to destination");
        }

        return destinations.get(destination);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        return name.equals(station.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
