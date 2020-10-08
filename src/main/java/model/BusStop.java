package model;

import java.util.Objects;

public class BusStop {
    private final Coord coord;
    private final Integer directionId;
    private final String busName;
    private static final double TRANSFER_PENALTY = 0.5;

    public BusStop(Double lng, Double lat, Integer directionId, String busName) {
        coord = new Coord(lng, lat);
        this.directionId = directionId;
        this.busName = busName;
    }

    public Coord getCoord() {
        return coord;
    }

    public Integer getDirectionId() {
        return directionId;
    }

    public String getBusName() {
        return busName;
    }

    public double getTransferPenalty() {
        return TRANSFER_PENALTY;
    }

    public double distanceTo(BusStop other) {
        return this.coord.distanceTo(other.getCoord());
    }

    public boolean isCloser(BusStop other) {
        return this.coord.isCloser(other.getCoord());
    }

    @Override
    public String toString() {
        return busName + "||" + coord.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusStop busStop = (BusStop) o;
        return coord.equals(busStop.coord) &&
                (directionId.equals(busStop.directionId))&&
                busName.equals(busStop.busName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coord, directionId, busName);
    }
}
