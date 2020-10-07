package pathfinder;

import model.BusInPath;
import model.BusRoute;
import model.BusStop;
import model.Coord;

import java.util.ArrayList;
import java.util.List;

public class TestGraphFactory {
    public static void main(String[] args) {
        Graph graph = getTestGraph();
        int i = 1;
        for(Node node : graph.getNodes().values()) {
            BusStop busStop = node.getBusStop();
            System.out.printf("%d | Linea: %s | Lng: %g | Lat: %g\n", i++, busStop.getBusName(), busStop.getCoord().getLng(), busStop.getCoord().getLat());
        }
        System.out.println("------------------------");
        Node firstBlack = graph.getNodes().get(new BusStop(0.000000,0.0000000,0, "Negro"));
        i = 1;
        for(Edge edge : firstBlack.getEdges()) {
            BusStop busStop = edge.getTail().getBusStop();
            double weight = edge.getWeight();
            System.out.printf("%d | Línea: %s | Lng: %g | Lat: %g | Weight: %g\n",
                    i++, busStop.getBusName(), busStop.getCoord().getLng(), busStop.getCoord().getLat(), weight);
        }

        System.out.println("------------------------");
        PathFinder pathFinder = getPathFinder(graph);
        pathFinder.findPath(new Coord(0.000000,0.000000), new Coord(0.011000,-0.011000)); //Negro 0.019 aprox
        System.out.println("------------------------");
//        pathFinder.findPath(new Coord(0.000000,0.000000), new Coord(0.02,0.0)); //Negro => Verde (CON EL GRAFO PEQUEÑO) 1.02402 creo
//        System.out.println("------------------------");
        //INVERTIR COORD
        pathFinder.findPath(new Coord(0.010000, -0.002000), new Coord(0.014000, -0.014000)); //Rojo
        System.out.println("------------------------");
        pathFinder.findPath(new Coord(-0.007, 0.0), new Coord(0.015000, -0.015)); //Verde => Negro => Rojo
        System.out.println("------------------------");
        pathFinder.findPath(new Coord(0.014, 0.0), new Coord(-0.01, -0.016)); //Azul
        System.out.println("------------------------");
        pathFinder.findPath(new Coord(-0.002, 0.0), new Coord(0.012, -0.0075)); //Negro
        System.out.println("------------------------");
        pathFinder.findPath(new Coord(0.013, -0.004), new Coord(-0.009000, 0.002)); //Azul => Verde
    }

    public static Graph getTestGraph() {
        List<BusStop> busStops = new ArrayList<>();
        List<BusRoute> busRoutes = new ArrayList<>(); //Puede estar vacio. Se crean las aristas por proximidad.
        generateBusStops(busStops);
        return new Graph(busStops, busRoutes, new Coord(0.018,0.00), new Coord(-0.018,-0.018));
    }

    public static PathFinder getPathFinder(Graph graph) {
        return new PathFinder(graph);
    }

    private static void generateBusStops(List<BusStop> busStops) {
        //Linea "Negro" - 8 paradas
        busStops.add(new BusStop(0.000000,0.0000000,0, "Negro"));
        busStops.add(new BusStop(0.000000,-0.003000,0, "Negro"));
        busStops.add(new BusStop(0.000000,-0.006000,0, "Negro"));
        busStops.add(new BusStop(0.001000,-0.008000,0, "Negro"));
        busStops.add(new BusStop(0.003000,-0.01000,0, "Negro"));
        busStops.add(new BusStop(0.005000,-0.011000,0, "Negro"));
        busStops.add(new BusStop(0.008000,-0.011000,0, "Negro"));
        busStops.add(new BusStop(0.011000,-0.011000,0, "Negro"));

        //Linea "Azul" - 11 paradas
        double lng = -0.007;
        double lat = -0.014;
        for(int i = 0; i < 6; i++) {
            busStops.add(new BusStop(lng, lat, 0, "Azul"));
            lng += 0.001000;
            lat += 0.002000;
        }

        lng = -0.001000;
        lat = -0.002000;
        for(int i = 0; i < 5; i++) {
            busStops.add(new BusStop(lng, lat, 0, "Azul"));
            lng += 0.003000;
        }

        //Linea "Verde" - 14 paradas
        lng = -0.001000;
        lat = 0.000000;
        for(int i = 0; i < 6; i++) {
            busStops.add(new BusStop(lng, lat, 0, "Verde"));
            busStops.add(new BusStop(lng - 0.006000, lat, 0, "Verde"));
            lat -= 0.003000;
        }
        busStops.add(new BusStop(-0.004000,0.000000,0, "Verde"));
        busStops.add(new BusStop(-0.004000,-0.015000,0, "Verde"));

        //Linea "Rojo" - 6 paradas
        lng = 0.012000;
        lat = -0.002000;
        for(int i = 0; i < 5; i++) {
            busStops.add(new BusStop(lng, lat, 0, "Rojo"));
            lat -= 0.003000;
        }
        busStops.add(new BusStop(0.015000,-0.015000,0, "Rojo"));

        //MINI GRAFO
//        busStops.add(new BusStop(0.000000,0.0000000,0, "Negro"));
//        busStops.add(new BusStop(0.004000,0.0000,0, "Negro"));
//        busStops.add(new BusStop(0.008000,0.00000,0, "Negro"));
//
//        busStops.add(new BusStop(0.012000,0.000000,0, "Verde"));
//        busStops.add(new BusStop(0.016000,0.000,0, "Verde"));
//        busStops.add(new BusStop(0.020000,0.000,0, "Verde"));

    }

}
