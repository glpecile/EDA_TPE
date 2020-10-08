package pathfinder;

import model.BusInPath;
import model.Coord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PathFinderTest {

    Graph graph = TestGraphFactory.getTestGraph();
    PathFinder pathFinder = TestGraphFactory.getPathFinder(graph);

    @Test
    void findPathTest() {
        List<BusInPath> path = pathFinder.findPath(new Coord(0.0,0.0), new Coord(0.011,-0.011)); //Negro 0.019
        Assertions.assertEquals("Negro", path.get(0).getName());

        path = pathFinder.findPath(new Coord(0.010000, -0.002000), new Coord(0.014000, -0.014000)); //Rojo
        Assertions.assertEquals("Rojo", path.get(0).getName());

        path = pathFinder.findPath(new Coord(-0.007, 0.0), new Coord(0.015000, -0.015)); //Verde => Negro => Rojo
        Assertions.assertEquals("Verde", path.get(0).getName());
        Assertions.assertEquals("Negro", path.get(1).getName());
        Assertions.assertEquals("Rojo", path.get(2).getName());

        path = pathFinder.findPath(new Coord(0.014, 0.0), new Coord(-0.01, -0.016)); //Azul
        Assertions.assertEquals("Azul", path.get(0).getName());

        path = pathFinder.findPath(new Coord(-0.002, 0.0), new Coord(0.012, -0.0075)); //Negro
        Assertions.assertEquals("Negro", path.get(0).getName());

        path = pathFinder.findPath(new Coord(0.013, -0.004), new Coord(-0.009000, 0.002)); //Azul => Verde
        Assertions.assertEquals("Azul", path.get(0).getName());
        Assertions.assertEquals("Verde", path.get(1).getName());
    }

    @Test
    void NoCloseStops() {
        List<BusInPath> path = pathFinder.findPath(new Coord(0.05, 0.0), new Coord(-0.009000, 0.002)); //No hay paradas cercanas
        Assertions.assertEquals("No hay paradas cercanas.", path.get(0).getName());
    }

    @Test
    void WalkTest() {
        List<BusInPath> path = pathFinder.findPath(new Coord(0.013, -0.004), new Coord(0.013, -0.005)); //From y To muy cerca
        Assertions.assertEquals("Camine.", path.get(0).getName());
    }

    @Test
    void NotFound() {
        List<BusInPath> path = pathFinder.findPath(new Coord(0.0, 0.0), new Coord(0.07, 0)); //No hay paradas cercanas
        Assertions.assertEquals("No encontrado.", path.get(0).getName());
    }
}
