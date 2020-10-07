package pathfinder;

import model.BusRoute;
import model.BusStop;
import model.Coord;

import java.util.*;

public class Graph {
    public final Coord TOP_RIGHT;
    public final Coord BOTTOM_LEFT;
    public static final double WALKING_DISTANCE = Coord.IS_CLOSER;
    public final int MAX_ROW;
    public final int MAX_COL;
    public final double IS_IN_TOWN;

    private final Map<BusStop, Node> nodes;
    private final Sector[][] matrix;

    public Graph(List<BusStop> busStops, List<BusRoute> busRoutes, Coord topRight, Coord bottomLeft) {
        nodes = new HashMap<>();
        TOP_RIGHT = topRight;
        BOTTOM_LEFT = bottomLeft;
        MAX_ROW = (int) (((TOP_RIGHT.getLat() - BOTTOM_LEFT.getLat()) / WALKING_DISTANCE) );
        MAX_COL = (int) (((TOP_RIGHT.getLng() - BOTTOM_LEFT.getLng()) / WALKING_DISTANCE ) );
        IS_IN_TOWN = (TOP_RIGHT.getLat() - BOTTOM_LEFT.getLat()) / MAX_ROW;
        matrix = new Sector[MAX_ROW][MAX_COL];
        generateNodes(busStops);
        generateEdges(busRoutes);

    }

    private void generateNodes(List<BusStop> busStops) {
        System.out.println("MAX_ROW: " + MAX_ROW);
        System.out.println("MAX_COL: " + MAX_COL);
        System.out.println("---------------");
        for (BusStop busStop : busStops) {
            if(isInTown(busStop.getCoord())){
                Node toAdd = new Node(busStop);
                nodes.putIfAbsent(busStop, toAdd);

                int row = rowIndex(busStop.getCoord().getLat());
                int col = colIndex(busStop.getCoord().getLng());

//                System.out.println("Row:" + row);
//                System.out.println("Col:" + col);
//                System.out.println("---------------");
                if(matrix[row][col] == null)
                    matrix[row][col] = new Sector();
                matrix[row][col].addNode(toAdd);
            }



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
        for (int i = 0; i < MAX_ROW ; i++) {
            for (int j = 0; j <MAX_COL ; j++) {

                if(matrix[i][j] != null){
                    List<Node> thisSectorNodes = matrix[i][j].sectorNodes;
                    for (Node node :
                            thisSectorNodes) {
                        for (int k = i - 1; k <= i + 1; k++) {
                            for (int l = j - 1; l <= j + 1; l++) {
                                if(k>=0 && k < MAX_ROW && l >= 0 && l<MAX_COL && matrix[k][l] != null){
                                    connectNodes(node, matrix[k][l].sectorNodes);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void connectNodes(Node head, List<Node> sectorNodes) {
        sectorNodes.forEach( (tail) -> {
                    if(head.getBusStop().isCloser(tail.getBusStop())){
                        head.addEdge(tail, head.getBusStop().distanceTo(tail.getBusStop()));
                    }
                }
        );
    }

    public  int rowIndex(double lat) {
        return (int) ((TOP_RIGHT.getLat() - lat) / IS_IN_TOWN);
    }

    public  int colIndex(double lng) {
        return (int) ((lng - BOTTOM_LEFT.getLng()) / IS_IN_TOWN);
    }

    public Map<BusStop, Node> getNodes() {
        return nodes;
    }

    public boolean isInTown(Coord position){
        return Double.compare(position.getLng(),TOP_RIGHT.getLng()) <= 0 && Double.compare(position.getLng(),BOTTOM_LEFT.getLng()) >= 0  &&
                Double.compare(position.getLat(),TOP_RIGHT.getLat()) <= 0 && Double.compare(position.getLat(),BOTTOM_LEFT.getLat()) >= 0; // Lo cambiamos a menor y mayor igual porque no tomaba los casos limites
    }

    private static class Sector {
        private final List<Node> sectorNodes;

        public Sector() {
            this.sectorNodes = new ArrayList<>();
        }

        public void addNode(Node node){
            sectorNodes.add(node);
        }

    }

}
