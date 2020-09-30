package reader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadResources {
    private final String filePath;
    public ReadResources(String filePath) {
        this.filePath = filePath;

    }

    public List<String[]> gettingFileReady() throws IOException {
        FileReader filereader = new FileReader(filePath);
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).withCSVParser(parser).build();
        return csvReader.readAll();
    }

}
