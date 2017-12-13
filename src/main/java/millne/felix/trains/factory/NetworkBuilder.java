package millne.felix.trains.factory;

import millne.felix.trains.TrainNetwork;

import java.util.Collection;

public class NetworkBuilder {
    public TrainNetwork build(Collection<RouteSet> routeSet) {
        TrainNetwork trainNetwork = new TrainNetwork();

        for (RouteSet set: routeSet){
            trainNetwork.addRoute(set.getDeparture(), set.getDestination(), set.getDistance());
        }

        return trainNetwork;
    }
}
