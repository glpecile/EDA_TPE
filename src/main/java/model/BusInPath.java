package model;

/**
 * Clase usada para mostrar un colectivo y manejar en el algoritmo de PathFinder.
 */
public class BusInPath {
    private final String name;
    private final double fromLat;
    private final double fromLng;
    private double toLat;
    private double toLng;
    private double cost;

    /**
     * Crea una instancia de un recorrido de colectivo a mostrar.
     *
     * @param name,    nombre de la linea de colectivo.
     * @param fromLat, latitud de donde comienza el recorrido.
     * @param fromLng, longitud de donde comienza el recorrido.
     * @param toLat,   latitud de donde finaliza el recorrido.
     * @param toLng,   longitud de donde finaliza el recorrido.
     * @param cost,    costo del recorrido.
     */
    public BusInPath(String name, double fromLat, double fromLng, double toLat, double toLng, double cost) {
        this.name = name;
        this.fromLat = fromLat;
        this.fromLng = fromLng;
        this.toLat = toLat;
        this.toLng = toLng;
        this.cost = cost;
    }

    /**
     * Crea una instancia de un recorrido de colectivo haciendo uso de la clase de Coord.
     *
     * @param name, nombre de la linea de colectivo.
     * @param from, coordenadas de longitud y latitud de partida del recorrido presentes en la
     *              clase Coord.
     * @param to,   coordenadas de longitud y latitud de partida del recorrido presentes en la
     *              clase Coord.
     * @param cost, costo del recorrido.
     */
    public BusInPath(String name, Coord from, Coord to, double cost) {
        this(name, from.getLat(), from.getLng(), to.getLat(), to.getLng(), cost);
    }

    public String getName() {
        return name;
    }

    public double getFromLat() {
        return fromLat;
    }

    public double getFromLng() {
        return fromLng;
    }

    public double getToLat() {
        return toLat;
    }

    public double getToLng() {
        return toLng;
    }

    public double getCost() {
        return cost;
    }

    public Coord getToCoord() {
        return new Coord(toLng, toLat);
    }

    public void setToLat(double toLat) {
        this.toLat = toLat;
    }

    public void setToLng(double toLng) {
        this.toLng = toLng;
    }

    public void setTo(Coord newTo) {
        setToLng(newTo.getLng());
        setToLat(newTo.getLat());
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void addCost(double extra) {
        this.cost += extra;
    }

    @Override
    public String toString() {
        return String.format("%s - %g =>", name, cost);
    }
}
