package model;

import java.util.List;

public class BusRoute {
    private final String line;
    private final int id_line;
    private final List<Coord> route;

    public BusRoute(String line, int id_line, List<Coord> route) {
        this.line = line;
        this.id_line = id_line;
        this.route = route;
    }

    public String getLine() {
        return line;
    }

    public int getId_line() {
        return id_line;
    }

    public List<Coord> getRoute() {
        return route;
    }

    public static class Coord {
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
}
