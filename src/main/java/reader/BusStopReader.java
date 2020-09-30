package reader;


import model.BusStop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BusStopReader {
    private final String filePath = "src/main/resources/paradas-de-colectivos.csv";
    private final List<String[]> busStopData;
    private final List<BusStop> busStops = new ArrayList<>();
    public BusStopReader() throws IOException {
        ReadResources  resources = new ReadResources(filePath);
        busStopData = resources.gettingFileReady();
    }
    private void getBusStopData(){
        for (String[] row:
             busStopData) {
            busStops.add(new BusStop(row[4],row[3],row[5],row[8]));
        }
    }
    public List<BusStop> getBusStops() {
        return busStops;
    }
}
