package reader;

import model.SubwayStop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubwayReader {
    @SuppressWarnings("all")
    private final String filePath = "src/main/resources/estaciones-de-subte.csv";
    private final List<String[]> subwayData;
    private final Map<String, List<SubwayStop>> subwayStops = new HashMap<>();

    public SubwayReader() throws IOException {
        ReadResources resources = new ReadResources(filePath);
        subwayData = resources.gettingFileReady();
    }

    public Map<String, List<SubwayStop>> getSubwayStops() {
        subwayData.forEach(s -> {
            SubwayStop subwayStop = new SubwayStop(Double.parseDouble(s[0]), Double.parseDouble(s[1]), s[4]);
            subwayStops.putIfAbsent(subwayStop.getBusName(), new ArrayList<>());
            subwayStops.get(subwayStop.getBusName()).add(subwayStop);
        });
        return subwayStops;
    }
}
