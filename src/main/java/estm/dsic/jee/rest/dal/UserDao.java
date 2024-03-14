package estm.dsic.jee.rest.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import estm.dsic.jee.rest.models.User;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserDao implements Repository<User, String> {

    @Resource(lookup = "jdbc/g-note")
    private DataSource datasource;
    private Connection conn;


    // public UserDao(DataSource dataSource) {
    //     this.datasource = dataSource;
    //     initializeConnection();
    // }
    private void initializeConnection() {
        try {
            conn = datasource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
public void create(User entity) {
    String sql = "INSERT INTO users (email, password, isAdmin, isActive) VALUES (?, ?, ?, ?)";

    try (Connection conn = datasource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, entity.getEmail());
        stmt.setString(2, entity.getPassword());
        stmt.setBoolean(3, false); // isAdmin is false by default
        stmt.setBoolean(4, false); // isActive is false by default

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @Override
    public User auth(User entity) {
        String query="SELECT * FROM users WHERE email = ? AND password=?";
        try{
            conn = datasource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            ResultSet rs= preparedStatement.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getBoolean("isAdmin"));
                user.setActive(rs.getBoolean("isActive"));

                user.setId(rs.getInt("id"));
                return user;
            }

        }catch (SQLException e){
            e.printStackTrace();

        }
        return null;
    }

    @Override
public void delete(User entity, String index) {
    String sql = "DELETE FROM users WHERE id = ?";
    try (Connection connection = datasource.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql)) {
        
        statement.setString(1, index);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Error deleting user", e);
    }
}

@Override
public void update(User entity, String index) {
    String sql = "UPDATE users SET email = ?, password = ?, isAdmin = ?, isActive = ? WHERE id = ?";
    try (Connection connection = datasource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
        
        statement.setString(1, entity.getEmail());
        statement.setString(2, entity.getPassword());
        statement.setBoolean(3, false);
        statement.setBoolean(4, entity.isActive());
        statement.setString(5, index);

        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Error updating user", e);
    }
}

@Override
public List<User> getAll() {
    List<User> users = new ArrayList<>();
    String query = "SELECT * FROM users";
    try (Connection conn = datasource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            User user = new User();
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setAdmin(rs.getBoolean("isAdmin"));
            user.setActive(rs.getBoolean("isActive"));
            user.setId(rs.getInt("id"));
            // Add any other fields here
            users.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exception
    }
    return users;
}



    
}
