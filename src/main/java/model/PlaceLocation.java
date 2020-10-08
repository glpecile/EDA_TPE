package model;

/**
 * Clase de localización de espacio cultural, es comparable para poder ser ordenada por nombre,
 * luego por coordenadas si posee el mismo nombre.
 */
public class PlaceLocation implements Comparable<PlaceLocation> {

    private final double lat;
    private final double lng;
    private final String name;

    /**
     * Crea una instancia de un lugar, usada en su respectivo reader.
     *
     * @param name, nombre del lugar cultural.
     * @param lat,  latitud donde se encuentra el lugar.
     * @param lng,  longitud donde se encuentra el lugar.
     */
    public PlaceLocation(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(PlaceLocation otherPlace) {
        int cmp = this.getName().compareTo(otherPlace.getName());
        if (cmp == 0) {
            cmp = Double.compare(this.getLat(), otherPlace.getLat());
            if (cmp == 0) {
                cmp = Double.compare(this.getLng(), otherPlace.getLng());
            }
        }
        return cmp;
    }
}
