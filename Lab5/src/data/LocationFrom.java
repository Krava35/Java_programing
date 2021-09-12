package data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * LocationFrom in coordinates X, Y, Z
 */
@XmlRootElement(name = "locationFrom")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"x", "y", "z"})
public class LocationFrom implements Serializable {
    private long x;
    private long y;
    private Float z; //not null

    public LocationFrom() {
        super();
    }

    /**
     * Constructs a new LocationFrom object
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @param z Z-Coordinate
     */
    public LocationFrom(long x, long y, Float z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Return a value of X-Coordinate
     * @return X-Coordinate
     */
    @XmlAttribute
    public long getX() {
        return x;
    }

    /**
     * Set a new X-Coordinate
     * @param x X-Coordinate
     */
    public void setX(long x) {
        this.x = x;
    }

    /**
     * Return a value of Y-Coordinate
     * @return Y-Coordinate
     */
    @XmlAttribute
    public long getY() {
        return y;
    }

    /**
     * Set a new Y-Coordinate
     * @param y Y-Coordinate
     */
    public void setY(long y) {
        this.y = y;
    }

    /**
     * Return a value of Z-Coordinate
     * @return Z-Coordinate
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
     * Returns a String object representing this LocationFrom value
     * @return a String object representing this LocationFrom value
     */
    @Override
    public String toString() {
        return "LocationFrom{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
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
            LocationFrom locationFromObj = (LocationFrom) obj;
            return (x == locationFromObj.getX()) && (y == locationFromObj.getY()) && (z.equals(locationFromObj.getZ()));
        }
        return false;
    }

    /**
     * Returns a hash code for this Object
     * @return a hash code for this Object
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

}
