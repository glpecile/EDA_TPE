package placefinder;

import model.PlaceLocation;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PlaceSearchEngine {

    public static List<PlaceLocation> getSimilarity(Iterable<PlaceLocation> placeLocations, String place) {
        TreeSet<RankedPlaceLocation> rankedPlaceLocationSet = new TreeSet<>();

        QGram qGram = new QGram(3);

        for(PlaceLocation placeLocation : placeLocations) {
            double similarity = qGram.similarity(place, placeLocation.getName());
            if(similarity > 0) {
                if(rankedPlaceLocationSet.size() < 10) {
                    rankedPlaceLocationSet.add(new RankedPlaceLocation(placeLocation, similarity));
                } else if(Double.compare(similarity, rankedPlaceLocationSet.last().getqGramRanking()) > 0) {
                    rankedPlaceLocationSet.remove(rankedPlaceLocationSet.last());
                    rankedPlaceLocationSet.add(new RankedPlaceLocation(placeLocation, similarity));
                }
            }
        }

        return rankedPlaceLocationSet.stream().map(RankedPlaceLocation::getPlaceLocation).collect(Collectors.toList());
    }
}
