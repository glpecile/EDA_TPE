package reader;

import model.PlaceLocation;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class PlacesReader {
    @SuppressWarnings("all")
    private final String filePath = "src/main/resources/espacios-culturales.csv";
    private final List<String[]> placesData;
    Set<PlaceLocation> placesSet = new HashSet<>();

    /**
     *
     * @throws IOException
     */
    public PlacesReader() throws IOException {
        ReadResources resources = new ReadResources(filePath);
        placesData = resources.gettingFileReady();
    }

    /**
     *
     * @return
     */
    public Set<PlaceLocation> getPlaces() {
        placesData.forEach(p -> placesSet.add(new PlaceLocation(p[3], Double.parseDouble(p[13]), Double.parseDouble(p[14]))));
        return placesSet;
    }

}
