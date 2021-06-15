package pl.camp.it.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.model.BasketPosition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderPositionDAO {

    @Autowired
    Connection connection;

    public void addOrderPosition(BasketPosition position, int orderId){
        try{
            String sql = "INSERT INTO torderposition (quantity, item_id, order_id) VALUES (?, ?, ?);";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setInt(1, position.getQuantity());
            preparedStatement.setInt(2, position.getItem().getId());
            preparedStatement.setInt(3,orderId);

            preparedStatement.executeUpdate();

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public List<BasketPosition> getOrderPositionsForOrder(int orderId){
        List<BasketPosition> result = new ArrayList<>();

        try{
            String sql = "SELECT * FROM torderposition WHERE order_id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setInt(1, orderId);


            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                BasketPosition basketPosition = new BasketPosition();
                basketPosition.setId(rs.getInt("id"));
                basketPosition.setQuantity(rs.getInt("quantity"));
                basketPosition.setItemId(rs.getInt("item_id"));

            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return result;
    }

}
