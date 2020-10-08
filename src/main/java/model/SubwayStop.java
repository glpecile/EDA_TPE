package model;

public class SubwayStop extends BusStop {
    private static final double TRANSFER_PENALTY = 0.125;

    public SubwayStop(Double lng, Double lat, String subwayLine) {
        super(lng, lat, 0, subwayLine);
    }

    @Override
    public double getTransferPenalty() {
        return TRANSFER_PENALTY;
    }
}
