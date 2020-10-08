package placefinder;

import model.PlaceLocation;
import model.RankedPlaceLocation;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Motor de búsqueda de palabras y coincidencias.
 */
public class PlaceSearchEngine {

    /**
     * Busca las palabras similares que existen en la base de datos.
     *
     * @param placeLocations, base de datos que contiene lugares culturales.
     * @param place,          palabra que se quiere buscar.
     * @return List con hasta 10 mejores coincidencias de place en la base de datos.
     */
    public static List<PlaceLocation> getSimilarity(Iterable<PlaceLocation> placeLocations, String place) {
        TreeSet<RankedPlaceLocation> rankedPlaceLocationSet = new TreeSet<>();

        QGram qGram = new QGram(3);

        for (PlaceLocation placeLocation : placeLocations) {
            double similarity = qGram.similarity(place, placeLocation.getName());
            if (similarity > 0) {
                if (rankedPlaceLocationSet.size() < 10) {
                    rankedPlaceLocationSet.add(new RankedPlaceLocation(placeLocation, similarity));
                } else if (Double.compare(similarity, rankedPlaceLocationSet.last().getqGramRanking()) > 0) {
                    rankedPlaceLocationSet.remove(rankedPlaceLocationSet.last());
                    rankedPlaceLocationSet.add(new RankedPlaceLocation(placeLocation, similarity));
                }
            }
        }

        return rankedPlaceLocationSet.stream().map(RankedPlaceLocation::getPlaceLocation).collect(Collectors.toList());
    }
}
