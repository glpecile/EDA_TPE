package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BusInPathTester {

    BusInPath busInPath = new BusInPath("Bus", new Coord(0.0, 0.2), new Coord(-12.5, 7.25), 0);

    @Test
    void gettersTest() {
        Assertions.assertEquals("Bus", busInPath.getName());
        Assertions.assertEquals(0.0, busInPath.getFromLng());
        Assertions.assertEquals(0.2,busInPath.getFromLat());
        Assertions.assertEquals(-12.5, busInPath.getToLng());
        Assertions.assertEquals(7.25, busInPath.getToLat());
        Assertions.assertEquals(0.0, busInPath.getCost());
        Assertions.assertEquals(new Coord(-12.5, 7.25), busInPath.getToCoord());
    }

    @Test
     void settersTest() {
        busInPath.setToLat(25.123);
        Assertions.assertEquals(25.123, busInPath.getToLat());
        busInPath.setToLng(-58.8753);
        Assertions.assertEquals(-58.8753, busInPath.getToLng());
        busInPath.setTo(new Coord(-4.2, 12.3));
        Assertions.assertEquals(-4.2, busInPath.getToLng());
        Assertions.assertEquals(12.3, busInPath.getToLat());
        busInPath.setCost(1.45);
        Assertions.assertEquals(1.45, busInPath.getCost());
    }

    @Test
    void addCostTest() {
        busInPath.addCost(3.1);
        Assertions.assertEquals(3.1, busInPath.getCost());
    }

}
