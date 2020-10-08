import model.*;
import pathfinder.Graph;
import pathfinder.PathFinder;
import placefinder.PlaceSearchEngine;
import reader.BusRouteReader;
import reader.BusStopReader;
import reader.PlacesReader;
import reader.SubwayReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Controller {

    private final Set<PlaceLocation> locations;
    private final PathFinder pathFinder;

    public Controller() throws IOException {
        locations = new PlacesReader().getPlaces();
        List<BusStop> busStops = new BusStopReader().getBusStops();
        List<BusRoute> busRoutes = new BusRouteReader().getBusRoutes();
        Map<String, List<SubwayStop>> subwayStops = new SubwayReader().getSubwayStops();
        // Top_Right: new Coord(-58.319693588930136, -34.51506436193081)
        // Bottom_Left: new Coord(-58.5607209515051, -34.738332689908475)
        Graph graph = new Graph(busStops, busRoutes, subwayStops, new Coord(-58.319693588930136, -34.51506436193081), new Coord(-58.5607209515051, -34.738332689908475));
        pathFinder = new PathFinder(graph);
    }

    public List<BusInPath> findPath(double fromLat, double fromLng, double toLat, double toLng) {
        return pathFinder.findPath(new Coord(fromLng, fromLat), new Coord(toLng, toLat));
    }

    public List<PlaceLocation> findPlaces(String searchTerm) {
        return (PlaceSearchEngine.getSimilarity(locations, searchTerm.toUpperCase()));
    }
}
