package model;

/**
 * Clase usada para preservar el orden de los espacios culturales y su ranking en el algoritmo de
 * precisión de búsqueda. Es comparable para preservar el orden al ser insertados en una
 * estructura, estos se comparan primero por su ranking y luego según el criterio de la clase
 * para espacios culturales.
 */
public class RankedPlaceLocation implements Comparable<RankedPlaceLocation> {
    private final PlaceLocation placeLocation;
    private final double qGramRanking;

    /**
     * Crea una instancia de un espacio cultural a ser utilizado en un ranking.
     *
     * @param placeLocation, espacio cultural a ser agregado al ranking.
     * @param qGramRanking,  ranking obtenido del algoritmo de qGram.
     */
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
        if (cmp == 0) {
            cmp = this.getPlaceLocation().compareTo(otherPlace.getPlaceLocation());
        }
        return cmp;
    }
}
