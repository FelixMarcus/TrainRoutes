package millne.felix.trains;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by FelixMarcus on 13/12/2017.
 */
public class TestNetwork {

    private static final String LONDON_EUSTON = "London Euston";
    private static final String LIVERPOOL_LIME_ST = "Liverpool Lime St";
    private static final String MANCHESTER_PICCADILLY = "Manchester Piccadilly";
    private static final String CAMBRIDGE = "Cambridge";

    @Test
    public void testBlankNetworkHasNoStation(){
        TrainNetwork testNetwork = new TrainNetwork();
        assertFalse(testNetwork.hasStation(new Station(CAMBRIDGE)));
    }

    @Test
    public void testNetworkDoesNotHaveStation(){
        TrainNetwork testNetwork = new TrainNetwork();
        testNetwork.addStation(new Station(LIVERPOOL_LIME_ST));
        assertFalse(testNetwork.hasStation(new Station(CAMBRIDGE)));
    }

    @Test
    public void testNetworkKnowsItHasStation(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station liverpool = new Station(LIVERPOOL_LIME_ST);
        testNetwork.addStation(liverpool);
        assertTrue(testNetwork.hasStation(liverpool));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNetworkCannotAddTrainsToNowhere(){
        TrainNetwork testNetwork = new TrainNetwork();
        testNetwork.addStation(null);
    }

    @Test
    public void testNetworkHasRoute(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station liverpool = new Station(LIVERPOOL_LIME_ST);
        testNetwork.addStation(liverpool);

        Station manchester = new Station(MANCHESTER_PICCADILLY);
        testNetwork.addStation(manchester);

        testNetwork.addRoute(liverpool, manchester, 2);

        assertTrue(testNetwork.hasRoute(liverpool, manchester));
    }

    @Test
    public void testNetworkHasNoRouteToNowhere(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station liverpool = new Station(LIVERPOOL_LIME_ST);
        testNetwork.addStation(liverpool);

        Station manchester = new Station(MANCHESTER_PICCADILLY);
        testNetwork.addStation(manchester);

        testNetwork.addRoute(liverpool, manchester, 2);

        assertFalse(testNetwork.hasRoute(liverpool, null));
    }

    @Test
    public void testNetworkHasNoRouteFromNowhere(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station liverpool = new Station(LIVERPOOL_LIME_ST);
        testNetwork.addStation(liverpool);

        Station manchester = new Station(MANCHESTER_PICCADILLY);
        testNetwork.addStation(manchester);

        testNetwork.addRoute(liverpool, manchester, 2);

        assertFalse(testNetwork.hasRoute(null, manchester));
    }

        @Test
        public void testNetworkHasNoRouteOffOfNetwork(){
            TrainNetwork testNetwork = new TrainNetwork();
            Station liverpool = new Station(LIVERPOOL_LIME_ST);
            testNetwork.addStation(liverpool);

            Station manchester = new Station(MANCHESTER_PICCADILLY);
            testNetwork.addStation(manchester);

            testNetwork.addRoute(liverpool, manchester, 2);

            assertFalse(testNetwork.hasRoute(liverpool, new Station(CAMBRIDGE)));
    }

    @Test
    public void testNetworkCanFindMoreComplexRoute(){
        TrainNetwork testNetwork = new TrainNetwork();
        Station liverpool = new Station(LIVERPOOL_LIME_ST);
        testNetwork.addStation(liverpool);

        Station manchester = new Station(MANCHESTER_PICCADILLY);
        testNetwork.addStation(manchester);

        Station euston = new Station(LONDON_EUSTON);
        testNetwork.addStation(euston);

        testNetwork.addRoute(liverpool, manchester, 2);
        testNetwork.addRoute(manchester, euston, 3);

        assertTrue(testNetwork.hasRoute(liverpool, euston));
    }
}
