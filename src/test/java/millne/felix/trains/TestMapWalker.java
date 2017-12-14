package millne.felix.trains;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by FelixMarcus on 14/12/2017.
 */
public class TestMapWalker {

    @Test
    public void testSingleStepMap(){
        Map<Station, Station> testInput = Maps.newHashMap();
        testInput.put(new Station("A"), new Station("B"));
        MapWalker testWalker = new MapWalker(testInput, new Station("A"));
        List<Station> testResultRoute = testWalker.getRoute();
        assertEquals(Lists.newArrayList(new Station("A"), new Station("B")), testResultRoute);
    }

    @Test
    public void testLongRouteMap(){
        Map<Station, Station> testInput = Maps.newHashMap();
        testInput.put(new Station("A"), new Station("B"));
        testInput.put(new Station("B"), new Station("C"));
        testInput.put(new Station("C"), new Station("D"));
        testInput.put(new Station("D"), new Station("E"));
        MapWalker testWalker = new MapWalker(testInput, new Station("A"));

        List<Station> testResultRoute = testWalker.getRoute();
        ArrayList<Station> expectedRoute = Lists.newArrayList(
                new Station("A"),
                new Station("B"),
                new Station("C"),
                new Station("D"),
                new Station("E"));

        assertEquals(expectedRoute, testResultRoute);
    }

    @Test
    public void testCircularMap(){
        Map<Station, Station> testInput = Maps.newHashMap();
        testInput.put(new Station("A"), new Station("B"));
        testInput.put(new Station("B"), new Station("C"));
        testInput.put(new Station("C"), new Station("D"));
        testInput.put(new Station("D"), new Station("A"));
        MapWalker testWalker = new MapWalker(testInput, new Station("A"));

        List<Station> testResultRoute = testWalker.getRoute();
        ArrayList<Station> expectedRoute = Lists.newArrayList(
                new Station("A"),
                new Station("B"),
                new Station("C"),
                new Station("D"),
                new Station("A"));

        assertEquals(expectedRoute, testResultRoute);
    }
}