package adv;

import java.sql.*;

public class testsql {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/unitsync";
        String user = "root";
        String password = "jojo"; // put your password here

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to UnitSync!");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM residents WHERE status = 'renting'");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("resid") + " | Name: " + rs.getString("name") + " | Apt: " + rs.getString("apt_number"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}