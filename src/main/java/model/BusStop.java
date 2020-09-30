package model;

import java.util.Objects;

public class BusStop {
    private final Double Lng;
    private final Double Lat;
    private final Integer directionId;
    private final String busName;

    public BusStop(Double lng, Double lat, Integer directionId, String busName) {
        Lng = lng;
        Lat = lat;
        this.directionId = directionId;
        this.busName = busName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusStop busStop = (BusStop) o;
        return Lng.equals(busStop.Lng) &&
               Lat.equals(busStop.Lat) &&
                (directionId== busStop.directionId)&&
                busName.equals(busStop.busName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Lng, Lat, directionId, busName);
    }
}
