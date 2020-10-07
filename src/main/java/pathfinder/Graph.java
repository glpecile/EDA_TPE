package pathfinder;

import model.BusRoute;
import model.BusStop;
import model.Coord;

import java.util.*;

public class Graph {
    public static final Coord TOP_RIGHT = new Coord(-58.30206850271441, -34.52854807880812);
    public static final Coord BOTTOM_LEFT = new Coord(-58.4840559, -34.6343296);
    public static final double WALKING_DISTANCE = Coord.IS_CLOSER;
    public static final int MAX_ROW = (int) (((TOP_RIGHT.getLat() - BOTTOM_LEFT.getLat()) / WALKING_DISTANCE) );
    public static final int MAX_COL = (int) (((TOP_RIGHT.getLng() - BOTTOM_LEFT.getLng()) / WALKING_DISTANCE ) );


    private final Map<BusStop, Node> nodes;
    private final Sector[][] matrix = new Sector[MAX_ROW][MAX_COL];

    public Graph(List<BusStop> busStops, List<BusRoute> busRoutes) {
        nodes = new HashMap<>();
        generateNodes(busStops);
        generateEdges(busRoutes);
    }

    private void generateNodes(List<BusStop> busStops) {
        for (BusStop busStop : busStops) {
            nodes.putIfAbsent(busStop, new Node(busStop));
        }
    }

    private void generateEdges(List<BusRoute> busRoutes) {
        for (BusRoute busRoute : busRoutes) {
            int i = 0;
            BusStop head = null;
            while (i < busRoute.getRoute().size()) {
                BusStop aux = new BusStop(busRoute.getRoute().get(i).getLng(),
                        busRoute.getRoute().get(i).getLat(),
                        busRoute.getId_line(), busRoute.getLine());
                if (nodes.containsKey(aux)) {
                    if (head != null) {
                        nodes.get(head).addEdge(nodes.get(aux), head.distanceTo(aux));
                    }
                    head = aux;
                }
                i++;
            }
        }

        for (Node head : nodes.values()) {
            for (Node tail : nodes.values()) {
                if (head.getBusStop().isCloser(tail.getBusStop()) /*&&
                        !head.getBusStop().getBusName().equals(tail.getBusStop().getBusName())*/) {
                    head.addEdge(tail, head.getBusStop().distanceTo(tail.getBusStop()));
                }
            }
        }
    }
    public static int rowIndex(double lat) {
        return (int) ((TOP_RIGHT.getLat() - lat) / WALKING_DISTANCE);
    }

    public static int colIndex(double lng) {
        return (int) ((lng - BOTTOM_LEFT.getLng()) / WALKING_DISTANCE);
    }

    public Map<BusStop, Node> getNodes() {
        return nodes;
    }

    private static class Sector {
        private final List<Node> nodes;

        public Sector() {
            this.nodes = new ArrayList<>();
        }
    }

}
