package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BusInPathTester {

    BusInPath busInPath;

    @BeforeEach
    void init() {
        busInPath = new BusInPath("Bus", new Coord(0.0, 0.2), new Coord(-12.5, 7.25), 0);
    }


    @Test
    void gettersTest() {
        init();
        Assertions.assertEquals("Bus", busInPath.getName());
        Assertions.assertEquals(0.0, busInPath.getFromLng());
        Assertions.assertEquals(0.2,busInPath.getFromLat());
        Assertions.assertEquals(-12.5, busInPath.getToLng());
        Assertions.assertEquals(7.5, busInPath.getToLat());
        Assertions.assertEquals(0.0, busInPath.getCost());
        Assertions.assertEquals(new Coord(-12.5, 7.25), busInPath.getToCoord());
    }

    @Test
     void setTest() {
        busInPath.setToLat(25.123);
        Assertions.assertEquals(25.123, );
    }
}
