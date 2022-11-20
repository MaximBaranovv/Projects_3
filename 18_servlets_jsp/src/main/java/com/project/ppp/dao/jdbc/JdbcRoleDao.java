package com.project.ppp.dao.jdbc;

import com.project.ppp.dao.RoleDao;
import com.project.ppp.entity.Role;
import configuration.ConnectionSupplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcRoleDao implements RoleDao {

    private final ConnectionSupplier connectionSupplier;

    public JdbcRoleDao(ConnectionSupplier connectionSupplier) {
        this.connectionSupplier = connectionSupplier;
    }

    @Override
    public void create(Role role) {
        String sql = "INSERT INTO jdbc_role (name) VALUES (?);";
        try (Connection connection = connectionSupplier.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, role.getName());
            preparedStatement.executeUpdate();
            connection.commit();
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Role role) {
        String sql = "UPDATE jdbc_role SET name = ? WHERE id = ?";
        try (Connection connection = connectionSupplier.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, role.getName());
            preparedStatement.setLong(2, role.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Role role) {
        String sql = "DELETE FROM jdbc_role WHERE id = ?";
        try (Connection connection = connectionSupplier.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, role.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Role> findAll() {
        List<Role> list = new ArrayList<>();
        String sql = "SELECT * FROM jdbc_role;";
        try (Connection connection = connectionSupplier.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    Role role = new Role();
                    role.setId(resultSet.getLong(1));
                    role.setName(resultSet.getString(2));
                    list.add(role);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public Role findById(long id) {
        Role role;
        String sql = "SELECT * FROM jdbc_role WHERE id = ?;";
        try (Connection connection = connectionSupplier.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                role = new Role();
                role.setId(resultSet.getLong(1));
                role.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return role;
    }

    @Override
    public Role findByName(String name) {
        Role role;
        String sql = "SELECT * FROM jdbc_role WHERE name = ?;";
        try (Connection connection = connectionSupplier.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                role = new Role();
                role.setId(resultSet.getLong(1));
                role.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return role;
    }
}
