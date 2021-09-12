package data;

import XML.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Route with ID, Name, CreationDate, Coordinates, LocationFrom, LocationTo, Distance
 */
@XmlRootElement(name = "route")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "name", "creationDate","coordinates" ,"locationFrom", "locationTo", "distance"})
public class Route implements Serializable {
    private int id = 0; //over 0, unique value, automatic generation
    private String name; //not null, not empty
    private Coordinates coordinates; //not null
    private LocalDateTime creationDate; //not null, automatic generation
    private LocationFrom locationFrom; //not null
    private LocationTo locationTo; //not null
    private Long distance; //not null, over 1


    public Route() {
        super();
    }

    /**
     * Constructs a new Route object
     * @param id ID
     * @param name Route name
     * @param coordinates Coordinates
     * @param creationDate creationDate
     * @param from LocationFrom
     * @param to LocationTo
     * @param distance Route distance
     */
    public Route(int id, String name, Coordinates coordinates, LocalDateTime creationDate, LocationFrom from, LocationTo to, Long distance) {
        super();
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.locationFrom = from;
        this.locationTo = to;
        this.distance = distance;
    }

    /**
     * Return a route ID
     * @return ID
     */
    @XmlAttribute
    public int getId() {
        return id;
    }

    /**
     * Set a new route ID
     * @param id route ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return a route name
     * @return route name
     */
    @XmlAttribute
    public String getName() {
        return name;
    }

    /**
     * Set a new route name
     * @param name Route name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return a route Coordinates object
     * @return Coordinates object
     */
    @XmlElement
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Set a new route Coordinates object
     * @param coordinates Coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Return a route creation date
     * @return CreationDate
     */
    @XmlElement
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Set a new route creation date
     * @param creationDate CreationDate
     */
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

    /**
     * Return a route LocationFrom object
     * @return LocationFrom object
     */
    @XmlElement
    public LocationFrom getLocationFrom() {
        return locationFrom;
    }

    /**
     * Set a new route LocationFrom object
     * @param from LocationFrom
     */
    public void setLocationFrom(LocationFrom from) {
        this.locationFrom = from;
    }

    /**
     * Return a route LocationTo object
     * @return LocationTo object
     */
    @XmlElement
    public LocationTo getLocationTo() {
        return locationTo;
    }

    /**
     * Set a new route LocationTo object
     * @param to LocationTo
     */
    public void setLocationTo(LocationTo to) {
        this.locationTo = to;
    }

    /**
     * Return a route distance
     * @return route distance
     */
    @XmlElement
    public Long getDistance() {
        return distance;
    }

    /**
     * Set a new route distance
     * @param distance Route distance
     */
    public void setDistance(Long distance) {
        this.distance = distance;
    }

    /**
     * Compares this object to the specified object
     * @param obj an object
     * @return true if the arguments are equal to each other and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Route route = (Route) obj;
        return Objects.equals(id, route.id) && similar(route);
    }

    /**
     * Compares this object to specified object on similar
     * @param route Route
     * @return true if the arguments are similar to each other and false otherwise
     */
    public boolean similar(Route route) {
        return  name.equals(route.name) &&
                coordinates.equals(route.coordinates) &&
                creationDate.equals(route.creationDate) &&
                locationFrom.equals(route.locationFrom) &&
                locationTo.equals(route.locationTo) &&
                distance.equals(route.distance);
    }

    /**
     * Returns a hash code for this Object
     * @return a hash code for this Object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, locationFrom, locationTo, distance);
    }

    /**
     * Returns a String object representing this LocationTo value
     * @return a String object representing this LocationFrom value
     */
    @Override
    public String toString() {
        return "ROUTE{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", locationFrom=" + locationFrom +
                ", locationTo=" + locationTo +
                ", distance=" + distance +
                '}';
    }


    /**
     * Compares two Route objects by distance
     * @param o Object to compare
     * @return the value 0 if this route distance is equal;
     * a value less than 0 if this route distance is less than the argument route distance;
     * and a value greater than 0 if this route distance is greater than the argument route distance.
     */
    public int compareTo(Route o) {
        return distance.compareTo(o.distance);
    }
}
