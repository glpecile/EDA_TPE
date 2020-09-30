import model.BusInPath;
import model.PlaceLocation;
import placefinder.PlaceSearchEngine;

import java.util.*;

public class Controller {

  public Controller() {
  }

  public List<BusInPath> findPath(double fromLat, double fromLng, double toLat, double toLng) {
    return Arrays.asList(new BusInPath("No implementado", 0, 0, 0, 0));
  }

  public List<PlaceLocation> findPlaces(String searchTerm) {
    return new ArrayList<PlaceLocation>(PlaceSearchEngine.getSimilarity(new TreeSet<PlaceLocation>(), searchTerm.toUpperCase()));
  }
}
