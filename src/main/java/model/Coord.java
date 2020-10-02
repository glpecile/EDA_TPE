package model;

public class Coord {
    private final double lat;
    private final double lng;

    public Coord(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
