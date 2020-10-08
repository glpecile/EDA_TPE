package model;

import java.util.Objects;

/**
 * Clase para simplificar el pasaje de coordenadas, contiene latitud, longitud y sus métodos para
 * el cálculo de las distancias o proximidad.
 */
public class Coord {
    public static final double IS_CLOSER = 0.005;
    private static final double ROUND = 1000000; // Tantos ceros como decimales.
    private final double lng;
    private final double lat;

    /**
     * Crea una instancia de una coordenada.
     *
     * @param lng, longitud de la coordenada dada.
     * @param lat, latitud de la coordenada dada.
     */
    public Coord(double lng, double lat) {
        this.lng = Math.round(lng * ROUND) / ROUND;
        this.lat = Math.round(lat * ROUND) / ROUND;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    /**
     * Calcula la distancia entre dos puntos, usando la instancia y uno recibido como parametro.
     *
     * @param other, coordenada recibida por parametro.
     * @return retorna la distancia entre los dos puntos.
     */
    public double distanceTo(Coord other) {
        return Math.sqrt(Math.pow((this.lat - other.lat), 2) + Math.pow((this.lng - other.lng), 2));
    }

    /**
     * Verifica que, usando distanceTo junto con una variable de clase IS_CLOSER, se encuentra
     * cerca de
     * la coordenada.
     *
     * @param other, coordenada a comparar su distancia.
     * @return retorna verdadero o falso si se encuentra en el rando cercano o no.
     */
    public boolean isCloser(Coord other) {
        return (this.distanceTo(other) < IS_CLOSER);
    }

    @Override
    public String toString() {
        return String.format("Lng: %g | Lat: %g", lng, lat);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return Double.compare(coord.lat, lat) == 0 &&
                Double.compare(coord.lng, lng) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }
}
