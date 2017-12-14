package millne.felix.trains;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

//TODO YAGNI - Remove
public class MapWalker {
    private final Map<Station, Station> confirmedShortestDistances;
    private final Station startNode;

    public MapWalker(Map<Station, Station> confirmedShortestDistances, Station startNode) {

        this.confirmedShortestDistances = confirmedShortestDistances;
        this.startNode = startNode;
    }

    public List<Station> getRoute() {
        List<Station> resultList = Lists.newArrayList(startNode);
        Station lastNode = startNode;
        while(confirmedShortestDistances.containsKey(lastNode)){
            lastNode = confirmedShortestDistances.get(lastNode);

            if(resultList.contains(lastNode)) {
                //Hit a loop
                resultList.add(lastNode);
                break;
            }

            resultList.add(lastNode);
        }

        return resultList;
    }
}
