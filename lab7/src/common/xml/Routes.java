package common.xml;


import common.data.Route;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Root object of XML file. Contains collection of Routes objects
 */
@XmlRootElement(name = "routes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Routes {

    @XmlElement(name = "data")
    private ArrayList<Route> routes = null;

    public Routes() {}

    /**
     * @return collection of Routes objects
     */
    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }
}
