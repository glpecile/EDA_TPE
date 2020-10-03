package pathfinder;

import model.BusStop;

import java.util.ArrayList;
import java.util.List;

public class Node {
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
