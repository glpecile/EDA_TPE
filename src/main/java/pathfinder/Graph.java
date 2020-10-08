package pathfinder;

import model.BusRoute;
import model.BusStop;
import model.Coord;
import model.SubwayStop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que modela un grafo dirigido con peso
 */
public class Graph {
    public final Coord TOP_RIGHT;
    public final Coord BOTTOM_LEFT;
    public static final double WALKING_DISTANCE = Coord.IS_CLOSER;
    public final int MAX_ROW;
    public final int MAX_COL;

    private final Map<BusStop, Node> nodes;
    private final Sector[][] matrix;

    /**
     * Crea un nuevo grafo. Genera los nodos y los conecta con aristas.
     * Los nodos están alocados en un HashMap.
     * También se crea una matriz que sectoriza los nodos y los agrupa por cercanía.
     *
     * @param busStops,    List que contiene las paradas de colectivo, representa nodos.
     * @param busRoutes,   List que contiene las rutas de cada colectivo.
     * @param subwayStops, Map que contiene las líneas de subte con cada estación, que representa
     *                    un nodo.
     * @param topRight,    Coord que representa el límite superior derecho de la ubicación de los
     *                    nodos.
     * @param bottomLeft,  Coord que representa el límite inferior izquiedo de la ubicación de
     *                     los nodos.
     */
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

    /**
     * Devuelve la fila de la matriz a la que corresponde lat.
     *
     * @param lat, latitud.
     * @return Int, índice de la matriz, correspondiente a la fila.
     */
    public int rowIndex(double lat) {
        return (int) ((TOP_RIGHT.getLat() - lat) / WALKING_DISTANCE);
    }

    /**
     * Devuelve la columna de la matriz a la que corresponde lng.
     *
     * @param lng, longitud.
     * @return Int, índice de la matriz, correspondiente a la columnd.
     */
    public int colIndex(double lng) {
        return (int) ((lng - BOTTOM_LEFT.getLng()) / WALKING_DISTANCE);
    }

    /**
     * Permite saber si una coordenada está dentro de los límites del grafo.
     *
     * @param position, Coord, representa una coordenada geográfica.
     * @return true si la coordenada está dentro de los límites "geográficos" del grafo.
     */
    public boolean isInTown(Coord position) {
        return Double.compare(position.getLng(), TOP_RIGHT.getLng()) <= 0 && Double.compare(position.getLng(), BOTTOM_LEFT.getLng()) >= 0 &&
                Double.compare(position.getLat(), TOP_RIGHT.getLat()) <= 0 && Double.compare(position.getLat(), BOTTOM_LEFT.getLat()) >= 0; // Lo cambiamos a menor y mayor igual porque no tomaba los casos limites
    }

    /**
     * Crea los nodos del grafo.
     *
     * @param busStops,    paradas de colectivo.
     * @param subwayStops, estaciones de subte.
     */
    private void generateNodes(List<BusStop> busStops, Map<String, List<SubwayStop>> subwayStops) {
        addNodes(busStops);
        for (List<SubwayStop> subwayStopList : subwayStops.values()) {
            addNodes(subwayStopList);
        }
    }

    /**
     * Añade una lista de nodos al grafo.
     *
     * @param busStops, lista de nodos a ser añadida.
     */
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

    /**
     * Genera las arista del grafo.
     *
     * @param busRoutes,   lista con los recorridos de cada colectivo.
     * @param subwayStops, estaciones de subte.
     */
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

    /**
     * Crea una arista desde el nodo head hacia los nodos cercanos de la lista sectorNodes.
     *
     * @param head,        nodo.
     * @param sectorNodes, lista con las posibles tails de head.
     */
    private void connectNodes(Node head, List<Node> sectorNodes) {
        sectorNodes.forEach((tail) -> {
                    if (head.getBusStop().isCloser(tail.getBusStop())) {
                        head.addEdge(tail, head.getBusStop().distanceTo(tail.getBusStop()));
                    }
                }
        );
    }

    /**
     * En base a una coordenada, busca los nodos cercanos.
     *
     * @param location, coordenada.
     * @return List con los nodos cercanos a la coordenada dada.
     */
    public List<Node> getClosest(Coord location) {
        List<Node> toReturn = new ArrayList<>();
        int row = rowIndex(location.getLat());
        int col = colIndex(location.getLng());
        if (row < 0 || row >= MAX_ROW || col < 0 || col >= MAX_COL) {
            return new ArrayList<>();
        }
        if (matrix[row][col] != null) {
            matrix[row][col].sectorNodes.forEach(node -> {
                for (int k = row - 1; k <= row + 1; k++) {
                    for (int l = col - 1; l <= col + 1; l++) {
                        if (k >= 0 && k < MAX_ROW && l >= 0 && l < MAX_COL && matrix[k][l] != null) {
                            matrix[k][l].sectorNodes.forEach((closest) -> {
                                if (location.isCloser(closest.getBusStop().getCoord())) {
                                    toReturn.add(closest);
                                }
                            });
                        }
                    }
                }
            });
        }
        return toReturn;
    }

    /**
     * Clase que representa los elementos de la matriz del grafo.
     */
    private static class Sector {
        private final List<Node> sectorNodes;

        /**
         * Crea un sector en la matriz. Cada sector está asociado a unas coordenadas geográficas, y tiene una lista de nodos.
         */
        public Sector() {
            this.sectorNodes = new ArrayList<>();
        }

        /**
         * Añade un nodo al sector.
         *
         * @param node, nodo a añadir.
         */
        public void addNode(Node node) {
            sectorNodes.add(node);
        }
    }

}
