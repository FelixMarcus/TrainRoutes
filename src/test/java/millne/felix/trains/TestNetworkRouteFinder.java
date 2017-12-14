package millne.felix.trains;

import com.google.common.collect.Lists;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by FelixMarcus on 14/12/2017.
 */
public class TestNetworkRouteFinder {

    @Test
    public void testFindExactEmptyRouteReturnsZero() {
        TrainNetwork testNetwork = new TrainNetwork();
        int exactRoute = testNetwork.findExactRoute(Lists.newArrayList());

        assertEquals(0, exactRoute);
    }

    @Test
    public void testFindExactRouteWithSingleStationOnBlankNetworkReturnsZero() {
        TrainNetwork testNetwork = new TrainNetwork();
        Station a = new Station("A");
        testNetwork.addStation(a);
        int exactRoute = testNetwork.findExactRoute(Lists.newArrayList(a));

        assertEquals(0, exactRoute);
    }
}
