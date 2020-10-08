package model;

import java.util.LinkedList;

/**
 * Clase usada para detallar el recorrido de un colectivo.
 */
public class BusRoute {
    private final String line;
    private final int id_line;
    private final LinkedList<Coord> route;

    /**
     * Crea una instancia de la ruta de un colectivo, usada en su respectivo reader.
     *
     * @param line,    nombre de linea de un colectivo.
     * @param id_line, dirección del recorrido del colectivo.
     * @param route,   listado de coordenadas que recorre.
     */
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
