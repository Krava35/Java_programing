package util;

import java.time.LocalDateTime;
import java.util.*;
import data.Route;
/**
 * Collection management
 */
public class CollectionManager {
    private ArrayList<Route> myCollection;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;

    {
        myCollection = new ArrayList<>();
    }

    public CollectionManager(FileManager fileManager){
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;
        loadCollection();
    }

    /**
     * Returns the collection itself.
     * @return The collection itself.
     */
    public ArrayList<Route> getCollection() {
        return myCollection;
    }

    /**
     * Returns last initialization time.
     * @return Last initialization time or null if there wasn't initialization.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * Returns last save time in session.
     * @return last save time or null if there wasn't saving.
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * Returns the collection's type.
     * @return The collection's type.
     */
    public String collectionType() {
        return myCollection.getClass().getName();
    }

    /**
     * Returns size of the collection.
     * @return Size of the collection.
     */
    public int collectionSize() {
        return myCollection.size();
    }

    /**
     * Removes routes lesser than the selected one.
     * @param route A route to compare with.
     */
    public void removeLower(Route route) {
        myCollection.removeIf(r -> r.compareTo(route) < 0);
    }

    /**
     * Returns a route by selected ID
     * @param id ID of the route.
     * @return Route by his ID or null if route isn't found.
     */
    public Route getById(Integer id) {
        for (Route route : myCollection) {
            if (route.getId() == id) return route;
        }
        return null;
    }

    /**
     * Returns a route by selected distance
     * @param distance Distance of the route
     * @return Route by his distance ot null if isn't found
     */
    public Route getByDistance(long distance) {
        for (Route route : myCollection) {
            if (route.getDistance().equals(distance)) return route;
        }
        return null;
    }

    /**
     * Adds a new route to collection.
     * @param route A route to add.
     */
    public void addToCollection(Route route) {
        myCollection.add(route);
    }

    /**
     * Removes the route from collection.
     * @param route A route to remove.
     */
    public void removeFromCollection(Route route) {
        myCollection.remove(route);
    }

    /**
     * Clears the collection.
     */
    public void clearCollection() {
        myCollection.clear();
    }

    /**
     * Generates next ID. It will be (the bigger ID in collection + 1).
     * @return Next ID.
     */
    public Integer generateNextId() {
        if (myCollection.isEmpty()) return 1;
        Integer lastId = 0;
        Iterator<Route> iterator = myCollection.stream().iterator();
        while (iterator.hasNext()){
            Integer compareId = iterator.next().getId();
            if( compareId > lastId){
                lastId = compareId;
            }
        }
        return lastId + 1;
    }

    /**
     * Saves the collection to file.
     */
    public void saveCollection() {
        fileManager.writeCollection(myCollection);
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * Loads the collection from file.
     */
    private void loadCollection() {
        myCollection = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    /**
     * Sorts the collection by distance.
     */
    public void sortedByDistance(){
        Collections.sort(myCollection, Route::compareTo);
    }

    /**
     * Removes all route from collection with selected distance.
     * @param distance Distance of the route.
     */
    public void removeByDistance(Long distance){
        myCollection.removeIf(route -> route.getDistance() == distance);
    }

    /**
     * Returns routes that are less then selected distance.
     * @param distance Distance of the route
     * @return Routes that are less then selected distance.
     */
    public ArrayList<Route> getLessThanDistance(Long distance){
        ArrayList<Route> routes = new ArrayList<>();
        Iterator<Route> iterator = myCollection.stream().filter(route -> route.getDistance() < distance).iterator();
        iterator.forEachRemaining(route -> routes.add(route));
        return routes;
    }

    /**
     * Return a max route by distance.
     * @return A max route by distance.
     */
    public Route getMaxByDistance(){
       return myCollection.stream().max(Route::compareTo).get();
    }

    /**
     * Counts groups od route by distance.
     * @return Quantity of grouped routes by distance.
     */
    public HashMap<Long, Long> groupCountingByDistance(){
        HashMap<Long, Long> routeMap = new HashMap<>();
        for (Route route : myCollection) {
            if (!(routeMap.containsKey(route.getDistance()))){
                routeMap.put(route.getDistance(), 1L);
            } else {
                routeMap.put(route.getDistance(), routeMap.get(route.getDistance()) + 1L);
            }
        }
        return routeMap;
    }


    /**
     * Returns a String object representing this collection value
     * @return a String object representing this collection value
     */
    @Override
    public String toString() {
        if (myCollection.isEmpty()) return "The collection is empty!";

        StringBuilder info = new StringBuilder();
        for (Route route : myCollection) {
            info.append(route);
            info.append("\n\n");
        }
        return info.toString();
    }
}

