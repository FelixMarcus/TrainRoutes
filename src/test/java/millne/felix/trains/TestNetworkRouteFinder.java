package millne.felix.trains;

import com.google.common.collect.Lists;
import millne.felix.trains.network.Station;
import millne.felix.trains.network.TrainNetwork;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

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
        Station testStationDeparture = new Station("A");
        testNetwork.addStation(testStationDeparture);
        int exactRoute = testNetwork.findExactRoute(Lists.newArrayList(testStationDeparture));

        assertEquals(0, exactRoute);
    }

    @Test
    public void testFindExactRouteWithSimpleRouteOnNetworkReturnsDistance() {
        TrainNetwork testNetwork = new TrainNetwork();
        Station testStationDeparture = new Station("A");
        Station testStationDestination = new Station("B");
        testNetwork.addRoute(testStationDeparture, testStationDestination, 1);
        int exactRoute = testNetwork.findExactRoute(Lists.newArrayList(testStationDeparture, testStationDestination));

        assertEquals(1, exactRoute);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindExactRouteWithSingleStationOnNetworkThrowsException() {
        TrainNetwork testNetwork = new TrainNetwork();
        Station testStationDeparture = new Station("A");
        Station testStationDestination = new Station("B");
        testNetwork.addStation(testStationDeparture);
        int exactRoute = testNetwork.findExactRoute(Lists.newArrayList(testStationDeparture, testStationDestination));

        assertEquals(1, exactRoute);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindExactRouteWithDestinationStationNotOnNetworkThrowsException() {
        TrainNetwork testNetwork = new TrainNetwork();
        Station testStationDeparture = new Station("A");
        Station testStationDestination = new Station("B");
        Station testBadStationDestination = new Station("C");
        testNetwork.addRoute(testStationDeparture, testStationDestination, 1);
        int exactRoute = testNetwork.findExactRoute(Lists.newArrayList(testStationDeparture, testBadStationDestination));

        assertEquals(1, exactRoute);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindExactRouteWithDepartureStationNotOnNetworkThrowsException() {
        TrainNetwork testNetwork = new TrainNetwork();
        Station testStationDeparture = new Station("A");
        Station testStationDestination = new Station("B");
        Station testBadStationDeparture = new Station("C");
        testNetwork.addRoute(testStationDeparture, testStationDestination, 1);
        int exactRoute = testNetwork.findExactRoute(Lists.newArrayList(testBadStationDeparture, testStationDestination));

        assertEquals(1, exactRoute);
    }

    @Test
    public void testFindExactRouteWithThreeStationRouteOnNetworkReturnsDistance() {
        TrainNetwork testNetwork = new TrainNetwork();
        Station testStationDeparture = new Station("A");
        Station testStationDestination1 = new Station("B");
        Station testStationDestination2 = new Station("C");
        testNetwork.addRoute(testStationDeparture, testStationDestination1, 1);
        testNetwork.addRoute(testStationDestination1, testStationDestination2, 2);
        int exactRoute = testNetwork.findExactRoute(Lists.newArrayList(testStationDeparture, testStationDestination1, testStationDestination2));

        assertEquals(3, exactRoute);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindExactRouteWithThreeStationRouteLastStationNotOnNetworkThrowException() {
        TrainNetwork testNetwork = new TrainNetwork();
        Station testStationDeparture = new Station("A");
        Station testStationDestination1 = new Station("B");
        Station testStationDestination2 = new Station("C");
        testNetwork.addRoute(testStationDeparture, testStationDestination1, 1);
        int exactRoute = testNetwork.findExactRoute(Lists.newArrayList(testStationDeparture, testStationDestination1, testStationDestination2));

        assertEquals(3, exactRoute);
    }

    @Test
    public void testFindExactRouteWithThreeStationRouteOnForkedNetworkReturnsDistance() {
        TrainNetwork testNetwork = new TrainNetwork();
        Station testStationDeparture = new Station("A");
        Station testStationDestination1 = new Station("B");
        Station testStationDestination2 = new Station("C");
        Station testStationNotADestination = new Station("D");
        testNetwork.addRoute(testStationDeparture, testStationDestination1, 1);
        testNetwork.addRoute(testStationDestination1, testStationDestination2, 2);
        testNetwork.addRoute(testStationDestination1, testStationNotADestination, 4);
        int exactRoute = testNetwork.findExactRoute(Lists.newArrayList(testStationDeparture, testStationDestination1, testStationDestination2));

        assertEquals(3, exactRoute);
    }

    @Test
    public void testFindExactRouteWithThreeStationRouteOnCircularNetworkReturnsDistance() {
        TrainNetwork testNetwork = new TrainNetwork();
        Station testStationDeparture = new Station("A");
        Station testStationDestination1 = new Station("B");
        Station testStationDestination2 = new Station("C");
        testNetwork.addRoute(testStationDeparture, testStationDestination1, 1);
        testNetwork.addRoute(testStationDestination1, testStationDestination2, 2);
        testNetwork.addRoute(testStationDestination2, testStationDeparture, 3);
        int exactRoute = testNetwork.findExactRoute(
                Lists.newArrayList(
                        testStationDeparture,
                        testStationDestination1,
                        testStationDestination2,
                        testStationDeparture
                )
        );

        assertEquals(6, exactRoute);
    }
}
