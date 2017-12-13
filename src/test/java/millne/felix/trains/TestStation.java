package millne.felix.trains;

import millne.felix.trains.Station;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by FelixMarcus on 13/12/2017.
 */
public class TestStation {

    @Test
    public void testStationHasNoRouteToNullStation(){
        Station station = new Station();
        assertFalse(station.hasRoute(null));
    }

    @Test
    public void testStationHasNoRoute(){
        Station station = new Station();
        assertFalse(station.hasRoute(new Station()));
    }

    @Test
    public void testStationGivenRouteHasRoute(){
        Station departureStation = new Station();
        Station destination = new Station();
        departureStation.addRouteTo(destination);
        assertFalse(departureStation.hasRoute(new Station()));
    }
}
