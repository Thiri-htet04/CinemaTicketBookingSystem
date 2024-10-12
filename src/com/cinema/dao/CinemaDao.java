package com.cinema.dao;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.Cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CinemaDao extends AbstractDao<Cinema>{
    private PgSqlConnectionFactory connectionFactory ;

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

            return cinema;
        }
        return null;
    }

    @Override
    public List<Cinema> getAll() throws SQLException {
        return null;
    }

    @Override
    public void create(Cinema entity) throws SQLException {

    }

    @Override
    public void delete(Cinema entity) throws SQLException {

    }
}
