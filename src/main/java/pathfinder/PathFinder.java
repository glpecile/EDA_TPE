package pathfinder;

public class PathFinder {
    private final Graph graph;

    public PathFinder(Graph graph) {
        this.graph = graph;
    }

    class PqNode implements Comparable<PqNode> {
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
