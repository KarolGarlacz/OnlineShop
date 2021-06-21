package pl.camp.it.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.processor.SpringErrorsTagProcessor;
import pl.camp.it.dao.IItemDAO;
import pl.camp.it.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ItemDAO implements IItemDAO{

    @Autowired
    Connection connection;

    public List<Item> getAllItems(){
        List<Item> result = new ArrayList<>();
        try{
            String sql = "SELECT * FROM titem";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                result.add(mapItem(rs));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    return result;
    }
    public List<Item> getFilteredItems(String pattern){
        List<Item> result = new ArrayList<>();
        try{
            String sql = "SELECT * FROM titem WHERE name LIKE ? OR category LIKE ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1, "%"+pattern+"%");
            preparedStatement.setString(2, "%"+pattern+"%");

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                result.add(mapItem(rs));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return result;
    }

    public void addItem(Item item){
        try{
            String sql = "INSERT INTO titem (name, price, quantity, code, category) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2,item.getPrice());
            preparedStatement.setInt(3,item.getQuantity());
            preparedStatement.setString(4, item.getCode());
            preparedStatement.setString(5, item.getCategory());
            preparedStatement.executeUpdate();

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }



    public Item findItemsByCode(String code){
        try{
            String sql = "SELECT * FROM titem WHERE code = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1, code);

            ResultSet rs = preparedStatement.executeQuery();

            if(!rs.next()){
                return null;
            }
            return mapItem(rs);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
}

public void updateItem(Item item){
        try{
            String sql = "UPDATE titem SET name = ?, price = ?, quantity = ?, code = ?, category = ? WHERE id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2,item.getPrice());
            preparedStatement.setInt(3,item.getQuantity());
            preparedStatement.setString(4, item.getCode());
            preparedStatement.setString(5, item.getCategory());
            preparedStatement.setInt(6, item.getId());

            preparedStatement.executeUpdate();

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
}

public Item getItemById(int itemId){
        try{
            String sql = "SELECT * FROM titem WHERE id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setInt(1, itemId);

            ResultSet rs = preparedStatement.executeQuery();

            if(!rs.next()){
                return null;
            }
            return mapItem(rs);

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
}

    private Item mapItem(ResultSet rs){
        try{
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setPrice(rs.getDouble("price"));
            item.setQuantity(rs.getInt("quantity"));
            item.setCode(rs.getString("code"));
            item.setCategory(rs.getString("category"));

            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
