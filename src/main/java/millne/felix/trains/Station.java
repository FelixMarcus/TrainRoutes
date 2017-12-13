package millne.felix.trains;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by FelixMarcus on 13/12/2017.
 */
public class Station {
    private final Set<Station> destinations = Sets.newHashSet();
    private final String name;

    public Station(String name) {
        this.name = name;
    }

    public boolean hasRouteTo(Station toStation) {
        if(toStation == null) {
            return false;
        }

        return destinations.contains(toStation);
    }

    public void addRouteTo(Station destination) {
        destinations.add(destination);
    }
}
