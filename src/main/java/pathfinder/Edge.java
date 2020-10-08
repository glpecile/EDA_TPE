package pathfinder;

/**
 * Clase que modela la arista de un grafo.
 */
public class Edge {
    private final Node tail;
    private final double weight;

    /**
     * Crea una nueva arista para el nodo head que la contiene.
     *
     * @param tail   Nodo al que se conecta head a través de la arista.
     * @param weight Peso de la arista.
     */
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
