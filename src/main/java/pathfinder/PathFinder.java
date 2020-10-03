package pathfinder;

import model.BusInPath;
import model.Coord;

import java.util.*;
import java.util.stream.Collectors;

public class PathFinder {
    private final Graph graph;

    public PathFinder(Graph graph) {
        this.graph = graph;
    }

    private void unmarkAllNodes() {
        graph.getNodes().values().forEach(Node::unmark);
    }

    public List<BusInPath> findPath(Coord from, Coord to) {
        Map<String,BusInPath> buses = new HashMap<>();
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
        System.out.println(closestStartStops);
        System.out.println(closestEndStops);
        closestStartStops.forEach(start -> {
            BusInPath bus = Dijkstra(start,closestEndStops);
            if(bus != null) {
                BusInPath aux = buses.getOrDefault(bus.getName(), null);
                if (aux == null || bus.getCost() < aux.getCost())
                    buses.put(bus.getName(), bus);
            }
        });
        return buses.values().stream().sorted(Comparator.comparing(BusInPath::getCost).thenComparing(BusInPath::getName)).collect(Collectors.toList());
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

//            System.out.println(pqNode.node.getBusStop() + ": " + pqNode.cost);

            for (Edge edge : pqNode.node.getEdges()) {
                double targetNodeCost = pqNode.cost + edge.getWeight();
                if(!pqNode.node.getBusStop().getBusName().equals(edge.getTail().getBusStop().getBusName())) {
                    targetNodeCost *= 1000;
                }
                if (targetNodeCost < edge.getTail().getCost()) {
                    edge.getTail().setCost(targetNodeCost);
                    queue.add(new PqNode(edge.getTail(), targetNodeCost));
                }

            }
            if (endStops.contains(pqNode.node)) {
                System.out.println(pqNode.node.getBusStop());
                return new BusInPath(pqNode.node.getBusStop().getBusName(),
                        startingBusStop.getBusStop().getCoord().getLat(),
                        startingBusStop.getBusStop().getCoord().getLng(),
                        pqNode.node.getBusStop().getCoord().getLat(),
                        pqNode.node.getBusStop().getCoord().getLng(),
                        pqNode.cost);
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
