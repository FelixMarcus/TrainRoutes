package millne.felix.trains;

import com.google.common.collect.Sets;

import java.util.Collection;

/**
 * Created by FelixMarcus on 13/12/2017.
 */
public class TrainNetwork {

    private Collection<Station> stations = Sets.newHashSet();

    public boolean hasStation(Station station) {
        return stations.contains(station);
    }

    public void addStation(Station station) {
        if(station == null){
            throw new IllegalArgumentException("TrainNetwork cannot have a null station in it");
        }

        stations.add(station);
    }
}
