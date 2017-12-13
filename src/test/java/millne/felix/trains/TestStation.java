package millne.felix.trains;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
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
        assertFalse(station.hasRouteTo(new Station("Cambridge")));
    }

    @Test
    public void testStationGivenRouteHasRoute(){
        Station departureStation = new Station("London Euston");
        Station destination = new Station("Liverpool Lime St");
        departureStation.addRouteTo(destination, 1);
        assertTrue(departureStation.hasRouteTo(destination));
    }

    @Test
    public void testStationGivenDifferentRouteWithSameNameHasRoute(){
        Station departureStation = new Station("London Euston");
        Station destination = new Station("Liverpool Lime St");
        departureStation.addRouteTo(destination, 1);
        assertTrue(departureStation.hasRouteTo(new Station("Liverpool Lime St")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStationCannotHaveNullName(){
        Station testStation = new Station(null);
    }

    @Test
    public void testStationWithRouteHasDistanceForThatRoute() {
        Station departureStation = new Station("London Euston");
        Station destination = new Station("London Euston");
        departureStation.addRouteTo(destination, 1);
        assertEquals(1, departureStation.getDistanceTo(destination));
    }

    @Test
    public void testStationWithRoutesKnowsDifferentDistanceForBothRoute() {
        Station euston = new Station("London Euston");
        Station liverpool = new Station("Liverpool Lime St");
        Station manchester = new Station("Manchester Piccadilly");
        euston.addRouteTo(liverpool, 1);
        euston.addRouteTo(manchester, 2);
        assertEquals(1, euston.getDistanceTo(liverpool));
        assertEquals(2, euston.getDistanceTo(manchester));
    }
}
