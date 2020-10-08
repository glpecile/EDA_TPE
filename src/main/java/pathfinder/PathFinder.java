package pathfinder;

import model.BusInPath;
import model.Coord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class PathFinder {
    private final Graph graph;

    public PathFinder(Graph graph) {
        this.graph = graph;
    }

    private void unmarkAllNodes() {
        graph.getNodes().values().forEach(Node::unmark);
    }

    public List<BusInPath> findPath(Coord from, Coord to) {
        long tInicial = System.currentTimeMillis();

        if (from.isCloser(to)) {
            return Collections.singletonList(new BusInPath("Camine.", from, to, from.distanceTo(to)));
        }
        List<Node> closestStartStops = new ArrayList<>();
        graph.getNodes().forEach((stop, node) -> {
            if (stop.getCoord().isCloser(from)) {
                closestStartStops.add(node);
            }
        });// aca podriamos hacer lo de busqueda por los sectores para hacerlo mas eficiente
        if (closestStartStops.isEmpty()) {
            return Collections.singletonList(new BusInPath("No hay paradas cercanas.", from, to, 10));
        }
        List<BusInPath> bus = Dijkstra(closestStartStops, from, to);
        System.out.println(bus);

        long tFinal = System.currentTimeMillis();
        System.out.println("Tiempo de Busqueda: " + (((double) (tFinal - tInicial)) / 1000.0));
        return bus;
    }

    // Complejidad: O((N+M)*log(N)).
    private List<BusInPath> Dijkstra(List<Node> startingBusStops, Coord from, Coord to) {
        unmarkAllNodes();
        graph.getNodes().values().forEach(node -> node.setCost(Double.MAX_VALUE));

        PriorityQueue<PqNode> queue = new PriorityQueue<>();
        startingBusStops.forEach((startingBusStop) -> {
            double initialCost = walkingPenalty(from.distanceTo(startingBusStop.getBusStop().getCoord()));
            List<BusInPath> initialList = new ArrayList<>();
            initialList.add(new BusInPath(startingBusStop.getBusStop().getBusName(), from, to, initialCost));
            queue.add(new PqNode(startingBusStop, initialList, initialCost));
        });

        while (!queue.isEmpty()) {
            PqNode pqNode = queue.remove();
            if (pqNode.node.isMarked()) continue;
            pqNode.node.mark();

            if (pqNode.node.getBusStop().getCoord().isCloser(to)) {
                List<BusInPath> auxList = pqNode.getBusesInPath();
                BusInPath last = auxList.get(auxList.size() - 1);
                last.setCost(pqNode.cost);
                last.addCost(walkingPenalty(pqNode.node.getBusStop().getCoord().distanceTo(to)));
                return auxList;
            }

            for (Edge edge : pqNode.node.getEdges()) {
                double targetNodeCost = pqNode.cost + edge.getWeight();
                boolean transfer = !pqNode.node.getBusStop().equalsLine(edge.getTail().getBusStop());
                if (transfer) {
                    targetNodeCost += (pqNode.node.getBusStop().getTransferPenalty() + edge.getTail().getBusStop().getTransferPenalty());
                    targetNodeCost += walkingPenalty(pqNode.node.getBusStop().distanceTo(edge.getTail().getBusStop()));
                }
                if (targetNodeCost < edge.getTail().getCost()) {
                    List<BusInPath> aux = new ArrayList<>(pqNode.getBusesInPath());
                    if (transfer) {
                        Node edgeNode = edge.getTail();
                        aux.add(new BusInPath(edgeNode.getBusStop().getBusName(), edgeNode.getBusStop().getCoord(), to, targetNodeCost));
                    }
                    edge.getTail().setCost(targetNodeCost);
                    queue.add(new PqNode(edge.getTail(), aux, targetNodeCost));
                }
            }
        }

        List<BusInPath> notFound = new ArrayList<>();
        notFound.add(new BusInPath("No encontrado", from, to, 10));
        return notFound;
    }

    private double walkingPenalty(double walkingDistance) {
        return Math.pow(Math.E, walkingDistance) - 1;
    }

    private static class PqNode implements Comparable<PqNode> {
        private final Node node;
        private final double cost;
        private final List<BusInPath> busesInPath;

        public PqNode(Node node, List<BusInPath> path, double cost) {
            this.node = node;
            this.cost = cost;
            this.busesInPath = path;
        }

        public List<BusInPath> getBusesInPath() {
            return busesInPath;
        }

        @Override
        public String toString() {
            return node.getBusStop().getCoord().toString();
        }

        @Override
        public int compareTo(PqNode other) {
            return Double.compare(cost, other.cost);
        }
    }

}
