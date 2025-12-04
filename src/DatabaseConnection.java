import java.sql.*;

public class DatabaseConnection {
    private static Connection connection;
    
    public static void setConnection(Connection conn) {
        connection = conn;
    }
    
    public static Connection getConnection() {
        return connection;
    }
    
    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}