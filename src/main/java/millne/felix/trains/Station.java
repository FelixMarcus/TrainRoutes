package millne.felix.trains;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by FelixMarcus on 13/12/2017.
 */
public class Station {
    private final Set<Station> destinations = Sets.newHashSet();

    public boolean hasRoute(Station toStation) {
        if(toStation == null) {
            return false;
        }

        return destinations.contains(destinations);
    }

    public void addRouteTo(Station destination) {
        destinations.add(destination);
    }
}
