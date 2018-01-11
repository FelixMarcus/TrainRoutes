package millne.felix.trains;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import millne.felix.trains.network.AllRoutesSearch;
import millne.felix.trains.network.Station;
import millne.felix.trains.network.TrainNetwork;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestRouteSearch {

    @Test
    public void testFindOneSmallRoute(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station testDepartureStation = new Station("A");
        Station testDestinationStation = new Station("B");
        testNetwork.addRoute(testDepartureStation, testDestinationStation, 1);
        AllRoutesSearch allRoutes = testNetwork.routesFor(testDepartureStation, testDestinationStation);
        Collection<List<Station>> foundRoutes = allRoutes.search();

        assertEquals(1, foundRoutes.size());
        List<Station> route1 = Lists.newArrayList(new Station("A"), new Station("B"));
        HashSet<List<Station>> expected = Sets.newHashSet();
        expected.add(route1);
        assertEquals(expected, foundRoutes);
    }

    @Test
    public void testFindTwoRoutes(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station testDepartureStation = new Station("A");
        Station testIntermediateStation = new Station("B");
        Station testDestinationStation = new Station("C");
        testNetwork.addRoute(testDepartureStation, testDestinationStation, 1);
        testNetwork.addRoute(testDepartureStation, testIntermediateStation, 1);
        testNetwork.addRoute(testIntermediateStation, testDestinationStation, 1);
        AllRoutesSearch allRoutes = testNetwork.routesFor(testDepartureStation, testDestinationStation);
        Collection<List<Station>> foundRoutes = allRoutes.search();

        assertEquals(2, foundRoutes.size());
        ArrayList<Station> route1 = Lists.newArrayList(new Station("A"), new Station("C"));
        ArrayList<Station> route2 = Lists.newArrayList(new Station("A"), new Station("B"), new Station("C"));
        HashSet<List<Station>> expected = Sets.newHashSet();
        expected.add(route1);
        expected.add(route2);
        assertEquals(expected, foundRoutes);
    }

    @Test
    public void testFindCircularRoute(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station testDepartureStation = new Station("A");
        Station testIntermediateStation = new Station("B");
        Station testIntermediateStation2 = new Station("C");
        Station testDestinationStation = new Station("A");
        testNetwork.addRoute(testDepartureStation, testIntermediateStation, 1);
        testNetwork.addRoute(testIntermediateStation, testIntermediateStation2, 1);
        testNetwork.addRoute(testIntermediateStation2, testDestinationStation, 1);
        AllRoutesSearch allRoutes = testNetwork.routesFor(testDepartureStation, testDestinationStation);
        Collection<List<Station>> foundRoutes = allRoutes.maxStops(4).search();

        assertEquals(1, foundRoutes.size());
        ArrayList<Station> route1 = Lists.newArrayList(new Station("A"), new Station("B"), new Station("C"), new Station("A"));
        HashSet<List<Station>> expected = Sets.newHashSet();
        expected.add(route1);
        assertEquals(expected, foundRoutes);
    }

    @Test
    public void testFindOneRouteFromTwoRoutesWithMaxStopLimit(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station testDepartureStation = new Station("A");
        Station testIntermediateStation = new Station("B");
        Station testDestinationStation = new Station("C");
        testNetwork.addRoute(testDepartureStation, testDestinationStation, 1);
        testNetwork.addRoute(testDepartureStation, testIntermediateStation, 1);
        testNetwork.addRoute(testIntermediateStation, testDestinationStation, 1);
        AllRoutesSearch allRoutes = testNetwork.routesFor(testDepartureStation, testDestinationStation);
        Collection<List<Station>> foundRoutes = allRoutes.maxStops(0).search();

        assertEquals(1, foundRoutes.size());
        ArrayList<Station> route1 = Lists.newArrayList(new Station("A"), new Station("C"));
        HashSet<List<Station>> expected = Sets.newHashSet();
        expected.add(route1);
        assertEquals(expected, foundRoutes);
    }

    @Test
    public void testFindCircularRoutesWithMaxStopsInComplexNetwork(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station stationA = new Station("A");
        Station stationB = new Station("B");
        Station stationC = new Station("C");
        Station stationD = new Station("D");
        Station stationE = new Station("E");
        testNetwork.addRoute(stationA, stationB, 5);
        testNetwork.addRoute(stationB, stationC, 4);
        testNetwork.addRoute(stationC, stationD, 8);
        testNetwork.addRoute(stationD, stationC, 6);
        testNetwork.addRoute(stationD, stationE, 6);
        testNetwork.addRoute(stationA, stationD, 5);
        testNetwork.addRoute(stationC, stationE, 2);
        testNetwork.addRoute(stationE, stationB, 3);
        testNetwork.addRoute(stationA, stationE, 7);
        Collection<List<Station>> foundRoutes = testNetwork.routesFor(stationC, stationC).maxStops(3).search();

        assertEquals(2, foundRoutes.size());
    }

    @Test
    public void testFindCircularRoutesWithExactStopsInComplexNetwork(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station stationA = new Station("A");
        Station stationB = new Station("B");
        Station stationC = new Station("C");
        Station stationD = new Station("D");
        Station stationE = new Station("E");
        testNetwork.addRoute(stationA, stationB, 5);
        testNetwork.addRoute(stationB, stationC, 4);
        testNetwork.addRoute(stationC, stationD, 8);
        testNetwork.addRoute(stationD, stationC, 6);
        testNetwork.addRoute(stationD, stationE, 6);
        testNetwork.addRoute(stationA, stationD, 5);
        testNetwork.addRoute(stationC, stationE, 2);
        testNetwork.addRoute(stationE, stationB, 3);
        testNetwork.addRoute(stationA, stationE, 7);
        Collection<List<Station>> foundRoutes = testNetwork.routesFor(stationA, stationC).exactStops(4).search();

        assertEquals(3, foundRoutes.size());
    }

    @Test
    public void testFindOneSmallRouteOverMaxDistance(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station testDepartureStation = new Station("A");
        Station testDestinationStation = new Station("B");
        testNetwork.addRoute(testDepartureStation, testDestinationStation, 2);
        AllRoutesSearch allRoutes = testNetwork.routesFor(testDepartureStation, testDestinationStation);
        Collection<List<Station>> foundRoutes = allRoutes.limitDistance(2).search();

        assertEquals(0, foundRoutes.size());
    }


    @Test
    public void testFindCircularRoutesWithMaxDistanceInComplexNetwork(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station stationA = new Station("A");
        Station stationB = new Station("B");
        Station stationC = new Station("C");
        Station stationD = new Station("D");
        Station stationE = new Station("E");
        testNetwork.addRoute(stationA, stationB, 5);
        testNetwork.addRoute(stationB, stationC, 4);
        testNetwork.addRoute(stationC, stationD, 8);
        testNetwork.addRoute(stationD, stationC, 8);
        testNetwork.addRoute(stationD, stationE, 6);
        testNetwork.addRoute(stationA, stationD, 5);
        testNetwork.addRoute(stationC, stationE, 2);
        testNetwork.addRoute(stationE, stationB, 3);
        testNetwork.addRoute(stationA, stationE, 7);
        Collection<List<Station>> foundRoutes = testNetwork.routesFor(stationC, stationC).limitDistance(30).search();

        assertEquals(7, foundRoutes.size());
    }
}
