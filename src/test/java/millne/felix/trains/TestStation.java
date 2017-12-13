package millne.felix.trains;

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
        assertFalse(station.hasRouteTo(null));
    }

    @Test
    public void testStationHasNoRoute(){
        Station station = new Station();
        assertFalse(station.hasRouteTo(new Station()));
    }

    @Test
    public void testStationGivenRouteHasRoute(){
        Station departureStation = new Station();
        Station destination = new Station();
        departureStation.addRouteTo(destination);
        assertTrue(departureStation.hasRouteTo(destination));
    }
}
