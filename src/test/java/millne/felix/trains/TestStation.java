package millne.felix.trains;

import millne.felix.trains.Station;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

/**
 * Created by FelixMarcus on 13/12/2017.
 */
public class TestStation {

    @Test
    public void testStationHasNoRoutes(){
        Station station = new Station();
        assertFalse(station.hasRoute());
    }
}
