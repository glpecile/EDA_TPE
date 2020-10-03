package pathfinder;

import model.BusInPath;
import model.Coord;

import java.util.*;

public class PathFinder {
    private final Graph graph;

    public PathFinder(Graph graph) {
        this.graph = graph;
    }

    private void unmarkAllNodes() {
        graph.getNodes().values().forEach(Node::unmark);
    }

    public List<BusInPath> findPath(Coord from, Coord to) {
        Set<BusInPath> buses = new TreeSet<>(Comparator.comparing(BusInPath::getCost).thenComparing(BusInPath::getName));
        List<Node> closestStartStops = new ArrayList<>();
        List<Node> closestEndStops = new ArrayList<>();
        graph.getNodes().forEach( (stop,node) -> {
            if (stop.getCoord().isCloser(from)) {
                closestStartStops.add(node);
            }
            if (stop.getCoord().isCloser(to)) {
                closestEndStops.add(node);
            }
        });
        closestStartStops.forEach(stop -> buses.add(Dijkstra(stop,closestEndStops)));
        return new ArrayList<>(buses);
    }

    // Complejidad: O((N+M)*log(N)).
    private BusInPath Dijkstra(Node startingBusStop, List<Node> endStops) {
        unmarkAllNodes();
        graph.getNodes().values().forEach(node -> node.setCost(Double.MAX_VALUE));

        PriorityQueue<PqNode> queue = new PriorityQueue<>();
        queue.add(new PqNode(startingBusStop,0));

        while (!queue.isEmpty()) {
            PqNode pqNode = queue.remove();
            if (pqNode.node.isMarked()) continue;
            pqNode.node.mark();
            /*
            if (estamos cerca del destino)
                return camino de llegada;
             */
            if (endStops.contains(pqNode.node))
                return new BusInPath(pqNode.node.getBusStop().getBusName(),
                        startingBusStop.getBusStop().getCoord().getLat(),
                        startingBusStop.getBusStop().getCoord().getLng(),
                        pqNode.node.getBusStop().getCoord().getLat(),
                        pqNode.node.getBusStop().getCoord().getLng(),
                        pqNode.cost);
//            System.out.println(pqNode.node.getBusStop() + ": " + pqNode.cost);

            for (Edge edge : pqNode.node.getEdges()) {
                double targetNodeCost = pqNode.cost + edge.getWeight();
                if (targetNodeCost < edge.getTail().getCost()) {
                    edge.getTail().setCost(targetNodeCost);
                    queue.add(new PqNode(edge.getTail(), targetNodeCost));
                }
            }
        }
        return null;
    }

    static private class PqNode implements Comparable<PqNode> {
        Node node;
        double cost;

        public PqNode(Node node, double cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(PqNode other) {
            return Double.compare(cost, other.cost);
        }
    }
}
