import java.sql.*;

public class App {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "expresso_shop";
    private static final String USER = "Aryan";
    private static final String PASSWORD = "cs380";

    public static void main(String[] args) {
        try {
            // Register MySQL JDBC driver
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            // Connect to MySQL server
            Connection connection = DriverManager.getConnection(JDBC_URL + DB_NAME, USER, PASSWORD);
            System.out.println("Connected to the database");

            // Create table
            createTable(connection);

            // Insert sample records
            insertRecords(connection);

            // Retrieve and print data
            retrieveData(connection);

            // Close connection
            connection.close();
            System.out.println("Connection closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS inventory_location1 ("
                + "itemID INT,"
                + "itemName VARCHAR(255),"
                + "inStock INT"
                + ")";

        Statement statement = connection.createStatement();
        statement.executeUpdate(createTableSQL);
        System.out.println("Table 'inventory_location1' created (if not existed)");
    }

    private static void insertRecords(Connection connection) throws SQLException {
        String insertSQL = "INSERT INTO inventory_location1 (itemID, itemName, inStock) VALUES "
                + "(1234, 'smallCup', 45), "
                + "(1235, 'smallLid', 90), "
                + "(2341, 'darkCoffee', 10)";

        Statement statement = connection.createStatement();
        statement.executeUpdate(insertSQL);
        System.out.println("Sample records inserted into 'inventory_location1'");
    }

    private static void retrieveData(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM inventory_location1";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectSQL);
        System.out.println("Retrieving data from 'inventory_location1':");
        System.out.println("itemID\titemName\tinStock");

        while (resultSet.next()) {
            int itemID = resultSet.getInt("itemID");
            String itemName = resultSet.getString("itemName");
            int inStock = resultSet.getInt("inStock");
            System.out.println(itemID + "\t" + itemName + "\t" + inStock);
        }
    }
}
