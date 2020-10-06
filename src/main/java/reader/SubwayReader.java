package reader;

import model.SubwayStop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubwayReader {
    private final String filePath = "src/main/resources/estaciones-de-subte.csv";
    private final List<String[]> subwayData;
    private final List<SubwayStop> subwayStops = new ArrayList<>();
    public SubwayReader() throws IOException {
        ReadResources resources = new ReadResources(filePath);
        subwayData = resources.gettingFileReady();
    }
    public List<SubwayStop> getSubwayStops() {
        subwayData.forEach(s -> subwayStops.add(new SubwayStop(Double.parseDouble(s[0]),Double.parseDouble(s[1]),s[2])));
        return subwayStops;
    }
}
