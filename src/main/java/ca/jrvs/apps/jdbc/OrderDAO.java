package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;
import com.sun.jmx.remote.util.OrderClassLoaders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DataAccessObject<Orders> {


    public static final String READ = "SELECT  c.first_name, c.last_name, c.email, " +
            "o.order_id,  o.creation_date, o.total_due, o.status, " +
            " s.first_name, s.last_name, s.email,  " +
            "ol.quantity,  p.code, p.name, p.size," +
            " p.variety, p.price FROM orders o " +
            "join customer c on o.customer_id = c.customer_id " +
            "join salesperson s on o.salesperson_id=s.salesperson_id" +
            "join order_item ol on ol.order_id=o.order_id" +
            "join product p on ol.product_id = p.product_id" +
            "WHERE o.order_id = ?;";

    public OrderDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Orders findById(long id) {
              Orders orders=new Orders();
        try(PreparedStatement statement=this.connection.prepareStatement(READ);) {

            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            List<OrderLine> orderLines = new ArrayList<>();


            while (rs.next()) {

                orders.setCustomerFirstName(rs.getString(1));
                orders.setCustomerLastName(rs.getString("2"));
                orders.setCustomerEmail(rs.getString("3"));
                orders.setId(rs.getLong(4));
                long ordersId = orders.getId();
                orders.getCreationDate(new Date(rs.getDate(5).getTime()));
                orders.getTotalDue(rs.getBigDecimal(6));
                orders.getStatus(rs.getString(7));
                orders.getSalespersonFirstName(rs.getString(8));
                orders.getSalespersonLastName(rs.getString(9));
                orders.getSalespersonEmail(rs.getString(10));


            OrderLine orderLine = new OrderLine();
            orderLine.setQuantity(rs.getInt(11));
            orderLine.setProductCode(rs.getString(12));
            orderLine.setProductName(rs.getString(13));
            orderLine.setProductSize(rs.getInt(14));
            orderLine.setProductVariety(rs.getString(15));
            orderLine.setProductPrice(rs.getBigDecimal(16));
            orderLines.add(orderLine);
        }
            orders.setOrderLines(orderLines);
            return  orders;


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("THe reason for failure is "+e);
        }
    }

    @Override
    public List<Orders> findAll() {
        return null;
    }

    @Override
    public Orders update(Orders dto) {
        return null;
    }

    @Override
    public Orders create(Orders dto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
