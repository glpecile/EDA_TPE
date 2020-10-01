package reader;

import model.BusRoute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BusRouteReader {
    private final String filePath = "src/main/resources/paradas-de-colectivo.csv";
    private final List<String[]> busRouteData;
    private final List<BusRoute> busRoutes = new ArrayList<>();

    public BusRouteReader() throws IOException {
        ReadResources  resources = new ReadResources(filePath);
        busRouteData = resources.gettingFileReady();
    }
}
