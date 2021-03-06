package pl.camp.it.dao.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.dao.IOrderDAO;
import pl.camp.it.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDAO implements IOrderDAO{

    @Autowired
    Connection connection;

    public int addOrder(Order order){
        try{
            String sql = "INSERT INTO torder (price, date, user_id) VALUES (?, ?, ?);";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setDouble(1, order.getPrice());
            preparedStatement.setDate(2, new java.sql.Date(order.getDate().getTime()));
            preparedStatement.setInt(3,order.getUser().getId());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(!rs.next()){
                return 0;
            }
            return rs.getInt(1);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return 0;
    }


    public List<Order> getOrdersForUser(int userId){
        List<Order> result = new ArrayList<>();

        try{
            String sql = "SELECT * FROM torder WHERE user_id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setInt(1,userId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setPrice(rs.getDouble("price"));
                order.setDate(rs.getDate("date"));
                order.setUserId(rs.getInt("user_id"));

                result.add(order);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return result;
    }
}
