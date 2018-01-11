package millne.felix.trains;

import millne.felix.trains.network.Station;
import millne.felix.trains.network.TrainNetwork;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestSearchNetworkShortestRoute {

    @Test
    public void testFindSingleStepShortestRoute(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station testStationDeparture = new Station("A");
        Station testStationDestination = new Station("B");
        testNetwork.addRoute(testStationDeparture, testStationDestination, 1);

        int shortestDistance = testNetwork.findShortestDistance(testStationDeparture, testStationDestination);

        assertEquals(1, shortestDistance);
    }

    @Test
    public void testFindShortestRouteBetweenTwoPathsIsDirectRoute(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station testStationDeparture = new Station("A");
        Station testStationIntermediate1 = new Station("B");
        Station testStationDestination = new Station("C");
        testNetwork.addRoute(testStationDeparture, testStationDestination, 1);
        testNetwork.addRoute(testStationDeparture, testStationIntermediate1, 1);
        testNetwork.addRoute(testStationIntermediate1, testStationDestination, 1);

        int shortestDistance = testNetwork.findShortestDistance(testStationDeparture, testStationDestination);

        assertEquals(1, shortestDistance);
    }

    @Test
    public void testFindShortestRouteBetweenTwoPathsIsIndirectRoute(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station testStationDeparture = new Station("A");
        Station testStationIntermediate1 = new Station("B");
        Station testStationDestination = new Station("C");
        testNetwork.addRoute(testStationDeparture, testStationDestination, 3);
        testNetwork.addRoute(testStationDeparture, testStationIntermediate1, 1);
        testNetwork.addRoute(testStationIntermediate1, testStationDestination, 1);

        int shortestDistance = testNetwork.findShortestDistance(testStationDeparture, testStationDestination);

        assertEquals(2, shortestDistance);
    }

    @Test
    public void testFindShortestRouteBetweenTwoPathsIsCircularRoute(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station testStationDeparture = new Station("A");
        Station testStationIntermediate1 = new Station("B");
        Station testStationDestination = new Station("A");
        testNetwork.addRoute(testStationDeparture, testStationIntermediate1, 1);
        testNetwork.addRoute(testStationIntermediate1, testStationDestination, 2);

        int shortestDistance = testNetwork.findShortestDistance(testStationDeparture, testStationDestination);

        assertEquals(3, shortestDistance);
    }
}
