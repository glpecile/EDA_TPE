package placefinder;

import model.PlaceLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlaceSearchEngineTest {

    Set<PlaceLocation> locations = TestPlacesFactory.getPlaceLocations();

    @Test
    void FirstReturnedResultTest() {
        Assertions.assertEquals("GRAL. MANUEL BELGRANO", PlaceSearchEngine.getSimilarity(locations, "Manuel Belgrano".toUpperCase()).get(0).getName());
        Assertions.assertEquals("CASA DE GOBIERNO (CASA ROSADA)", PlaceSearchEngine.getSimilarity(locations, "Casa rosada".toUpperCase()).get(0).getName());
        Assertions.assertEquals("SERGIO SOUZA", PlaceSearchEngine.getSimilarity(locations, "Sergio Chouza".toUpperCase()).get(0).getName());
        Assertions.assertEquals("EDIFICIO KAVANAGH", PlaceSearchEngine.getSimilarity(locations, "kavanag".toUpperCase()).get(0).getName());
        Assertions.assertEquals("A.M.I.A.", PlaceSearchEngine.getSimilarity(locations, "amia".toUpperCase()).get(0).getName());
        Assertions.assertEquals("CAFE TORTORI", PlaceSearchEngine.getSimilarity(locations, "Tortoni".toUpperCase()).get(0).getName());
        Assertions.assertEquals("CABILDO DE LA CIUDAD DE BUENOS AIRES", PlaceSearchEngine.getSimilarity(locations, "cabildo".toUpperCase()).get(0).getName());
        Assertions.assertEquals("HOSPITAL DEL REY", PlaceSearchEngine.getSimilarity(locations, "hospital".toUpperCase()).get(0).getName());
        Assertions.assertEquals("CINEMARK CABALLITO", PlaceSearchEngine.getSimilarity(locations, "cinemakr".toUpperCase()).get(0).getName());
        Assertions.assertEquals("BIBLIOTECA MUSEO SIMIK", PlaceSearchEngine.getSimilarity(locations, "biblioteca".toUpperCase()).get(0).getName());
    }

    @Test
    void AllReturnedResultsTest() {
        List<PlaceLocation> placeLocations = PlaceSearchEngine.getSimilarity(locations, "casa".toUpperCase());
        Assertions.assertEquals("CASA DE LA MONEDA", placeLocations.get(0).getName());
        Assertions.assertEquals("CASA DE GOBIERNO (CASA ROSADA)", placeLocations.get(1).getName());
        Assertions.assertEquals("CAFE TORTORI", placeLocations.get(2).getName());
        Assertions.assertEquals("SERGIO SOUZA", placeLocations.get(3).getName());
        Assertions.assertEquals("CABILDO DE LA CIUDAD DE BUENOS AIRES", placeLocations.get(4).getName());
        Assertions.assertEquals("CINEMARK CABALLITO", placeLocations.get(5).getName());
        Assertions.assertEquals("LA INTERNACIONAL ARGENTINA", placeLocations.get(6).getName());
        Assertions.assertEquals("CENTRO CULTURAL ALFONSINA STORNI", placeLocations.get(7).getName());
    }

}
