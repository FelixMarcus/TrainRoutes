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
    public void testStationHasNoRoutes(){
        Station station = new Station();
        assertFalse(station.hasRoute(null));
    }

    @Test
    public void testStationHasRoute(){
        Station station = new Station();
        assertTrue(station.hasRoute(new Station()));
    }
}
