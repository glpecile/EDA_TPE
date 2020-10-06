import model.*;
import pathfinder.Graph;
import pathfinder.PathFinder;
import placefinder.PlaceSearchEngine;
import reader.BusRouteReader;
import reader.BusStopReader;
import reader.PlacesReader;

import java.io.IOException;
import java.util.*;

public class Controller {

    private final Set<PlaceLocation> locations;
    private final List<BusStop> busStops;
    private final List<BusRoute> busRoutes;
    private final PathFinder pathFinder;

    public Controller() throws IOException {
        locations = new PlacesReader().getPlaces();
        busStops = new BusStopReader().getBusStops();
        busRoutes = new BusRouteReader().getBusRoutes();
        Graph graph = new Graph(busStops, busRoutes);
        pathFinder = new PathFinder(graph);
        System.out.println("Tamaño values: " + graph.getNodes().values().size());
        List<BusStop> veintidosA = new ArrayList<>();
        int[] edges = {0};
        graph.getNodes().values().forEach(val -> edges[0] += val.getEdges().size());
        System.out.println("Aristas totales: " + edges[0]);
//        final int[] i = {0};
//        graph.getNodes().values().forEach(val -> {
//            if(val.getBusStop().getBusName().equals("371E") && val.getBusStop().getDirectionId() == 0) {
//                i[0] += val.getEdges().size();
//                veintidosA.add(val.getBusStop());
//
//            }
//        });
//        System.out.println("Nodos 276D-0: " + veintidosA.size());
//        System.out.println("Aristas 276D-0: " + i[0]);
    }

    public List<BusInPath> findPath(double fromLat, double fromLng, double toLat, double toLng) {
        return pathFinder.findPath(new Coord(fromLng ,fromLat),new Coord(toLng, toLat));
    }

    public List<PlaceLocation> findPlaces(String searchTerm){
        return new ArrayList<>(PlaceSearchEngine.getSimilarity(locations, searchTerm.toUpperCase()));
    }
}
