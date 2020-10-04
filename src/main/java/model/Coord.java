package model;

import java.util.Objects;


public class Coord {
    private static final double IS_CLOSER = 0.005;
    private static final double ROUND = 1000000; //Tantos ceros como decimales
    private final double lat;
    private final double lng;

    public Coord(double lat, double lng) {
        this.lat = Math.round(lat * ROUND) / ROUND;
        this.lng = Math.round(lng * ROUND) / ROUND;
//        this.lat = lat;
//        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return Double.compare(coord.lat, lat) == 0 &&
                Double.compare(coord.lng, lng) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }

    /*
    TODO
     */
    public double distanceTo(Coord other) {
        return Math.sqrt(Math.pow((this.lat-other.lat),2)+Math.pow((this.lng-other.lng),2));
    }

    public boolean isCloser(Coord other) {
        return (this.distanceTo(other) < IS_CLOSER );
    }
}
