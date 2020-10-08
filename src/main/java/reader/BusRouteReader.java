package reader;

import model.BusRoute;
import model.Coord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BusRouteReader {
    @SuppressWarnings("all")
    private final String filePath = "src/main/resources/recorrido-colectivos.csv";
    private final List<String[]> busRouteData;
    private final List<BusRoute> busRoutes = new ArrayList<>();

    /**
     *
     * @throws IOException
     */
    public BusRouteReader() throws IOException {
        ReadResources  resources = new ReadResources(filePath);
        busRouteData = resources.gettingFileReady();
    }

    /**
     *
     * @return
     */
    public List<BusRoute> getBusRoutes() {
        busRouteData.forEach(b -> busRoutes.add(new BusRoute(b[4], Integer.parseInt(b[1]), generateCoords(b[14]))));
        System.out.println("Tamaño busRoutes: " + busRoutes.size());
        return busRoutes;
    }

    /**
     *
     * @param coords
     * @return
     */
    private LinkedList<Coord> generateCoords(String coords) {
        coords = coords.substring(12, coords.length() - 1);
        String[] numbers = coords.split(", ");
        LinkedList<Coord> coordList = new LinkedList<>();
        for(String number : numbers) {
            String[] coord = number.split(" ");
            coordList.add(new Coord(Double.parseDouble(coord[0]), Double.parseDouble(coord[1])));
        }
        return coordList;
    }
}
