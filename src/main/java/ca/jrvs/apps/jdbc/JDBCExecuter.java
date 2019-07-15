package ca.jrvs.apps.jdbc;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecuter {


    public static void main(String... args) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "hplussport", "postgres", "password");
        try {
            Connection connection = dcm.getConnection();
            OrderDAO orderDAO = new OrderDAO(connection);
            Orders order= orderDAO.findById(1000);
            System.out.println(order);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}






