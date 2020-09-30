package placefinder;

import model.PlaceLocation;

import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Set<PlaceLocation> locations = new TreeSet<>();
        locations.add(new PlaceLocation("8 ESQUINAS"));
        locations.add(new PlaceLocation("ESCUELA METROPOLITANA DE ARTE DRAMATICA"));
        locations.add(new PlaceLocation("GIER MUSIC"));
        locations.add(new PlaceLocation("BOVEDA QUE GUARDA LOS RESTOS DE CARLOS GARDEL"));
        locations.add(new PlaceLocation("ARCHIVO HISTORICO DEL CREMATORIO MUNICIPAL"));
        locations.add(new PlaceLocation("CREMATORIO MUNICIPAL"));
        locations.add(new PlaceLocation("JORGE ALEJANDRO NEWBERY"));
        locations.add(new PlaceLocation("JUAN BAUTISTA AMBROSETTI"));
        locations.add(new PlaceLocation("MANUEL G. ARGERICH"));
        locations.add(new PlaceLocation("ALEJANDRO FRANK"));
        locations.add(new PlaceLocation("SENTIMIENTO"));
        locations.add(new PlaceLocation("CAFE PALACIO"));
        locations.add(new PlaceLocation("MUSEO FOTOGRAFICO SIMIK"));
        locations.add(new PlaceLocation("MUSEO ANCONETANI DEL ACORDEON"));
        locations.add(new PlaceLocation("ESPACIO CULTURAL CARLOS GARDEL"));

        Set<RankedPlaceLocation> ranking = PlaceSearchEngine.getSimilarity(locations, "MUSEO");
        ranking.forEach(System.out::println);
        System.out.println("--------------------");
        Set<RankedPlaceLocation> ranking2 = PlaceSearchEngine.getSimilarity(locations, "CARLOS GARDEL");
        ranking2.forEach(System.out::println);


    }
}
