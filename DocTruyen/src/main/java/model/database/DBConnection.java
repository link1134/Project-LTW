package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String jdbcServerName = "50NWOS";              // tên server SQL (VD: localhost hoặc LAPTOP-XXXX)
    private static final String jdbcDataBase = "DocTruyenDataBase";     // tên database
    private static final String jdbcUsername = "sa";                    // user SQL
    private static final String jdbcPassword = "123456";                // password SQL

    
    private static final String jdbcURL = 
        "jdbc:sqlserver://" + jdbcServerName + ":1433;databaseName=" + jdbcDataBase + ";encrypt=false;";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println(" Kết nối CSDL thành công!");
        } catch (ClassNotFoundException e) {
            System.out.println(" Không tìm thấy driver JDBC SQL Server!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(" Lỗi khi kết nối CSDL!");
            e.printStackTrace();
        }
        return conn;
    }

    
    public static void main(String[] args) {
        Connection connection = getConnection();
        if (connection != null) {
            System.out.println("Kết nối thành công đến " + jdbcDataBase);
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Kết nối thất bại!");
        }
    }
}
