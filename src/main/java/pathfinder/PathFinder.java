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
        long tInicial = System.currentTimeMillis();
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
            List<BusInPath> bus = Dijkstra(start,from, to, walkingPenalty(from.distanceTo(start.getBusStop().getCoord())));
            BusInPath busCost;
            busCost = bus.get(bus.size() - 1);
            buses.putIfAbsent(busCost.getCost(),bus);
        });
        System.out.println(buses.values().size());
        Collection<List<BusInPath>> path = buses.values();
        for(List<BusInPath> list : path) {
            System.out.println(list);
        }
        long tFinal = System.currentTimeMillis();
        System.out.println("Tiempo de Busqueda: " + (((double)(tFinal - tInicial))/1000.0));
        return buses.firstEntry().getValue();
    }

    // Complejidad: O((N+M)*log(N)).
    private List<BusInPath> Dijkstra(Node startingBusStop,Coord from, Coord to, double initialCost) {
        unmarkAllNodes();
        graph.getNodes().values().forEach(node -> node.setCost(Double.MAX_VALUE));

        PriorityQueue<PqNode> queue = new PriorityQueue<>();
        BusInPath startBusPath = new BusInPath(startingBusStop.getBusStop().getBusName(),from,to, initialCost);
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

//            pqNode.busesInPath.get(pqNode.busesInPath.size() - 1).setCost(pqNode.cost);
//            pqNode.busesInPath.get(pqNode.busesInPath.size() - 1).setTo(pqNode.node.getBusStop().getCoord());


            if(pqNode.node.getBusStop().getCoord().isCloser(to)){
                List<BusInPath> auxList = pqNode.getBusesInPath();
                BusInPath last = auxList.get(auxList.size() - 1);
//                System.out.println("............");
//                System.out.println("PQ: " + pqNode.node.getBusStop().getCoord());
//                System.out.println("PQ COST: " + pqNode.cost);
//                System.out.println("ANTES: " + last.getCost());
//                System.out.println("PENALIDAD : " + walkingPenalty(to.distanceTo(pqNode.node.getBusStop().getCoord())));
                last.setCost(pqNode.cost);
//                last.addCost(walkingPenalty(to.distanceTo(pqNode.node.getBusStop().getCoord())));
                last.addCost(walkingPenalty(pqNode.node.getBusStop().getCoord().distanceTo(to)));
//                System.out.println("DESPUES: " + last.getCost());
                return auxList;
            }

            //pqNode.busesInPath.get(pqNode.busesInPath.size() - 1).setTo(pqNode.node.getBusStop().getCoord());

            for (Edge edge : pqNode.node.getEdges()) {
                double targetNodeCost = pqNode.cost + edge.getWeight();
                if (targetNodeCost < edge.getTail().getCost()) {
                    edge.getTail().setCost(targetNodeCost);
                    List<BusInPath> aux = new ArrayList<>(pqNode.getBusesInPath());

                    if(!pqNode.node.getBusStop().getBusName().equals(edge.getTail().getBusStop().getBusName())) {
                        targetNodeCost += TRANSFER_PENALTY;
                        targetNodeCost += walkingPenalty(pqNode.node.getBusStop().getCoord().distanceTo(edge.getTail().getBusStop().getCoord()));
                        Node edgeNode = edge.getTail();
                        //aux.get(aux.size() - 1).setCost(pqNode.cost);
                        //aux.get(aux.size() - 1).setTo(pqNode.node.getBusStop().getCoord());
                        BusInPath toAdd = new BusInPath(edgeNode.getBusStop().getBusName(), edgeNode.getBusStop().getCoord(), to, targetNodeCost);
                        aux.add(toAdd);
                    }

                    //aux.get(aux.size() - 1).setCost(targetNodeCost);
                    //aux.get(aux.size() - 1).setCost(pqNode.cost);
                    //aux.get(aux.size()-1).setTo(pqNode.node.getBusStop().getCoord());
                    //aux.get(aux.size()-1).setTo(edge.getTail().getBusStop().getCoord()); no hace falta esta actu.
                    /*
                    Si no cambia de bondi, el to es siempre el mismo, si cambia se actualiza cuando cambio usando el pqNode
                    No afecta el walkingPenalty porque se calcula entre el to, y el ultimo Pqnode.
                     */
                    //edge.getTail().setCost(targetNodeCost);
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
        Node node;
        double cost;
        List<BusInPath> busesInPath;
        public PqNode(Node node,List<BusInPath> path, double cost) {
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
