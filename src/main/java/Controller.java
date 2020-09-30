import model.BusInPath;
import model.PlaceLocation;
import placefinder.PlaceSearchEngine;
import reader.PlacesReader;

import java.io.IOException;
import java.util.*;

public class Controller {

  public Controller() {
  }

  public List<BusInPath> findPath(double fromLat, double fromLng, double toLat, double toLng) {
    return Collections.singletonList(new BusInPath("No implementado", 0, 0, 0, 0));
  }

  public List<PlaceLocation> findPlaces(String searchTerm) throws IOException {
    Set<PlaceLocation> locations;
//    locations.add(new PlaceLocation("PALACIO BAROLO"));
//    locations.add(new PlaceLocation("ESCUELA METROPOLITANA DE ARTE DRAMATICA"));
//    locations.add(new PlaceLocation("GIER MUSIC"));
//    locations.add(new PlaceLocation("BOVEDA QUE GUARDA LOS RESTOS DE CARLOS GARDEL"));
//    locations.add(new PlaceLocation("ARCHIVO HISTORICO DEL CREMATORIO MUNICIPAL"));
//    locations.add(new PlaceLocation("CREMATORIO MUNICIPAL"));
//    locations.add(new PlaceLocation("JORGE ALEJANDRO NEWBERY"));
//    locations.add(new PlaceLocation("JUAN BAUTISTA AMBROSETTI"));
//    locations.add(new PlaceLocation("MANUEL G. ARGERICH"));
//    locations.add(new PlaceLocation("ALEJANDRO FRANK"));
//    locations.add(new PlaceLocation("SENTIMIENTO"));
//    locations.add(new PlaceLocation("CAFE PALACIO"));
//    locations.add(new PlaceLocation("MUSEO FOTOGRAFICO SIMIK"));
//    locations.add(new PlaceLocation("MUSEO ANCONETANI DEL ACORDEON"));
//    locations.add(new PlaceLocation("ESPACIO CULTURAL CARLOS GARDEL"));
//    locations.add(new PlaceLocation("ESPUCIO CULTURAL CARLOS GARDEL"));
    locations = new PlacesReader().getPlaces();
    return new ArrayList<>(PlaceSearchEngine.getSimilarity(locations, searchTerm.toUpperCase()));
  }
}
