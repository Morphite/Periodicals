package ua.nure.leonov.summarytask.pool;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class TestConnection {

    private final static Logger LOG = Logger.getLogger(ConnectionPool.class);

    public static void main(String[] args) throws ConnectionPoolException, SQLException {
        LOG.info("Test a connection");
        System.out.println("Test a log");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from user");
        while (rs.next()) {
            System.out.print(rs.getString(1) + " " + rs.getString(2) + " "
                    + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " "
                    + rs.getString(6) + " " + rs.getString(7) + " " + rs.getString(8) + " ");
        }
    }
}
