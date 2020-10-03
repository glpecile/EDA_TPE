package pathfinder;

import model.BusRoute;
import model.BusStop;
import model.Coord;

import java.util.*;

public class Graph {
    //CAMBIAR TODO
    public final Map<BusStop, Node> nodes;

    public Graph(List<BusStop> busStops, List<BusRoute> busRoutes) {
        nodes = new HashMap<>();
        generateNodes(busStops);
        generateEdges(busRoutes);
    }

    private void generateNodes(List<BusStop> busStops) {
        for(BusStop busStop : busStops) {
            nodes.putIfAbsent(busStop, new Node(busStop));
        }
    }

    private void generateEdges(List<BusRoute> busRoutes) {
        for(BusRoute busRoute : busRoutes) {
            int i = 0;
            BusStop head = null;
            while(i < busRoute.getRoute().size()) {
                BusStop aux = new BusStop(busRoute.getRoute().get(i).getLng(),
                busRoute.getRoute().get(i).getLat(),
                busRoute.getId_line(), busRoute.getLine());
                if(nodes.containsKey(aux)) {
                    if(head != null) {
                        nodes.get(head).addEdge(nodes.get(aux), head.getCoord().distanceTo(aux.getCoord()));
                    }
                head = aux;
                }
                i++;
            }
        }

        for(Node head : nodes.values()) {
            for(Node tail : nodes.values()) {
                if(head.getBusStop().getCoord().isCloser(tail.getBusStop().getCoord()) &&
                        !head.getBusStop().getBusName().equals(tail.getBusStop().getBusName())) {
                    head.addEdge(tail, head.getBusStop().getCoord().distanceTo(tail.getBusStop().getCoord()));
                }
            }
        }
    }
}
