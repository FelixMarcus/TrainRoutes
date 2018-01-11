package millne.felix;

import com.google.common.collect.Lists;
import millne.felix.trains.io.NetworkFileReader;
import millne.felix.trains.network.Station;
import millne.felix.trains.network.TrainNetwork;
import millne.felix.trains.factory.NetworkBuilder;
import millne.felix.trains.factory.RouteSet;
import millne.felix.trains.parser.SingleCharacterStationNameInputParser;

import java.util.Collection;
import java.util.List;

public class PrettyTestPrinter {

    public static void main(String[] args) {

        String input;
        if(args.length == 0){
            System.out.println("Using default network");
            input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        } else {
            String fileName = args[0];

            input = new NetworkFileReader().getNetworkStringFromFile(fileName);

        }

        SingleCharacterStationNameInputParser parser = new SingleCharacterStationNameInputParser();
        List<RouteSet> parsedResult = parser.parse(input);

        TrainNetwork network = new NetworkBuilder().build(parsedResult);

        System.out.println("Test 1: The distance of the route A-B-C (expected = 9)");
        String exactRouteDistance1 = network.findSantisedExactRouteDistance(
                Lists.newArrayList(
                        new Station("A"),
                        new Station("B"),
                        new Station("C")
                )
        );
        System.out.println("Result: "+ exactRouteDistance1);

        System.out.println("Test 2: The distance of the route A-D (expected = 5)");
        String exactRouteDistance2 = network.findSantisedExactRouteDistance(
                Lists.newArrayList(
                        new Station("A"),
                        new Station("D")
                )
        );
        System.out.println("Result: "+ exactRouteDistance2);

        System.out.println("Test 3: The distance of the route A-D-C (expected = 13)");
        String exactRouteDistance3 = network.findSantisedExactRouteDistance(
                Lists.newArrayList(
                        new Station("A"),
                        new Station("D"),
                        new Station("C")
                )
        );
        System.out.println("Result: "+ exactRouteDistance3);

        System.out.println("Test 4: The distance of the route A-D (expected = 22)");
        String exactRouteDistance4 = network.findSantisedExactRouteDistance(
                Lists.newArrayList(
                        new Station("A"),
                        new Station("E"),
                        new Station("B"),
                        new Station("C"),
                        new Station("D")
                )
        );
        System.out.println("Result: "+ exactRouteDistance4);

        System.out.println("Test 5: The distance of the route A-D (expected = NO_SUCH_ROUTE)");
        String exactRouteDistance5 = network.findSantisedExactRouteDistance(
                Lists.newArrayList(
                        new Station("A"),
                        new Station("E"),
                        new Station("D")
                )
        );
        System.out.println("Result: "+ exactRouteDistance5);

        System.out.println("Test 6: The number of trips starting at C and ending at C with a maximum of 3 stops (expected = 2)");
        int numberOfRoutes6 = network.routesFor(new Station("C"), new Station("C")).maxStops(3).search().size();
        System.out.println("Result: "+ numberOfRoutes6);

        System.out.println("Test 7: The number of trips starting at A and ending at C with exactly 4 stops. (expected = 3)");
        Collection<List<Station>> routes7 = network.routesFor(new Station("A"), new Station("C")).exactStops(4).search();
        int numberOfRoutes7 = routes7.size();
        System.out.println("Result: "+ numberOfRoutes7);

        System.out.println("Test 8: The length of the shortest route (in terms of distance to travel) from A to C (expected = 9)");
        int shortestDistance8 = network.findShortestDistance(new Station("A"), new Station("C"));
        System.out.println("Result: "+ shortestDistance8);

        System.out.println("Test 9: The length of the shortest route (in terms of distance to travel) from B to B (expected = 9)");
        int shortestDistance9 = network.findShortestDistance(new Station("B"), new Station("B"));
        System.out.println("Result: "+ shortestDistance9);

        System.out.println("Test 10: The number of different routes from C to C with a distance of less than 30 (expected = 7)");
        int numberOfRoutes10 = network.routesFor(new Station("C"), new Station("C")).limitDistance(30).search().size();
        System.out.println("Result: "+ numberOfRoutes10);
    }

}
