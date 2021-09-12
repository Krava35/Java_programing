package data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Coordinates X, Y
 */
@XmlRootElement(name = "coordinates")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"x", "y"})
public class Coordinates implements Serializable {
    private long x;
    private Double y; // over -30, not null

    public Coordinates() {
        super();
    }

    /**
     * Constructs a new Coordinates object
     * @param x X-Coordinates
     * @param y Y-Coordinates
     */
    public Coordinates(long x, Double y) {
        super();
        this.x = x;
        this.y = y;
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
     * @param x X-Coordinates
     */
    public void setX(long x) {
        this.x = x;
    }

    /**
     * Return a value of Y-Coordinate
     * @return Y-Coordinate
     */
    @XmlAttribute
    public Double getY() {
        return y;
    }

    /**
     * Set a new Y-Coordinate
     * @param y Y-Coordinates
     */
    public void setY(Double y) {
        this.y = y;
    }

    /**
     * Returns a String object representing this Coordinates value
     * @return a String object representing this Coordinates value
     */
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
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
            Coordinates coordinatesObj = (Coordinates) obj;
            return (x == coordinatesObj.getX()) && y.equals(coordinatesObj.getY());
        }
        return false;
    }

    /**
     * Returns a hash code for this Object
     * @return a hash code for this Object
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
