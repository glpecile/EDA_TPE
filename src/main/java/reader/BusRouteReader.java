package reader;

import model.BusRoute;
import model.Coord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase para lectura de csv de recorridos de colectivos. Se hace composición con ReadResources.
 */
public class BusRouteReader {
    @SuppressWarnings("all")
    private final String filePath = "src/main/resources/recorrido-colectivos.csv";
    private final List<String[]> busRouteData;
    private final List<BusRoute> busRoutes = new ArrayList<>();

    /**
     * Crea una nueva instancia para poder leer el csv, su path es pasado a ReadResources como
     * variable previamente definida. Los archivos son preparados para ser procesados.
     *
     * @throws IOException Si no es posible encontrar el path del archivo es
     *                     arrojada una excepción de tipo IO.
     */
    public BusRouteReader() throws IOException {
        ReadResources resources = new ReadResources(filePath);
        busRouteData = resources.gettingFileReady();
    }

    /**
     * Procesado y parseo de datos obtenidos del csv.
     *
     * @return Lista de las recorridos de colectivos parseadas y procesadas para ser usados por
     * pathfinder.
     */
    public List<BusRoute> getBusRoutes() {
        busRouteData.forEach(b -> busRoutes.add(new BusRoute(b[4], Integer.parseInt(b[1]), generateCoords(b[14]))));
        return busRoutes;
    }

    /**
     * Genera una lista de coordenadas de la ruta con orden de inserción.
     *
     * @param coords, coordenadas del recorrido a ser agregadas a la lista a retornar.
     * @return LinkedList de coordenadas del recorrido de colectivos.
     */
    private LinkedList<Coord> generateCoords(String coords) {
        coords = coords.substring(12, coords.length() - 1);
        String[] numbers = coords.split(", ");
        LinkedList<Coord> coordList = new LinkedList<>();
        for (String number : numbers) {
            String[] coord = number.split(" ");
            coordList.add(new Coord(Double.parseDouble(coord[0]), Double.parseDouble(coord[1])));
        }
        return coordList;
    }
}
