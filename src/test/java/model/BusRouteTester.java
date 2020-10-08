package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pathfinder.TestGraphFactory;

import java.util.LinkedList;
import java.util.List;

public class BusRouteTester {

    BusRoute busRoute = new BusRoute("Route", 0, TestGraphFactory.routeFactory());

    @Test
    void gettersTest() {
        Assertions.assertEquals("Route", busRoute.getLine());
        Assertions.assertEquals(0, busRoute.getId_line());
        Assertions.assertEquals(new Coord(0.0, 0.0), busRoute.getRoute().getFirst());
        Assertions.assertEquals(new Coord(0.011, -0.011), busRoute.getRoute().getLast());
        Assertions.assertEquals(new Coord(0.001, -0.008), busRoute.getRoute().get(3));
    }
}
