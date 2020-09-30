package reader;


import java.io.IOException;
import java.util.List;

public class BusStopReader {
    private final String filePath = "/resources/espacios-culturales.csv";
    private final List<String[]> busStopData;
    public BusStopReader() throws IOException {
        ReadResources  resources = new ReadResources(filePath);
        busStopData = resources.gettingFileReady();
    }



}
