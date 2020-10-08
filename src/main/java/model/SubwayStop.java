package model;

/**
 * Clase usada para paradas de subtes, extiende la clase de paradas de colectivos ya que poseen
 * comportamiento en común.
 */
public class SubwayStop extends BusStop {
    private static final double TRANSFER_PENALTY = 0.125;

    /**
     * Crea una instancia de una parada de subte, usada en su respectivo reader.
     *
     * @param lng,        longitud donde se encuentra la parada.
     * @param lat,        latitud donde se encuentra la parada.
     * @param subwayLine, línea de subte.
     */
    public SubwayStop(Double lng, Double lat, String subwayLine) {
        super(lng, lat, 0, subwayLine);
    }

    @Override
    public double getTransferPenalty() {
        return TRANSFER_PENALTY;
    }
}
