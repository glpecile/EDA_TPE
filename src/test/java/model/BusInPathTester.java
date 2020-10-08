package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BusInPathTester {

    BusInPath busInPath;

    @BeforeAll
    void init() {
        busInPath = new BusInPath("Bus", new Coord(0.0, 0.2), new Coord(-12.5, 7.25), 0);
    }


    @Test
    void gettersTest() {
        init();
        Assertions.assertEquals("Bus", busInPath.getName());
        Assertions.assertEquals(0.0, busInPath.getFromLng());
        Assertions.assertEquals(0.2,0.2);

    }
}
