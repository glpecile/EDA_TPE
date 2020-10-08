package reader;

import model.SubwayStop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase para lectura de csv de subtes. Se hace composición con ReadResources.
 */
public class SubwayReader {
    @SuppressWarnings("all")
    private final String filePath = "src/main/resources/estaciones-de-subte.csv";
    private final List<String[]> subwayData;
    private final Map<String, List<SubwayStop>> subwayStops = new HashMap<>();

    /**
     * Crea una nueva instancia para poder leer el csv, su path es pasado a ReadResources como
     * variable previamente definida. Los archivos son preparados para ser procesados.
     *
     * @throws IOException Si no es posible encontrar el path del archivo es
     *                     arrojada una excepción de tipo IO.
     */
    public SubwayReader() throws IOException {
        ReadResources resources = new ReadResources(filePath);
        subwayData = resources.gettingFileReady();
    }

    /**
     * Procesado y parseo de datos obtenidos del csv.
     *
     * @return Mapa con llave de tipo String con el nombre de la linea y de valor las paradas de
     * subte parseadas y procesadas para ser usados por pathfinder.
     */
    public Map<String, List<SubwayStop>> getSubwayStops() {
        subwayData.forEach(s -> {
            SubwayStop subwayStop = new SubwayStop(Double.parseDouble(s[0]), Double.parseDouble(s[1]), s[4]);
            subwayStops.putIfAbsent(subwayStop.getBusName(), new ArrayList<>());
            subwayStops.get(subwayStop.getBusName()).add(subwayStop);
        });
        return subwayStops;
    }
}
