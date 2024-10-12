package com.cinema.dao;

import com.cinema.database.PgSqlConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao <T> {
    private PgSqlConnectionFactory connectionFactory;
    public abstract String getTableName();
    public abstract T convertToObj(ResultSet resultSet) throws SQLException;

    public abstract String getCreateQuery();
    public abstract void prepareParam (PreparedStatement preparedStatement, T entity) throws SQLException;

    public AbstractDao(){
        this.connectionFactory = new PgSqlConnectionFactory();
    }
    public T findbyId(int id) throws SQLException{
        String query = "select * from " + this.getTableName() + "where id = ?";
        Connection connection = this.connectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            T object = convertToObj(resultSet);
            return object;
        }
        this.connectionFactory.closeConnection();

        return null;


    }
    public List<T> getAll() throws SQLException{
        List<T> objects = new ArrayList<T>();
        String query = "select * from "+ this.getTableName();
        Connection connection = this.connectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            T object = this.convertToObj(resultSet);
            objects.add(object);
        }
        this.connectionFactory.closeConnection();;
        return objects;
    }
    public void create(T entity) throws SQLException{
        String query = this.getCreateQuery();
        Connection connection = this.connectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        this.prepareParam(preparedStatement, entity); //pass by reference
        preparedStatement.executeUpdate();

    }

    public abstract void delete(T entity) throws SQLException;
}
