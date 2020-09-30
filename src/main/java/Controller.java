import model.BusInPath;
import model.BusStop;
import model.PlaceLocation;
import placefinder.PlaceSearchEngine;
import reader.BusStopReader;
import reader.PlacesReader;

import java.io.IOException;
import java.util.*;

public class Controller {

    private final Set<PlaceLocation> locations;
    private final List<BusStop> busStops;

    public Controller() throws IOException {
        locations = new PlacesReader().getPlaces();
        busStops = new BusStopReader().getBusStops();
    }

    public List<BusInPath> findPath(double fromLat, double fromLng, double toLat, double toLng) {
        return Collections.singletonList(new BusInPath("No implementado", 0, 0, 0, 0));
    }

    public List<PlaceLocation> findPlaces(String searchTerm){
        return new ArrayList<>(PlaceSearchEngine.getSimilarity(locations, searchTerm.toUpperCase()));
    }
}
