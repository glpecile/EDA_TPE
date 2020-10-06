package model;

public class SubwayStop extends BusStop {
    public SubwayStop(Double lng, Double lat, String subwayLine) {
        super(lng, lat, 0, subwayLine);
    }
}
