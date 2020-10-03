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
        List<BusStop> veintidosA = new ArrayList<>();
        int edges[] = {0};
        graph.nodes.values().forEach(val -> edges[0] += val.edges.size());
        System.out.println("Aristas totales: " + edges[0]);
        final int[] i = {0};
        graph.nodes.values().forEach(val -> {
            if(val.getBusStop().getBusName().equals("371E") && val.getBusStop().getDirectionId() == 0) {
                i[0] += val.edges.size();
                veintidosA.add(val.getBusStop());

            }
        });
        System.out.println("Nodos 276D-0: " + veintidosA.size());
        System.out.println("Aristas 276D-0: " + i[0]);
    }

    public List<BusInPath> findPath(double fromLat, double fromLng, double toLat, double toLng) {
        return Collections.singletonList(new BusInPath("No implementado", 0, 0, 0, 0));
    }

    public List<PlaceLocation> findPlaces(String searchTerm){
        return new ArrayList<>(PlaceSearchEngine.getSimilarity(locations, searchTerm.toUpperCase()));
    }
}
