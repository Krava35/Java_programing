package serverModule.util;

import java.io.Console;
import java.sql.*;
import java.util.Scanner;

public class DatabaseManager {
    public static final String ROUTE_TABLE = "routes";
    public static final String USER_TABLE = "users";
    public static final String COORDINATES_TABLE = "coordinates";
    public static final String LOCATION_FROM_TABLE = "location_from";
    public static final String LOCATION_TO_TABLE = "location_to";

    public static final String ROUTE_TABLE_ID_COLUMN = "id";
    public static final String ROUTE_TABLE_NAME_COLUMN ="name";
    public static final String ROUTE_TABLE_COORDINATES_ID_COLUMN = "coordinates_id";
    public static final String ROUTE_TABLE_CREATION_DATE_COLUMN = "creation_date";
    public static final String ROUTE_TABLE_LOCATION_FROM_ID_COLUMN = "location_from_id";
    public static final String ROUTE_TABLE_LOCATION_TO_ID_COLUMN = "location_to_id";
    public static final String ROUTE_TABLE_DISTANCE_COLUMN = "distance";
    public static final String ROUTE_TABLE_USER_ID_COLUMN = "user_id";

    public static final String USER_TABLE_ID_COLUMN = "id";
    public static final String USER_TABLE_USERNAME_COLUMN = "username";
    public static final String USER_TABLE_PASSWORD_COLUMN = "password";
    public static final String USER_TABLE_ONLINE_COLUMN = "online";

    public static final String COORDINATES_TABLE_ID_COLUMN = "id";
    public static final String COORDINATES_TABLE_X_COLUMN = "x";
    public static final String COORDINATES_TABLE_Y_COLUMN = "y";

    public static final String LOCATION_FROM_TABLE_ID_COLUMN = "id";
    public static final String LOCATION_FROM_TABLE_X_COLUMN = "x";
    public static final String LOCATION_FROM_TABLE_Y_COLUMN = "y";
    public static final String LOCATION_FROM_TABLE_Z_COLUMN = "z";

    public static final String LOCATION_TO_TABLE_ID_COLUMN = "id";
    public static final String LOCATION_TO_TABLE_X_COLUMN = "x";
    public static final String LOCATION_TO_TABLE_Y_COLUMN = "y";
    public static final String LOCATION_TO_TABLE_Z_COLUMN = "z";
    public static final String LOCATION_TO_TABLE_NAME_COLUMN = "name";

    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private final String url = "jdbc:postgresql://pg:5432/studs";

    private String user;
    private String password;
    private Connection connection;

    public DatabaseManager(){
        doConnectionToDatabase();
    }

    private void doConnectionToDatabase(){
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();
        System.out.println("Database connection ...");
        while (true){
            System.out.println("Enter login:");
            this.user = scanner.nextLine();
            System.out.println("Enter password:");
            this.password = String.valueOf(console.readPassword());
            try{
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Database connection established!");
                break;
            } catch (ClassNotFoundException e) {
                System.out.println("Database driver not found!");
                System.exit(0);
            } catch (SQLException e){
                System.out.println("An error occurred while connecting to the database!");
                System.out.println("Check the correctness of the login and password!");
            }
        }

    }

    public PreparedStatement doPreparedStatement(String sqlStatement, boolean generateKeys) throws SQLException {
        PreparedStatement preparedStatement;
        try {
            if (connection == null) throw new SQLException();
            int autoGenerationKeys = generateKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS;
            preparedStatement = connection.prepareStatement(sqlStatement, autoGenerationKeys);
            return preparedStatement;
        }catch (SQLException e){
            if (connection == null) {
                System.out.println("Database connection not established!");
            }
        }
        throw new SQLException();
    }

    public void closePreparedStatement(PreparedStatement preparedStatement){
        if (preparedStatement == null) return;
        try{
            preparedStatement.close();
        } catch (SQLException e){
            System.out.println("Failed to close SQL query");
        }
    }

    public void closeConnection(){
        if (connection == null) return;
        try{
            connection.close();
            System.out.println("The connection to the database has been dropped!");
        } catch (SQLException e){
            System.out.println("An error occurred while disconnecting the database connection!");
        }
    }

    public void setCommit(){
        try{
            if (connection == null) throw new SQLException();
            connection.setAutoCommit(false);
        } catch (SQLException e){
            System.out.println("An error occurred while setting 'commit'!");
        }
    }

    public void setAutoCommit(){
        try{
            if (connection == null) throw new SQLException();
            connection.setAutoCommit(true);
        } catch (SQLException e){
            System.out.println("An error occurred while confirming the new state of the database!");
        }
    }

    public void commit() {
        try {
            if (connection == null) throw new SQLException();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("An error occurred while confirming the new state of the database!");
        }
    }

    public void rollback(){
        try {
            if (connection == null) throw new SQLException();
            connection.rollback();
        } catch (SQLException e){
            System.out.println("An error occurred while resetting the database to its original state!");
        }
    }

    public void setSavepoint() {
        try {
            if (connection == null) throw new SQLException();
            connection.setSavepoint();
        } catch (SQLException e){
            System.out.println("An error occurred while saving the database state!");
        }
    }
}
