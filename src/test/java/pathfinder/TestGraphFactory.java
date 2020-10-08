package pathfinder;

import model.*;

import java.util.*;

public class TestGraphFactory {

    public static Graph getTestGraph() {
        List<BusStop> busStops = new ArrayList<>();
        List<BusRoute> busRoutes = new ArrayList<>();
        busRoutes.add(new BusRoute("Negro", 0, routeFactory()));
        Map<String, List<SubwayStop>> subwayStops = new HashMap<>();
        generateBusStops(busStops, subwayStops);
        return new Graph(busStops, busRoutes, subwayStops, new Coord(0.018,0.00), new Coord(-0.018,-0.018));
    }

    public static PathFinder getPathFinder(Graph graph) {
        return new PathFinder(graph);
    }

    private static void generateBusStops(List<BusStop> busStops, Map<String, List<SubwayStop>> subwayStops) {
        //Linea "Negro" - 8 paradas - Colectivo
        busStops.add(new BusStop(0.000000,0.0000000,0, "Negro"));
        busStops.add(new BusStop(0.000000,-0.003000,0, "Negro"));
        busStops.add(new BusStop(0.000000,-0.006000,0, "Negro"));
        busStops.add(new BusStop(0.001000,-0.008000,0, "Negro"));
        busStops.add(new BusStop(0.003000,-0.01000,0, "Negro"));
        busStops.add(new BusStop(0.005000,-0.011000,0, "Negro"));
        busStops.add(new BusStop(0.008000,-0.011000,0, "Negro"));
        busStops.add(new BusStop(0.011000,-0.011000,0, "Negro"));

        //Linea "Azul" - 11 paradas - Colectivo
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

        //Linea "Verde" - 14 paradas - Colectivo
        lng = -0.001000;
        lat = 0.000000;
        for(int i = 0; i < 6; i++) {
            busStops.add(new BusStop(lng, lat, 0, "Verde"));
            busStops.add(new BusStop(lng - 0.006000, lat, 0, "Verde"));
            lat -= 0.003000;
        }
        busStops.add(new BusStop(-0.004000,0.000000,0, "Verde"));
        busStops.add(new BusStop(-0.004000,-0.015000,0, "Verde"));

        //Linea "Rojo" - 6 paradas - Subte
        lng = 0.012000;
        lat = -0.002000;
        List<SubwayStop> line = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            line.add(new SubwayStop(lng, lat, "Rojo"));
            lat -= 0.003000;
        }
        line.add(new SubwayStop(0.015000,-0.015000,"Rojo"));
        subwayStops.put("Rojo" , line);
    }

    public static LinkedList<Coord> routeFactory() {
        LinkedList<Coord> list = new LinkedList<>();
        list.add(new Coord(0.000000,0.0000000));
        list.add(new Coord(0.000000,-0.003000));
        list.add(new Coord(0.000000,-0.006000));
        list.add(new Coord(0.001000,-0.008000));
        list.add(new Coord(0.003000,-0.01000));
        list.add(new Coord(0.005000,-0.011000));
        list.add(new Coord(0.008000,-0.011000));
        list.add(new Coord(0.011000,-0.011000));
        return list;
    }
}
