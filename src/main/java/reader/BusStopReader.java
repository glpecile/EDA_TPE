package reader;


import model.BusStop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BusStopReader {
    private final String filePath = "src/main/resources/paradas-de-colectivo.csv";
    private final List<String[]> busStopData;
    private final List<BusStop> busStops = new ArrayList<>();

    public BusStopReader() throws IOException {
        ReadResources  resources = new ReadResources(filePath);
        busStopData = resources.gettingFileReady();
    }

    public List<BusStop> getBusStops() {
        busStopData.forEach(b -> busStops.add(new BusStop(Double.parseDouble(b[4]), Double.parseDouble(b[3]), Integer.parseInt(b[5]), b[8])));
        return busStops;
    }
}
