package millne.felix.trains.parser;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import millne.felix.trains.factory.RouteSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by FelixMarcus on 13/12/2017.
 */
public class SingleCharacterStationNameInputParser {
    public List<RouteSet> parse(String testInput) {
        if(Strings.isNullOrEmpty(testInput)){
            return Lists.newArrayList();
        }

        ArrayList<RouteSet> parsedRoutes = Lists.newArrayList();

        for(String splitString: testInput.split(" ")) {
            char firstChar = splitString.charAt(0);
            char secondChar = splitString.charAt(1);
            String numString = splitString.substring(2);
            Integer parsedNum = Integer.valueOf(numString);
            RouteSet set = new RouteSet(String.valueOf(firstChar), String.valueOf(secondChar), parsedNum);
            parsedRoutes.add(set);
        }
        return parsedRoutes;
    }
}
