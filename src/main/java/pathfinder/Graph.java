package pathfinder;

import model.BusRoute;
import model.BusStop;
import model.Coord;
import model.SubwayStop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    public final Coord TOP_RIGHT;
    public final Coord BOTTOM_LEFT;
    public static final double WALKING_DISTANCE = Coord.IS_CLOSER;
    public final int MAX_ROW;
    public final int MAX_COL;

    private final Map<BusStop, Node> nodes;
    private final Sector[][] matrix;

    public Graph(List<BusStop> busStops, List<BusRoute> busRoutes, Map<String, List<SubwayStop>> subwayStops, Coord topRight, Coord bottomLeft) {
        TOP_RIGHT = topRight;
        BOTTOM_LEFT = bottomLeft;
        MAX_ROW = (int) (((TOP_RIGHT.getLat() - BOTTOM_LEFT.getLat()) / WALKING_DISTANCE) + 1);
        MAX_COL = (int) (((TOP_RIGHT.getLng() - BOTTOM_LEFT.getLng()) / WALKING_DISTANCE) + 1);
        matrix = new Sector[MAX_ROW][MAX_COL];
        nodes = new HashMap<>();

        generateNodes(busStops, subwayStops);
        generateEdges(busRoutes, subwayStops);
    }

    public Map<BusStop, Node> getNodes() {
        return nodes;
    }

    public int rowIndex(double lat) {
        return (int) ((TOP_RIGHT.getLat() - lat) / WALKING_DISTANCE);
    }

    public int colIndex(double lng) {
        return (int) ((lng - BOTTOM_LEFT.getLng()) / WALKING_DISTANCE);
    }

    public boolean isInTown(Coord position) {
        return Double.compare(position.getLng(), TOP_RIGHT.getLng()) <= 0 && Double.compare(position.getLng(), BOTTOM_LEFT.getLng()) >= 0 &&
                Double.compare(position.getLat(), TOP_RIGHT.getLat()) <= 0 && Double.compare(position.getLat(), BOTTOM_LEFT.getLat()) >= 0; // Lo cambiamos a menor y mayor igual porque no tomaba los casos limites
    }

    private void generateNodes(List<BusStop> busStops, Map<String, List<SubwayStop>> subwayStops) {
        addNodes(busStops);
        for (List<SubwayStop> subwayStopList : subwayStops.values()) {
            addNodes(subwayStopList);
        }
    }

    private void addNodes(List<? extends BusStop> busStops) {
        for (BusStop busStop : busStops) {
            if (isInTown(busStop.getCoord())) {
                Node toAdd = new Node(busStop);
                nodes.putIfAbsent(busStop, toAdd);
                int row = rowIndex(busStop.getCoord().getLat());
                int col = colIndex(busStop.getCoord().getLng());
                if (matrix[row][col] == null) {
                    matrix[row][col] = new Sector();
                }
                matrix[row][col].addNode(toAdd);
            }
        }
    }

    private void generateEdges(List<BusRoute> busRoutes, Map<String, List<SubwayStop>> subwayStops) {
        for (BusRoute busRoute : busRoutes) {
            BusStop head = null;
            for (int i = 0; i < busRoute.getRoute().size(); i++) {
                BusStop aux = new BusStop(busRoute.getRoute().get(i).getLng(),
                        busRoute.getRoute().get(i).getLat(),
                        busRoute.getId_line(), busRoute.getLine());
                if (nodes.containsKey(aux)) {
                    if (head != null) {
                        nodes.get(head).addEdge(nodes.get(aux), head.distanceTo(aux));
                    }
                    head = aux;
                }
            }
        }

        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                if (matrix[i][j] != null) {
                    int indexI = i, indexJ = j;
                    matrix[i][j].sectorNodes.forEach(node -> {
                        for (int k = indexI - 1; k <= indexI + 1; k++) {
                            for (int l = indexJ - 1; l <= indexJ + 1; l++) {
                                if (k >= 0 && k < MAX_ROW && l >= 0 && l < MAX_COL && matrix[k][l] != null) {
                                    connectNodes(node, matrix[k][l].sectorNodes);
                                }
                            }
                        }
                    });
                }
            }
        }

        subwayStops.values().forEach(subwayLine ->
                subwayLine.forEach(subwayStop ->
                        subwayLine.forEach(tail -> nodes.get(subwayStop).addEdge(nodes.get(tail), subwayStop.distanceTo(tail) / 2))));
    }

    private void connectNodes(Node head, List<Node> sectorNodes) {
        sectorNodes.forEach((tail) -> {
                    if (head.getBusStop().isCloser(tail.getBusStop())) {
                        head.addEdge(tail, head.getBusStop().distanceTo(tail.getBusStop()));
                    }
                }
        );
    }


    private static class Sector {
        private final List<Node> sectorNodes;

        public Sector() {
            this.sectorNodes = new ArrayList<>();
        }

        public void addNode(Node node) {
            sectorNodes.add(node);
        }
    }

}
