package com.cinema.dao;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.Cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinemaDao extends AbstractDao<Cinema>{
    private PgSqlConnectionFactory connectionFactory ;

    @Override
    public String getTableName() {
        return "cinemas";
    }

    @Override
    public Cinema convertToObj(ResultSet resultSet) throws SQLException {
        Cinema cinema = new Cinema();
        cinema.setId(resultSet.getInt("id"));
        cinema.setName(resultSet.getString("name"));
        cinema.setAddress(resultSet.getString("address"));
        return cinema;

    }

    @Override
    public String getCreateQuery() {
        return "INSERT into " + this.getTableName() + "(name, address)" + " values (? , ?)";
    }

    @Override
    public void prepareParam(PreparedStatement preparedStatement, Cinema cinema) throws SQLException {
        preparedStatement.setString(1, cinema.getName());
        preparedStatement.setString(2, cinema.getAddress());
    }

    public CinemaDao(){
        this.connectionFactory = new PgSqlConnectionFactory();
    }
    @Override
    public Cinema findbyId(int id) throws SQLException {
        String query = "select * from cinemas where id = ?";
        Connection connection = this.connectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            Cinema cinema = new Cinema();
            cinema.setId(resultSet.getInt("id"));
            cinema.setName(resultSet.getString("name"));
            cinema.setAddress(resultSet.getString("address"));
            this.connectionFactory.closeConnection();

            return cinema;
        }
        return null;
    }


    @Override
    public void create(Cinema cinema) throws SQLException {
        String sql = "INSERT INTO cinemas (name, address) VALUES (?, ?)";
        Connection connection = this.connectionFactory.createConnection();
        connection.prepareStatement(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cinema.getName());
        preparedStatement.setString(2, cinema.getAddress());
        preparedStatement.executeUpdate();
        this.connectionFactory.closeConnection();



    }

    @Override
    public void delete(Cinema entity) throws SQLException {

    }
}
