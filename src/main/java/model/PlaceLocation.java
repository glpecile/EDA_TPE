package model;

public class PlaceLocation implements Comparable<PlaceLocation>{

  private double lat;
  private double lng;
  private String name;

  public PlaceLocation(String name) {
    this.name = name;
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
    if(cmp == 0) {
      cmp = Double.compare(this.getLat(), otherPlace.getLat());
      if(cmp == 0) {
        cmp = Double.compare(this.getLng(), otherPlace.getLng());
      }
    }
    return cmp;
  }
}
