package pl.camp.it.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.dao.IUserDAO;
import pl.camp.it.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserDAO implements IUserDAO {

    @Autowired
    Connection connection;

    public User getUserByLogin(String login){
        try{
            String sql = "SELECT * FROM tuser WHERE login = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1,login);

            ResultSet rs = preparedStatement.executeQuery();

            if(!rs.next()){
                return null;
            }
            return mapUser(rs);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }


    public void addUser(User user){
        try{
            String sql = "INSERT INTO tuser (name, surname, login, password, status) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getStatus().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    private User mapUser(ResultSet rs){
        try{
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setStatus(User.Status.valueOf(rs.getString("status")));
        return user;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
}
