package reader;


import model.BusStop;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BusStopReader {
    private final String filePath = "/resources/espacios-culturales.csv";
    private final List<String[]> busStopData;
    private Set<BusStop> busStops = new HashSet<>();
    public BusStopReader() throws IOException {
        ReadResources  resources = new ReadResources(filePath);
        busStopData = resources.gettingFileReady();
    }

    public Set<BusStop> getBusStops() {
        return busStops;
    }
}
