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
            //System.out.println("ENTRE");
            int i = 0;
            BusStop head = null;
            while(i < busRoute.getRoute().size()) {
                BusStop aux = new BusStop(busRoute.getRoute().get(i).getLng(),
                        busRoute.getRoute().get(i).getLat(),
                        busRoute.getId_line(), busRoute.getLine());
                if(nodes.containsKey(aux)) {
                    //System.out.println("ENtre x2");
                    //System.out.println(head);
                    //System.out.println(aux);
                    if(head != null) {
                        nodes.get(head).addEdge(nodes.get(aux), head.getCoord().distanceTo(aux.getCoord()));
                        //System.out.println(head + " - " + aux);
                    }
                    head = aux;
                }
                i++;
            }
        }
    }

    public static class Node {
        //CAMBIAR
        private final BusStop busStop;
        public List<Edge> edges;

        public Node(BusStop busStop) {
            this.busStop = busStop;
            edges = new ArrayList<>();
        }

        public void addEdge(Node tail, double weight) {
            edges.add(new Edge(tail, weight));
        }

        public BusStop getBusStop() {
            return busStop;
        }
    }

    public static class Edge {
        private final Node tail;
        private final double weight;

        public Edge(Node tail, double weight) {
            this.tail = tail;
            this.weight = weight;
        }

        public Node getTail() {
            return tail;
        }

        public double getWeight() {
            return weight;
        }
    }
}
