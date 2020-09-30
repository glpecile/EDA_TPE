package model;

public class RankedPlaceLocation implements Comparable<RankedPlaceLocation> {
    private final PlaceLocation placeLocation;
    private final double qGramRanking;

    public RankedPlaceLocation(PlaceLocation placeLocation, double qGramRanking) {
        this.placeLocation = placeLocation;
        this.qGramRanking = qGramRanking;
    }

    public PlaceLocation getPlaceLocation() {
        return placeLocation;
    }

    public double getqGramRanking() {
        return qGramRanking;
    }

    @Override
    public String toString() {
        return String.format("%s - %f", placeLocation.getName(), qGramRanking);
    }

    @Override
    public int compareTo(RankedPlaceLocation otherPlace) {
        int cmp = Double.compare(otherPlace.getqGramRanking(), this.getqGramRanking());
        if(cmp == 0) {
            cmp = this.getPlaceLocation().compareTo(otherPlace.getPlaceLocation());
        }
        return cmp;
    }
}
