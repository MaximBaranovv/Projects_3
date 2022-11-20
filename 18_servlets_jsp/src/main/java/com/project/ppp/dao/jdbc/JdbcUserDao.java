package com.project.ppp.dao.jdbc;

import com.project.ppp.dao.RoleDao;
import com.project.ppp.dao.UserDao;
import com.project.ppp.entity.Role;
import com.project.ppp.entity.User;
import configuration.ConnectionSupplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JdbcUserDao implements UserDao {

    private static final String LOGIN = "login";
    private static final String EMAIL = "email";

    private final ConnectionSupplier connectionSupplier;
    private final RoleDao roleDao;

    public JdbcUserDao(ConnectionSupplier connectionSupplier) {
        this.connectionSupplier = connectionSupplier;
        this.roleDao = new JdbcRoleDao(this.connectionSupplier);
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO jdbc_user (login, password, email, first_name, last_name, birthday, role_id) VALUES (?,?,?,?,?,?,?);";
        try (Connection connection = connectionSupplier.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            setUserData(preparedStatement, user);
            preparedStatement.executeUpdate();
            connection.commit();
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE jdbc_user SET login = ?, password = ?, email = ?, first_name = ?, last_name = ?, birthday = ?, role_id = ? WHERE id= ?;";
        try (Connection connection = connectionSupplier.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            setUserData(preparedStatement, user);
            preparedStatement.setLong(8, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setUserData(PreparedStatement statement, User user) throws SQLException {
        Role role = roleDao.findById(user.getRole().getId());
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getFirstName());
        statement.setString(5, user.getLastName());
        statement.setDate(6, user.getBirthday());
        statement.setLong(7, role.getId());
    }

    @Override
    public void remove(User user) {
        String sql = "DELETE FROM jdbc_user WHERE id = ?;";
        try (Connection connection = connectionSupplier.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM jdbc_user;";
        try (Connection connection = connectionSupplier.getConnection();
             Statement statement = connection.createStatement()) {
            Map<Long, Role> roles = roleDao.findAll()
                    .stream()
                    .collect(Collectors.toMap(Role::getId, Function.identity()));

            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    list.add(getUser(resultSet, roles));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public User findById(long id) {
        User user;
        String sql = "SELECT * FROM jdbc_user WHERE id = ?;";
        try (Connection connection = connectionSupplier.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            Map<Long, Role> roles = roleDao.findAll()
                    .stream()
                    .collect(Collectors.toMap(Role::getId, Function.identity()));

            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                user = getUser(resultSet, roles);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    private User getUser(ResultSet resultSet, Map<Long, Role> roles) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(1));
        user.setLogin(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setEmail(resultSet.getString(4));
        user.setFirstName(resultSet.getString(5));
        user.setLastName(resultSet.getString(6));
        user.setBirthday(resultSet.getDate(7));
        user.setRole(roles.get(resultSet.getLong(8)));

        return user;
    }

    @Override
    public User findByLogin(String login) {
        return findByString(LOGIN, login);
    }

    @Override
    public User findByEmail(String email) {
        return findByString(EMAIL, email);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        String sql = "SELECT * FROM jdbc_user WHERE login = ? and password = ?;";
        User user;
        try (Connection connection = connectionSupplier.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                user = new User();
                user.setId(resultSet.getLong(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setFirstName(resultSet.getString(5));
                user.setLastName(resultSet.getString(6));
                user.setBirthday(resultSet.getDate(7));
                user.setRole(roleDao.findById(resultSet.getLong(8)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean userIsExist(String login, String password) {
        String sql = "SELECT * FROM jdbc_user u JOIN jdbc_role br on br.id = u.role_id WHERE login=? AND password=?";
        boolean isExist = false;
        User user = null;
        try (Connection connection = connectionSupplier.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            try {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getLong(1));
                    user.setLogin(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                    user.setEmail(resultSet.getString(4));
                    user.setFirstName(resultSet.getString(5));
                    user.setLastName(resultSet.getString(6));
                    user.setBirthday(resultSet.getDate(7));
                    user.setRole(roleDao.findById(resultSet.getLong(8)));
                }
            } catch (SQLException throwables) {
                throw new RuntimeException(throwables);
            }
            if (user != null) {
                isExist = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExist;
    }

    private User findByString(String key, String value) {
        String sql = "SELECT * FROM jdbc_user WHERE " + key + " = ?;";
        try (Connection connection = connectionSupplier.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            Map<Long, Role> roles = roleDao.findAll()
                    .stream()
                    .collect(Collectors.toMap(Role::getId, Function.identity()));

            preparedStatement.setString(1, value);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return getUser(resultSet, roles);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
