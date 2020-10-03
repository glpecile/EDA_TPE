package model;

public class BusInPath {
  private final String name;
  private final double fromLat;
  private final double fromLng;
  private final double toLat;
  private final double toLng;
  private final double cost;

  public BusInPath(String name, double fromLat, double fromLng, double toLat, double toLng, double cost) {
    this.name = name;
    this.fromLat = fromLat;
    this.fromLng = fromLng;
    this.toLat = toLat;
    this.toLng = toLng;
    this.cost = cost;
  }

  public String getName() {
    return name;
  }

  public double getFromLat() {
    return fromLat;
  }

  public double getFromLng() {
    return fromLng;
  }

  public double getToLat() {
    return toLat;
  }

  public double getToLng() {
    return toLng;
  }

  public double getCost() {
    return cost;
  }
}
