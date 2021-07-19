package simulator;

import java.sql.*;

public class Connect {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/atm";

    private Connection conn = null;
    private Statement stmnt = null;

    public Connect(){

        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, "root", "");
            stmnt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public Statement getStmnt(){
        return stmnt;
    }
    public void cleanUp(){
        try {
            stmnt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
