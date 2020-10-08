package reader;

import model.PlaceLocation;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Clase para lectura de csv de espacios culturales. Se hace composición con ReadResources.
 */
public class PlacesReader {
    @SuppressWarnings("all")
    private final String filePath = "src/main/resources/espacios-culturales.csv";
    private final List<String[]> placesData;
    Set<PlaceLocation> placesSet = new HashSet<>();

    /**
     * Crea una nueva instancia para poder leer el csv, su path es pasado a ReadResources como
     * variable previamente definida. Los archivos son preparados para ser procesados.
     *
     * @throws IOException Si no es posible encontrar el path del archivo es
     *                     arrojada una excepción de tipo IO.
     */
    public PlacesReader() throws IOException {
        ReadResources resources = new ReadResources(filePath);
        placesData = resources.gettingFileReady();
    }

    /**
     * Procesado y parseo de datos obtenidos del csv.
     *
     * @return Set de lugares parseados y procesados para ser usados por placefinder.
     */
    public Set<PlaceLocation> getPlaces() {
        placesData.forEach(p -> placesSet.add(new PlaceLocation(p[3], Double.parseDouble(p[13]), Double.parseDouble(p[14]))));
        return placesSet;
    }

}
