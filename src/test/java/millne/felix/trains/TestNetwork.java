package millne.felix.trains;

import com.google.common.graph.Network;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

/**
 * Created by FelixMarcus on 13/12/2017.
 */
public class TestNetwork {

    public static final String LONDON_EUSTON = "London Euston";
    public static final String LIVERPOOL_LIME_ST = "Liverpool Lime St";
    public static final String MANCHESTER_PICCADILLY = "Manchester Piccadilly";
    public static final String CAMBRIDGE = "Cambridge";

    @Test
    public void testBlankNetworkHasNoStation(){

        TrainNetwork testNetwork = new TrainNetwork();
        assertFalse(testNetwork.hasStation());
    }
}
