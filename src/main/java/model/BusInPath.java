package model;

public class BusInPath {
    private final String name;
    private final double fromLat;
    private final double fromLng;
    private double toLat;
    private double toLng;
    private double cost;

    public BusInPath(String name, double fromLat, double fromLng, double toLat, double toLng, double cost) {
        this.name = name;
        this.fromLat = fromLat;
        this.fromLng = fromLng;
        this.toLat = toLat;
        this.toLng = toLng;
        this.cost = cost;
    }
    public BusInPath(String name, Coord from, Coord to, double cost) {
        this.name = name;
        this.fromLat = from.getLat();
        this.fromLng = from.getLng();
        this.toLat = to.getLat();
        this.toLng = to.getLng();
        this.cost = cost;
    }

    public void setToLat(double toLat) {
        this.toLat = toLat;
    }

    public void setToLng(double toLng) {
        this.toLng = toLng;
    }
    public void setTo(Coord newTo){
        setToLat(newTo.getLat());
        setToLng(newTo.getLng());
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

    public void addCost(double extra) {
        this.cost += extra;
    }

    public Coord getToCoord() {
        return new Coord(toLat, toLng);
    }

    public String toString() {
        return String.format("%s - %g =>", name, cost);
    }
}
