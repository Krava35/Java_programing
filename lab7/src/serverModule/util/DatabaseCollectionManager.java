package serverModule.util;

import common.data.Coordinates;
import common.data.LocationFrom;
import common.data.LocationTo;
import common.data.Route;
import common.exceptions.DatabaseManagerException;
import common.utility.RouteLite;
import common.utility.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.concurrent.CopyOnWriteArrayList;

public class DatabaseCollectionManager {
    private final String SELECT_ALL_ROUTES = "SELECT * FROM " + DatabaseManager.ROUTE_TABLE;
    private final String SELECT_ROUTE_BY_ID = SELECT_ALL_ROUTES + " WHERE " +
            DatabaseManager.ROUTE_TABLE_ID_COLUMN + " = ?";
    private final String SELECT_ROUTE_BY_ID_AND_USER_ID = SELECT_ROUTE_BY_ID + " AND " +
            DatabaseManager.ROUTE_TABLE_USER_ID_COLUMN + " = ?";
    private final String INSERT_ROUTE = "INSERT INTO " +
            DatabaseManager.ROUTE_TABLE + " (" +
            DatabaseManager.ROUTE_TABLE_NAME_COLUMN + ", " +
            DatabaseManager.ROUTE_TABLE_COORDINATES_ID_COLUMN + ", " +
            DatabaseManager.ROUTE_TABLE_CREATION_DATE_COLUMN + ", " +
            DatabaseManager.ROUTE_TABLE_LOCATION_FROM_ID_COLUMN + ", " +
            DatabaseManager.ROUTE_TABLE_LOCATION_TO_ID_COLUMN + ", " +
            DatabaseManager.ROUTE_TABLE_DISTANCE_COLUMN + ", " +
            DatabaseManager.ROUTE_TABLE_USER_ID_COLUMN + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String DELETE_ROUTE_BY_ID = "DELETE FROM " + DatabaseManager.ROUTE_TABLE +
            " WHERE " + DatabaseManager.ROUTE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_ROUTE_NAME_BY_ID = "UPDATE " + DatabaseManager.ROUTE_TABLE + " SET " +
            DatabaseManager.ROUTE_TABLE_NAME_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.ROUTE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_ROUTE_DISTANCE_BY_ID = "UPDATE " + DatabaseManager.ROUTE_TABLE + " SET " +
            DatabaseManager.ROUTE_TABLE_DISTANCE_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.ROUTE_TABLE_ID_COLUMN + " = ?";

    private final String SELECT_ALL_COORDINATES = "SELECT * FROM " + DatabaseManager.COORDINATES_TABLE;
    private final String SELECT_COORDINATES_BY_ID = SELECT_ALL_COORDINATES + " WHERE " + DatabaseManager.COORDINATES_TABLE_ID_COLUMN + " = ?";
    private final String DELETE_COORDINATES_BY_ID = "DELETE FROM " + DatabaseManager.COORDINATES_TABLE +
            " WHERE " + DatabaseManager.COORDINATES_TABLE_ID_COLUMN + " = ?";
    private final String INSERT_COORDINATES = "INSERT INTO " +
            DatabaseManager.COORDINATES_TABLE + " (" +
            DatabaseManager.COORDINATES_TABLE_X_COLUMN + ", " +
            DatabaseManager.COORDINATES_TABLE_Y_COLUMN + ") VALUES (?, ?)";
    private final String UPDATE_COORDINATES_BY_ID = "UPDATE " + DatabaseManager.COORDINATES_TABLE + " SET " +
            DatabaseManager.COORDINATES_TABLE_X_COLUMN + " = ?, " +
            DatabaseManager.COORDINATES_TABLE_Y_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.COORDINATES_TABLE_ID_COLUMN + " = ?";

    private final String SELECT_ALL_LOCATIONS_FROM = "SELECT * FROM " + DatabaseManager.LOCATION_FROM_TABLE;
    private final String SELECT_LOCATION_FROM_BY_ID = SELECT_ALL_LOCATIONS_FROM + " WHERE " + DatabaseManager.LOCATION_FROM_TABLE_ID_COLUMN + " = ?";
    private final String DELETE_LOCATION_FROM_BY_ID = "DELETE FROM " + DatabaseManager.LOCATION_FROM_TABLE +
            " WHERE " + DatabaseManager.LOCATION_FROM_TABLE_ID_COLUMN + " = ?";
    private final String INSERT_LOCATION_FROM = "INSERT INTO " +
            DatabaseManager.LOCATION_FROM_TABLE + " (" +
            DatabaseManager.LOCATION_FROM_TABLE_X_COLUMN + ", " +
            DatabaseManager.LOCATION_FROM_TABLE_Y_COLUMN + ", " +
            DatabaseManager.LOCATION_FROM_TABLE_Z_COLUMN + ") VALUES (?, ?, ?)";
    private final String UPDATE_LOCATION_FROM_BY_ID = "UPDATE " + DatabaseManager.LOCATION_FROM_TABLE + " SET " +
            DatabaseManager.LOCATION_FROM_TABLE_X_COLUMN + " = ?, " +
            DatabaseManager.LOCATION_FROM_TABLE_Y_COLUMN + " = ?, " +
            DatabaseManager.LOCATION_FROM_TABLE_Z_COLUMN + " = ?, " + " WHERE " +
            DatabaseManager.LOCATION_FROM_TABLE_ID_COLUMN + " = ?";

    private final String SELECT_ALL_LOCATIONS_TO = "SELECT * FROM " + DatabaseManager.LOCATION_TO_TABLE;
    private final String SELECT_LOCATION_TO_BY_ID = SELECT_ALL_LOCATIONS_TO + " WHERE " + DatabaseManager.LOCATION_TO_TABLE_ID_COLUMN + " = ?";
    private final String DELETE_LOCATION_TO_BY_ID = "DELETE FROM " + DatabaseManager.LOCATION_TO_TABLE +
            " WHERE " + DatabaseManager.LOCATION_TO_TABLE_ID_COLUMN + " = ?";
    private final String INSERT_LOCATION_TO = "INSERT INTO " +
            DatabaseManager.LOCATION_TO_TABLE + " (" +
            DatabaseManager.LOCATION_TO_TABLE_X_COLUMN + ", " +
            DatabaseManager.LOCATION_TO_TABLE_Y_COLUMN + ", " +
            DatabaseManager.LOCATION_TO_TABLE_Z_COLUMN + ", " +
            DatabaseManager.LOCATION_TO_TABLE_NAME_COLUMN + ") VALUES (?, ?, ?, ?)";
    private final String UPDATE_LOCATION_TO_BY_ID = "UPDATE " + DatabaseManager.LOCATION_TO_TABLE + " SET " +
            DatabaseManager.LOCATION_TO_TABLE_X_COLUMN + " = ?, " +
            DatabaseManager.LOCATION_TO_TABLE_Y_COLUMN + " = ?, " +
            DatabaseManager.LOCATION_TO_TABLE_Z_COLUMN + " = ?, " +
            DatabaseManager.LOCATION_TO_TABLE_NAME_COLUMN + " = ?" + " WHERE " +
            DatabaseManager.LOCATION_TO_TABLE_ID_COLUMN + " = ?";

    private DatabaseManager databaseManager;
    private DatabaseUserManager databaseUserManager;

    public DatabaseCollectionManager(DatabaseManager databaseManager, DatabaseUserManager databaseUserManager) {
        this.databaseManager = databaseManager;
        this.databaseUserManager = databaseUserManager;
    }

    private Route returnRoute(ResultSet resultSet, int id) throws SQLException {
        String name = resultSet.getString(DatabaseManager.ROUTE_TABLE_NAME_COLUMN);
        Coordinates coordinates = getCoordinatesByID(resultSet.getInt(DatabaseManager.ROUTE_TABLE_COORDINATES_ID_COLUMN));
        LocalDateTime creationDate = resultSet.getTimestamp(DatabaseManager.ROUTE_TABLE_CREATION_DATE_COLUMN).toLocalDateTime();
        LocationFrom locationFrom = getLocationFromByID(resultSet.getInt(DatabaseManager.ROUTE_TABLE_LOCATION_FROM_ID_COLUMN));
        LocationTo locationTo = getLocationToToByID(resultSet.getInt(DatabaseManager.ROUTE_TABLE_LOCATION_TO_ID_COLUMN));
        Long distance = resultSet.getLong(DatabaseManager.ROUTE_TABLE_DISTANCE_COLUMN);
        User owner = databaseUserManager.getUserById(resultSet.getInt(DatabaseManager.ROUTE_TABLE_USER_ID_COLUMN));
        return new Route(id, name, coordinates, creationDate, locationFrom, locationTo, distance, owner);
    }

    public CopyOnWriteArrayList<Route> getCollection(){
        CopyOnWriteArrayList<Route> routes = new CopyOnWriteArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseManager.doPreparedStatement(SELECT_ALL_ROUTES, false);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(DatabaseManager.ROUTE_TABLE_ID_COLUMN);
                routes.add(returnRoute(resultSet, id));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            databaseManager.closePreparedStatement(preparedStatement);
        }
        return routes;
    }

    private Coordinates getCoordinatesByID(int id) throws SQLException{
        Coordinates coordinates;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseManager.doPreparedStatement(SELECT_COORDINATES_BY_ID, false);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                coordinates = new Coordinates(
                        resultSet.getLong(DatabaseManager.COORDINATES_TABLE_X_COLUMN),
                        resultSet.getDouble(DatabaseManager.COORDINATES_TABLE_Y_COLUMN)
                );
            } else throw new SQLException();
        } catch (SQLException e){
            System.out.println("An error occurred while executing the SELECT_COORDINATES_BY_ID query!");
            throw new SQLException(e);
        } finally {
            databaseManager.closePreparedStatement(preparedStatement);
        }
        return coordinates;
    }

    private LocationFrom getLocationFromByID(int id) throws SQLException{
        LocationFrom locationFrom;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseManager.doPreparedStatement(SELECT_LOCATION_FROM_BY_ID, false);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                locationFrom = new LocationFrom(
                        resultSet.getLong(DatabaseManager.LOCATION_FROM_TABLE_X_COLUMN),
                        resultSet.getLong(DatabaseManager.LOCATION_FROM_TABLE_Y_COLUMN),
                        resultSet.getFloat(DatabaseManager.LOCATION_FROM_TABLE_Z_COLUMN)
                );
            } else throw new SQLException();
        } catch (SQLException e){
            System.out.println("An error occurred while executing the SELECT_LOCATION_FROM_BY_ID query!");
            throw new SQLException(e);
        } finally {
            databaseManager.closePreparedStatement(preparedStatement);
        }
        return locationFrom;
    }

    private LocationTo getLocationToToByID(int id) throws SQLException{
        LocationTo locationTo;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseManager.doPreparedStatement(SELECT_LOCATION_TO_BY_ID, false);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                locationTo = new LocationTo(
                        resultSet.getDouble(DatabaseManager.LOCATION_TO_TABLE_X_COLUMN),
                        resultSet.getFloat(DatabaseManager.LOCATION_TO_TABLE_Y_COLUMN),
                        resultSet.getFloat(DatabaseManager.LOCATION_TO_TABLE_Z_COLUMN),
                        resultSet.getString(DatabaseManager.LOCATION_TO_TABLE_NAME_COLUMN)
                );
            } else throw new SQLException();
        } catch (SQLException e){
            System.out.println("An error occurred while executing the SELECT_LOCATION_TO_BY_ID query!");
            throw new SQLException(e);
        } finally {
            databaseManager.closePreparedStatement(preparedStatement);
        }
        return locationTo;
    }

    private int getCoordinatesIdByRouteId(int routeID) throws SQLException{
        int coordinatesID;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseManager.doPreparedStatement(SELECT_ROUTE_BY_ID, false);
            preparedStatement.setInt(1, routeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                coordinatesID = resultSet.getInt(databaseManager.ROUTE_TABLE_COORDINATES_ID_COLUMN);
            } else throw new SQLException();
        } catch (SQLException e){
            System.out.println("An error occurred while executing the SELECT_ROUTE_BY_ID query!");
            throw new SQLException(e);
        } finally {
            databaseManager.closePreparedStatement(preparedStatement);
        }
        return coordinatesID;
    }

    private int getLocationFromByRouteId(int routeID) throws SQLException{
        int locationFromID;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseManager.doPreparedStatement(SELECT_ROUTE_BY_ID, false);
            preparedStatement.setInt(1, routeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                locationFromID = resultSet.getInt(databaseManager.ROUTE_TABLE_LOCATION_FROM_ID_COLUMN);
            }else throw new SQLException();
        } catch (SQLException e){
            System.out.println("An error occurred while executing the SELECT_ROUTE_BY_ID query!");
            throw new SQLException(e);
        } finally {
            databaseManager.closePreparedStatement(preparedStatement);
        }
        return locationFromID;
    }

    private int getLocationToByRouteId(int routeID) throws SQLException{
        int locationToID;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseManager.doPreparedStatement(SELECT_ROUTE_BY_ID, false);
            preparedStatement.setInt(1, routeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                locationToID = resultSet.getInt(databaseManager.ROUTE_TABLE_LOCATION_TO_ID_COLUMN);
            }else throw new SQLException();
        } catch (SQLException e){
            System.out.println("An error occurred while executing the SELECT_ROUTE_BY_ID query!");
            throw new SQLException(e);
        } finally {
            databaseManager.closePreparedStatement(preparedStatement);
        }
        return locationToID;
    }

    public Route insertRoute(RouteLite routeLite, User user) throws DatabaseManagerException {
        Route routeToInsert;
        PreparedStatement insertRoute = null;
        PreparedStatement insertCoordinates = null;
        PreparedStatement insertLocationFrom = null;
        PreparedStatement insertLocationTo = null;
        try {
            databaseManager.setCommit();
            databaseManager.setSavepoint();

            LocalDateTime localDateTime = LocalDateTime.now();

            insertCoordinates = databaseManager.doPreparedStatement(INSERT_COORDINATES, true);
            insertCoordinates.setLong(1, routeLite.getCoordinates().getX());
            insertCoordinates.setDouble(2, routeLite.getCoordinates().getY());
            if (insertCoordinates.executeUpdate() == 0) throw new SQLException();
            ResultSet resultSetCoordinates = insertCoordinates.getGeneratedKeys();
            int coordinateID;
            if (resultSetCoordinates.next()) coordinateID = resultSetCoordinates.getInt(1);
            else throw new SQLException();

            insertLocationFrom = databaseManager.doPreparedStatement(INSERT_LOCATION_FROM, true);
            insertLocationFrom.setLong(1, routeLite.getLocationFrom().getX());
            insertLocationFrom.setLong(2, routeLite.getLocationFrom().getY());
            insertLocationFrom.setFloat(3, routeLite.getLocationFrom().getZ());
            if (insertLocationFrom.executeUpdate() == 0) throw new SQLException();
            ResultSet resultSetLocationFrom = insertLocationFrom.getGeneratedKeys();
            int locationFromID;
            if (resultSetLocationFrom.next()) locationFromID = resultSetLocationFrom.getInt(1);
            else throw new SQLException();

            insertLocationTo = databaseManager.doPreparedStatement(INSERT_LOCATION_TO, true);
            insertLocationTo.setDouble(1, routeLite.getLocationTo().getX());
            insertLocationTo.setFloat(2, routeLite.getLocationTo().getY());
            insertLocationTo.setFloat(3, routeLite.getLocationTo().getZ());
            insertLocationTo.setString(4, routeLite.getLocationTo().getName());
            if (insertLocationTo.executeUpdate() == 0) throw new SQLException();
            ResultSet resultSetLocationTo = insertLocationTo.getGeneratedKeys();
            int locationToID;
            if (resultSetLocationTo.next()) locationToID = resultSetLocationTo.getInt(1);
            else throw new SQLException();

            insertRoute = databaseManager.doPreparedStatement(INSERT_ROUTE, true);
            insertRoute.setString(1, routeLite.getName());
            insertRoute.setInt(2, coordinateID);
            insertRoute.setTimestamp(3, Timestamp.valueOf(localDateTime));
            insertRoute.setInt(4, locationFromID);
            insertRoute.setInt(5, locationToID);
            insertRoute.setLong(6, routeLite.getDistance());
            insertRoute.setInt(7, databaseUserManager.getUserIdByUsername(user));
            if (insertRoute.executeUpdate() == 0) throw new SQLException();
            ResultSet resultSetRoute = insertRoute.getGeneratedKeys();
            int routeID;
            if (resultSetRoute.next()) routeID = resultSetRoute.getInt(1);
            else throw new SQLException();

            routeToInsert = new Route(
                    routeID,
                    routeLite.getName(),
                    routeLite.getCoordinates(),
                    localDateTime,
                    routeLite.getLocationFrom(),
                    routeLite.getLocationTo(),
                    routeLite.getDistance(),
                    user
            );

            databaseManager.commit();
            return routeToInsert;

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("An error occurred while adding a new object to the database!");
            databaseManager.rollback();
            throw new DatabaseManagerException();
        } finally {
            databaseManager.closePreparedStatement(insertRoute);
            databaseManager.closePreparedStatement(insertCoordinates);
            databaseManager.closePreparedStatement(insertLocationFrom);
            databaseManager.closePreparedStatement(insertLocationTo);
            databaseManager.setAutoCommit();
        }

    }

    public void updateRouteByID(int routeID, RouteLite routeLite) throws DatabaseManagerException{
        PreparedStatement updateRouteName = null;
        PreparedStatement updateRouteCoordinates = null;
        PreparedStatement updateRouteLocationFrom = null;
        PreparedStatement updateRouteLocationTo = null;
        PreparedStatement updateRouteDistance = null;
        try {
            databaseManager.setCommit();
            databaseManager.setSavepoint();

            updateRouteName = databaseManager.doPreparedStatement(UPDATE_ROUTE_NAME_BY_ID, false);
            updateRouteCoordinates = databaseManager.doPreparedStatement(UPDATE_COORDINATES_BY_ID, false);
            updateRouteLocationFrom = databaseManager.doPreparedStatement(UPDATE_LOCATION_FROM_BY_ID, false);
            updateRouteLocationTo = databaseManager.doPreparedStatement(UPDATE_LOCATION_TO_BY_ID, false);
            updateRouteDistance = databaseManager.doPreparedStatement(UPDATE_ROUTE_DISTANCE_BY_ID, false);

            if (routeLite.getName() != null){
                updateRouteName.setString(1, routeLite.getName());
                updateRouteName.setInt(2, routeID);
                if (updateRouteName.executeUpdate() == 0) throw new SQLException();
            }

            if (routeLite.getCoordinates() != null){
                updateRouteCoordinates.setLong(1, routeLite.getCoordinates().getX());
                updateRouteCoordinates.setDouble(2, routeLite.getCoordinates().getY());
                updateRouteCoordinates.setInt(3, getCoordinatesIdByRouteId(routeID));
                if (updateRouteCoordinates.executeUpdate() == 0) throw new SQLException();
            }

            if (routeLite.getLocationFrom() != null){
                updateRouteLocationFrom.setLong(1, routeLite.getLocationFrom().getX());
                updateRouteLocationFrom.setLong(2, routeLite.getLocationFrom().getY());
                updateRouteLocationFrom.setFloat(3, routeLite.getLocationFrom().getZ());
                updateRouteLocationFrom.setInt(4, getLocationFromByRouteId(routeID));
                if (updateRouteLocationFrom.executeUpdate() == 0) throw new SQLException();
            }

            if (routeLite.getLocationTo() != null){
                updateRouteLocationTo.setDouble(1, routeLite.getLocationTo().getX());
                updateRouteLocationTo.setFloat(2, routeLite.getLocationTo().getY());
                updateRouteLocationTo.setFloat(3, routeLite.getLocationTo().getZ());
                updateRouteLocationTo.setString(4, routeLite.getLocationTo().getName());
                updateRouteLocationTo.setInt(5, getLocationToByRouteId(routeID));
                if (updateRouteLocationTo.executeUpdate() == 0) throw new SQLException();
            }

            if (routeLite.getDistance() != -1){
                updateRouteDistance.setLong(1, routeLite.getDistance());
                updateRouteDistance.setInt(2, routeID);
                if (updateRouteDistance.executeUpdate() == 0) throw new SQLException();
            }

            databaseManager.commit();

        } catch (SQLException e){
            System.out.println("An error occurred while executing a group of requests to update an object!");
            databaseManager.rollback();
            throw new DatabaseManagerException();
        } finally {
            databaseManager.closePreparedStatement(updateRouteName);
            databaseManager.closePreparedStatement(updateRouteCoordinates);
            databaseManager.closePreparedStatement(updateRouteLocationFrom);
            databaseManager.closePreparedStatement(updateRouteLocationTo);
            databaseManager.closePreparedStatement(updateRouteDistance);
            databaseManager.setAutoCommit();
        }
    }

    public boolean checkRouteByIdAndUserId(int routeID, User user) throws DatabaseManagerException{
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseManager.doPreparedStatement(SELECT_ROUTE_BY_ID_AND_USER_ID, false);
            preparedStatement.setInt(1, routeID);
            preparedStatement.setInt(2, databaseUserManager.getUserIdByUsername(user));
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e){
            System.out.println("An error occurred while executing the SELECT_ROUTE_BY_ID_AND_USER_ID query!");
            throw new DatabaseManagerException();
        } finally {
            databaseManager.closePreparedStatement(preparedStatement);
        }
    }

    public void deleteRouteByID(int routeID) throws DatabaseManagerException{
        PreparedStatement deleteRoute = null;
        PreparedStatement deleteCoordinates = null;
        PreparedStatement deleteLocationFrom = null;
        PreparedStatement deleteLocationTo = null;
        try {
           int coordinatesID = getCoordinatesIdByRouteId(routeID);
           int locationFromID = getLocationFromByRouteId(routeID);
           int locationToID = getLocationToByRouteId(routeID);
           deleteRoute = databaseManager.doPreparedStatement(DELETE_ROUTE_BY_ID, false);
           deleteRoute.setInt(1, routeID);
           if (deleteRoute.executeUpdate() == 0) throw new SQLException();
           deleteCoordinates = databaseManager.doPreparedStatement(DELETE_COORDINATES_BY_ID, false);
           deleteCoordinates.setInt(1, coordinatesID);
           if (deleteCoordinates.executeUpdate() == 0) throw new SQLException();
           deleteLocationFrom = databaseManager.doPreparedStatement(DELETE_LOCATION_FROM_BY_ID, false);
           deleteLocationFrom.setInt(1, locationFromID);
           if (deleteLocationFrom.executeUpdate() == 0) throw new SQLException();
           deleteLocationTo = databaseManager.doPreparedStatement(DELETE_LOCATION_TO_BY_ID, false);
           deleteLocationTo.setInt(1, locationToID);
           if (deleteLocationTo.executeUpdate() == 0) throw new SQLException();
        } catch (SQLException e){
            System.out.println("An error occurred while executing the DELETE_ROUTE_BY_ID request!");
            throw new DatabaseManagerException();
        } finally {
            databaseManager.closePreparedStatement(deleteRoute);
            databaseManager.closePreparedStatement(deleteCoordinates);
            databaseManager.closePreparedStatement(deleteLocationFrom);
            databaseManager.closePreparedStatement(deleteLocationTo);
        }
    }

    public void clearCollection() throws DatabaseManagerException{
        CopyOnWriteArrayList<Route> routes = getCollection();
        for (Route route : routes){
            deleteRouteByID(route.getId());
        }
    }
}

