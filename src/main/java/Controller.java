import model.BusInPath;
import model.BusRoute;
import model.BusStop;
import model.PlaceLocation;
import pathfinder.Graph;
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

    public Controller() throws IOException {
        locations = new PlacesReader().getPlaces();
        busStops = new BusStopReader().getBusStops();
        busRoutes = new BusRouteReader().getBusRoutes();
        Graph graph = new Graph(busStops, busRoutes);
        System.out.println("Tamaño values: " + graph.nodes.values().size());
        int amount = 0;
        for (Graph.Node value : graph.nodes.values()) {
            amount+= value.edges.size();
        }
        System.out.println(amount);
    }

    public List<BusInPath> findPath(double fromLat, double fromLng, double toLat, double toLng) {
        return Collections.singletonList(new BusInPath("No implementado", 0, 0, 0, 0));
    }

    public List<PlaceLocation> findPlaces(String searchTerm){
        return new ArrayList<>(PlaceSearchEngine.getSimilarity(locations, searchTerm.toUpperCase()));
    }
}
