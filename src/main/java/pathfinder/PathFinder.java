package pathfinder;

import model.BusInPath;
import model.Coord;

import java.util.*;

public class PathFinder {
    private final Graph graph;
    private static final double WALKING_PENALTY = 0.01;
    private static final double TRANSFER_PENALTY = 1;

    public PathFinder(Graph graph) {
        this.graph = graph;
    }

    private void unmarkAllNodes() {
        graph.getNodes().values().forEach(Node::unmark);
    }

    public List<BusInPath> findPath(Coord from, Coord to) {
        if (from.isCloser(to)) {
            return Collections.singletonList(new BusInPath("Camine.",from,to,from.distanceTo(to)));
        }
        TreeMap<Double,List<BusInPath>> buses = new TreeMap<>();
        List<Node> closestStartStops = new ArrayList<>();
        graph.getNodes().forEach( (stop,node) -> {
            if (stop.getCoord().isCloser(from)) {
                closestStartStops.add(node);
            }
        });
        closestStartStops.forEach(start -> {
            List<BusInPath> bus = Dijkstra(start,to, walkingPenalty(from.distanceTo(start.getBusStop().getCoord())));
            BusInPath busCost;
            busCost = bus.get(bus.size() - 1);
            buses.putIfAbsent(busCost.getCost(),bus);
        });
        System.out.println(buses.values().size());
        Collection<List<BusInPath>> path = buses.values();
        for(List<BusInPath> list : path) {
            System.out.println(list);
        }
        return buses.firstEntry().getValue();
    }

    // Complejidad: O((N+M)*log(N)).
    private List<BusInPath> Dijkstra(Node startingBusStop, Coord to, double initialCost) {
        unmarkAllNodes();
        graph.getNodes().values().forEach(node -> node.setCost(Double.MAX_VALUE));

        PriorityQueue<PqNode> queue = new PriorityQueue<>();
        BusInPath startBusPath = new BusInPath(startingBusStop.getBusStop().getBusName(),startingBusStop.getBusStop().getCoord(),to, initialCost);
        List<BusInPath> initialList = new ArrayList<>();
        initialList.add(startBusPath);
        queue.add(new PqNode(startingBusStop, initialList,initialCost));

        while (!queue.isEmpty()) {
            PqNode pqNode = queue.remove();
            if (pqNode.node.isMarked()) continue;
            pqNode.node.mark();
            /*
            if (estamos cerca del destino)
                return camino de llegada;
            <=> System.out.println(pqNode.node.getBusStop() + ": " + pqNode.cost);
             */
            if(pqNode.node.getBusStop().getCoord().isCloser(to)){
                List<BusInPath> auxList = pqNode.getBusesInPath();
                BusInPath last = auxList.get(auxList.size() - 1);
                last.addCost(walkingPenalty(to.distanceTo(last.getToCoord())));
                return auxList;
            }
            for (Edge edge : pqNode.node.getEdges()) {
                double targetNodeCost = pqNode.cost + edge.getWeight();
                if (targetNodeCost < edge.getTail().getCost()) {
                    edge.getTail().setCost(targetNodeCost);
                    List<BusInPath> aux = new ArrayList<> (pqNode.getBusesInPath());
                    if(!pqNode.node.getBusStop().getBusName().equals(edge.getTail().getBusStop().getBusName())) {
                        targetNodeCost += TRANSFER_PENALTY;
                        Node edgeNode = edge.getTail();
//                      aux.get(aux.size()-1).setTo(edgeNode.getBusStop().getCoord());
                        BusInPath toAdd = new BusInPath(edgeNode.getBusStop().getBusName(),edgeNode.getBusStop().getCoord(),to, targetNodeCost);
                        aux.add(toAdd);
                    }
                    queue.add(new PqNode(edge.getTail(), aux, targetNodeCost));
                }

            }

        }
        return new ArrayList<>();
    }

    static private class PqNode implements Comparable<PqNode> {
        Node node;
        double cost;
        List<BusInPath> busesInPath;
        public PqNode(Node node,List<BusInPath> path, double cost) {
            this.node = node;
            this.cost = cost;
            busesInPath = path;
        }

        public List<BusInPath> getBusesInPath() {
            return busesInPath;
        }

        @Override
        public int compareTo(PqNode other) {
            return Double.compare(cost, other.cost);
        }
    }

    private double walkingPenalty(double walkingDistance) {
        return Math.pow(Math.E, 5 * walkingDistance) - 1;
    }
}
