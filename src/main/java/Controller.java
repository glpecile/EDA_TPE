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
    private final PathFinder pathFinder;

    public Controller() throws IOException {
//        BusStopReader busStopReader = new BusStopReader();
        locations = new PlacesReader().getPlaces();
        List<BusStop> busStops = new BusStopReader().getBusStops();
        List<BusRoute> busRoutes = new BusRouteReader().getBusRoutes();
        // Top_Right: new Coord(-58.319693588930136, -34.51506436193081)
        // Bottom_Left: new Coord(-58.5607209515051, -34.738332689908475)
        Graph graph = new Graph(busStops, busRoutes,new Coord(-58.319693588930136, -34.51506436193081),new Coord(-58.5607209515051, -34.738332689908475));
//        Graph graph = new Graph(busStopReader.getBusStops(), busRoutes,busStopReader.getTopRight(),busStopReader.getBottomLeft());
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
