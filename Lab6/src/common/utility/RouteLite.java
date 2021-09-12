package common.utility;

import common.data.Coordinates;
import common.data.LocationFrom;
import common.data.LocationTo;

import java.io.Serializable;
import java.util.Objects;

public class RouteLite implements Serializable {
    private String name;
    private Coordinates coordinates;
    private LocationFrom locationFrom;
    private LocationTo locationTo;
    private Long distance;

    public RouteLite() {
    }

    public RouteLite(String name, Coordinates coordinates, LocationFrom from, LocationTo to, Long distance) {
        super();
        this.name = name;
        this.coordinates = coordinates;
        this.locationFrom = from;
        this.locationTo = to;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocationFrom getLocationFrom() {
        return locationFrom;
    }

    public void setLocationFrom(LocationFrom locationFrom) {
        this.locationFrom = locationFrom;
    }

    public LocationTo getLocationTo() {
        return locationTo;
    }

    public void setLocationTo(LocationTo locationTo) {
        this.locationTo = locationTo;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RouteLite route = (RouteLite) obj;
        return  Objects.equals(name, route.name) &&
                coordinates.equals(route.coordinates) &&
                locationFrom.equals(locationFrom) &&
                locationTo.equals(route.locationTo) &&
                distance == route.distance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, locationFrom, locationTo, distance);
    }

    @Override
    public String toString() {
        return "ROUTE{" +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", locationFrom=" + locationFrom +
                ", locationTo=" + locationTo +
                ", distance=" + distance +
                '}';
    }
}
