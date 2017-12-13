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
        Station station = new Station("London Euston");
        assertFalse(station.hasRouteTo(null));
    }

    @Test
    public void testStationHasNoRoute(){
        Station station = new Station("London Euston");
        assertFalse(station.hasRouteTo(new Station("London Euston")));
    }

    @Test
    public void testStationGivenRouteHasRoute(){
        Station departureStation = new Station("London Euston");
        Station destination = new Station("London Euston");
        departureStation.addRouteTo(destination);
        assertTrue(departureStation.hasRouteTo(destination));
    }

    @Test
    public void testStationGivenDifferentRouteWithSameNameHasRoute(){
        Station departureStation = new Station("London Euston");
        Station destination = new Station("London Euston");
        departureStation.addRouteTo(destination);
        assertTrue(departureStation.hasRouteTo(new Station("London Euston")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStationCannotHaveNullName(){
        Station testStation = new Station(null);
    }
}
