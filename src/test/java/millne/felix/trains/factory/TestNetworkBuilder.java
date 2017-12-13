package millne.felix.trains.factory;

import com.google.common.collect.Sets;
import millne.felix.trains.Station;
import millne.felix.trains.TrainNetwork;
import org.junit.Test;

import java.util.Collection;

import static junit.framework.TestCase.*;

public class TestNetworkBuilder {

    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";

    @Test
    public void testBuildEmptyNetwork(){
        NetworkBuilder testBuilder = new NetworkBuilder();
        TrainNetwork testBuildResult = testBuilder.build(Sets.newHashSet());

        assertNotNull(testBuildResult);
    }

    @Test
    public void testBuildSingleRouteNetwork() {
        Collection<RouteSet> testRouteSets = Sets.newHashSet(new RouteSet(A, B, 1));

        NetworkBuilder testBuilder = new NetworkBuilder();
        TrainNetwork testBuildResult = testBuilder.build(testRouteSets);

        assertNotNull(testBuildResult);
        assertTrue(testBuildResult.hasRoute(new Station(A), new Station(B)));
    }

    @Test
    public void testBuildDoubleLengthNetwork() {
        RouteSet routeSet1 = new RouteSet(A, B, 1);
        RouteSet routeSet2 = new RouteSet(B, C, 1);

        Collection<RouteSet> testRouteSets = Sets.newHashSet(routeSet1, routeSet2);

        NetworkBuilder testBuilder = new NetworkBuilder();
        TrainNetwork testBuildResult = testBuilder.build(testRouteSets);

        assertNotNull(testBuildResult);
        assertTrue(testBuildResult.hasRoute(new Station(A), new Station(B)));
        assertTrue(testBuildResult.hasRoute(new Station(B), new Station(C)));
        assertTrue(testBuildResult.hasRoute(new Station(A), new Station(C)));
    }

    @Test
    public void testBuildForkedNetwork() {
        RouteSet routeSet1 = new RouteSet(A, B, 1);
        RouteSet routeSet2 = new RouteSet(A, C, 1);

        Collection<RouteSet> testRouteSets = Sets.newHashSet(routeSet1, routeSet2);

        NetworkBuilder testBuilder = new NetworkBuilder();
        TrainNetwork testBuildResult = testBuilder.build(testRouteSets);

        assertNotNull(testBuildResult);
        assertTrue(testBuildResult.hasRoute(new Station(A), new Station(B)));
        assertTrue(testBuildResult.hasRoute(new Station(A), new Station(C)));
        assertFalse(testBuildResult.hasRoute(new Station(B), new Station(C)));
    }
}
