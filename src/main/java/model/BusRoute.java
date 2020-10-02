package model;

import java.util.LinkedList;

public class BusRoute {
    private final String line;
    private final int id_line;
    private final LinkedList<Coord> route;

    public BusRoute(String line, int id_line, LinkedList<Coord> route) {
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

    public LinkedList<Coord> getRoute() {
        return route;
    }
}
