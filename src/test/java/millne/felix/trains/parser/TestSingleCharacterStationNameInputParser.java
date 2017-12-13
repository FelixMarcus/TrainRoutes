package millne.felix.trains.parser;

import millne.felix.trains.factory.RouteSet;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestSingleCharacterStationNameInputParser {

    @Test
    public void testParseNullReturnsEmptyCollection() {
        String testInput = null;
        SingleCharacterStationNameInputParser testParser = new SingleCharacterStationNameInputParser();
        Collection<RouteSet> parseResult = testParser.parse(testInput);

        assertTrue(parseResult.isEmpty());
    }

    @Test
    public void testParseEmptyStringReturnsEmptyCollection() {
        String testInput = "";
        SingleCharacterStationNameInputParser testParser = new SingleCharacterStationNameInputParser();
        Collection<RouteSet> parseResult = testParser.parse(testInput);

        assertTrue(parseResult.isEmpty());
    }

    @Test
    public void testParseSingleCharacterSetIntoRouteSet() {
        String testInput = "AB1";
        SingleCharacterStationNameInputParser testParser = new SingleCharacterStationNameInputParser();
        List<RouteSet> parseResult = testParser.parse(testInput);

        assertEquals(1, parseResult.size());
        assertEquals(new RouteSet("A", "B", 1), parseResult.get(0));
    }

    @Test
    public void testParseDoubleCharacterSetIntoRouteSet() {
        String testInput = "AB1 BC2";
        SingleCharacterStationNameInputParser testParser = new SingleCharacterStationNameInputParser();
        List<RouteSet> parseResult = testParser.parse(testInput);

        assertEquals(2, parseResult.size());
        assertEquals(new RouteSet("A", "B", 1), parseResult.get(0));
        assertEquals(new RouteSet("B", "C", 2), parseResult.get(1));
    }
}
