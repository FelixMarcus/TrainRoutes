package millne.felix.trains;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by FelixMarcus on 13/12/2017.
 */
public class TestStation {

    public static final String LONDON_EUSTON = "London Euston";
    public static final String LIVERPOOL_LIME_ST = "Liverpool Lime St";
    public static final String MANCHESTER_PICCADILLY = "Manchester Piccadilly";
    public static final String CAMBRIDGE = "Cambridge";

    @Test
    public void testStationHasNoRouteToNullStation(){
        Station station = new Station(LONDON_EUSTON);
        assertFalse(station.hasRouteTo(null));
    }

    @Test
    public void testStationHasNoRoute(){
        Station station = new Station(LONDON_EUSTON);
        assertFalse(station.hasRouteTo(new Station(CAMBRIDGE)));
    }

    @Test
    public void testStationGivenRouteHasRoute(){
        Station departureStation = new Station(LONDON_EUSTON);
        Station destination = new Station(LIVERPOOL_LIME_ST);
        departureStation.addRouteTo(destination, 1);
        assertTrue(departureStation.hasRouteTo(destination));
    }

    @Test
    public void testStationGivenDifferentRouteWithSameNameHasRoute(){
        Station departureStation = new Station(LONDON_EUSTON);
        Station destination = new Station(LIVERPOOL_LIME_ST);
        departureStation.addRouteTo(destination, 1);
        assertTrue(departureStation.hasRouteTo(new Station(LIVERPOOL_LIME_ST)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStationCannotHaveNullName(){
        Station testStation = new Station(null);
    }

    @Test
    public void testStationWithRouteHasDistanceForThatRoute() {
        Station departureStation = new Station(LONDON_EUSTON);
        Station destination = new Station(LONDON_EUSTON);
        departureStation.addRouteTo(destination, 1);
        assertEquals(1, departureStation.getDistanceTo(destination));
    }

    @Test
    public void testStationWithRoutesKnowsDifferentDistanceForBothRoute() {
        Station euston = new Station(LONDON_EUSTON);
        Station liverpool = new Station(LIVERPOOL_LIME_ST);
        Station manchester = new Station(MANCHESTER_PICCADILLY);
        euston.addRouteTo(liverpool, 1);
        euston.addRouteTo(manchester, 2);
        assertEquals(1, euston.getDistanceTo(liverpool));
        assertEquals(2, euston.getDistanceTo(manchester));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStationThrowsExceptionWhenHasNoRouteToGetDistanceTo() {
        Station euston = new Station(LONDON_EUSTON);
        int distanceToCambridge = euston.getDistanceTo(new Station(CAMBRIDGE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStationThrowsExceptionDistanceIsToNowhere() {
        Station euston = new Station(LONDON_EUSTON);
        int distanceToCambridge = euston.getDistanceTo(null);
    }
}
