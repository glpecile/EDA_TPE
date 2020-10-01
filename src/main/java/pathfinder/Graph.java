package pathfinder;

import model.BusStop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private final Map<BusStop, Node> nodes;

    public Graph(List<BusStop> busStops) {
        nodes = new HashMap<>();
        generateNodes(busStops);
        generateEdges();
    }

    private void generateNodes(List<BusStop> busStops) {
        for(BusStop busStop : busStops) {
            if(!nodes.containsKey(busStop)) {
                nodes.put(busStop, new Node(busStop));
            }
        }
    }

    private void generateEdges() {

    }

    public static class Node {
        private final BusStop busStop;
        private List<Edge> edges;

        public Node(BusStop busStop) {
            this.busStop = busStop;
        }

        public void addEdge(Node tail, int weight) {
            edges.add(new Edge(tail, weight));
        }

        public BusStop getBusStop() {
            return busStop;
        }
    }

    public static class Edge {
        private final Node tail;
        private final int weight;

        public Edge(Node tail, int weight) {
            this.tail = tail;
            this.weight = weight;
        }

        public Node getTail() {
            return tail;
        }

        public int getWeight() {
            return weight;
        }
    }
}
