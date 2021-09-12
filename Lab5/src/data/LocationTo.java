package data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * LocationTo in coordinates X, Y, Z and with name
 */
@XmlRootElement(name = "locationTo")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"name", "x", "y", "z"})
public class LocationTo implements Serializable {
    private Double x; //not null
    private float y;
    private Float z; //not null
    private String name; //not empty, not null

    public LocationTo() {
        super();
    }

    /**
     * Constructs a new LocationFrom object
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @param z Z-Coordinate
     * @param name Location name
     */
    public LocationTo(double x, float y, Float z, String name) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    /**
     * Return a value of X-Coordinate
     * @return X-Coordinate
     */
    @XmlAttribute
    public Double getX() {
        return x;
    }

    /**
     * Set a new X-Coordinate
     * @param x X-Coordinate
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * Return a value of Y-Coordinate
     * @return Y-Coordinates
     */
    @XmlAttribute
    public float getY() {
        return y;
    }

    /**
     * Set a new Y-Coordinate
     * @param y Y-Coordinate
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Return a value of Z-Coordinate
     * @return Z-Coordinates
     */
    @XmlAttribute
    public Float getZ() {
        return z;
    }

    /**
     * Set a new Z-Coordinate
     * @param z Z-Coordinate
     */
    public void setZ(Float z) {
        this.z = z;
    }

    /**
     * Return a location name
     * @return Location name
     */
    @XmlElement
    public String getName(){
        return name;
    }

    /**
     * Set a new location name
     * @param name Location name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a String object representing this LocationTo value
     * @return a String object representing this LocationFrom value
     */
    @Override
    public String toString() {
        return "LocationTo{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Compares this object to the specified object
     * @param obj an object
     * @return true if the arguments are equal to each other and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinates) {
            LocationTo locationToObj = (LocationTo) obj;
            return (x.equals(locationToObj.getX()))
                    && (y == locationToObj.getY())
                    && (z.equals(locationToObj.getZ())
                    && (name.equals(locationToObj.getName())));
        }
        return false;
    }

    /**
     * Returns a hash code for this Object
     * @return a hash code for this Object
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, name);
    }
}
