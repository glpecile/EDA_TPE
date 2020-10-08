package reader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Clase para lectura de recursos. Heredada por lectores de csv.
 */
public class ReadResources {
    private final String filePath;

    /**
     * Crea una instancia para leer recursos.
     * @param filePath, nombre del archivo a buscar.
     */
    public ReadResources(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Entrega una lista a parsear con lo leído del csv.
     * @return Lista de Strings con cada linea del csv como array de Strings.
     * @throws IOException Si no es posible encontrar el path del archivo es arrojada una excepción de tipo IO.
     */
    public List<String[]> gettingFileReady() throws IOException {
        FileReader filereader = new FileReader(filePath);
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).withCSVParser(parser).build();
        return csvReader.readAll();
    }

}
