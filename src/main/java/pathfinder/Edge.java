package pathfinder;

public class Edge {
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

    @Override
    public String toString() {
        return tail.getBusStop().getCoord().toString() + "||" + weight;
    }
}
