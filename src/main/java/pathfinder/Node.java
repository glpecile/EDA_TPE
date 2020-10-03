package pathfinder;

import model.BusStop;

import java.util.ArrayList;
import java.util.List;

public class Node {
    //CAMBIAR
    private final BusStop busStop;
    private List<Edge> edges;
    private boolean marked;
    private double cost=0;

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

    void mark() {
        marked = true;
    }

    void unmark() {
        marked = false;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isMarked() {
        return marked;
    }

    public double getCost() {
        return cost;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
