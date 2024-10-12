package com.cinema.dao;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.Cinema;
import com.cinema.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao extends AbstractDao<Customer>{
    private PgSqlConnectionFactory connectionFactory;

    @Override
    public String getTableName() {
        return "customers";
    }

    @Override
    public Customer convertToObj(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setName(resultSet.getString("name"));
        return customer;
    }

    @Override
    public String getCreateQuery() {
        return "INSERT into " + this.getTableName() + " (name, address) values (?, ?)";
    }

    @Override
    public void prepareParam(PreparedStatement preparedStatement, Customer customer) throws SQLException {
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getAddress());

    }

    public CustomerDao(){
        this.connectionFactory = new PgSqlConnectionFactory();
    }

    @Override
    public Customer findbyId(int id) throws SQLException {
        String query = "select * from customers where id = ?";
        try(Connection connection = this.connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    Customer customer = new Customer();
                    customer.setId(resultSet.getInt("id"));
                    customer.setName(resultSet.getString("name"));
                    this.connectionFactory.closeConnection();
                    return customer;
                }

            }

        }
        this.connectionFactory.closeConnection();

        return null;
    }


    @Override
    public void create(Customer customer) throws SQLException{
        String query = "insert into customers (name) values (?)";
        try(Connection connection = this.connectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setString(1, customer.getName());
            preparedStatement.executeUpdate();

        }
        this.connectionFactory.closeConnection();

    }

    @Override
    public void delete(Customer customer) throws SQLException {
        String query = "delete from customers where id = ?";

        Connection connection = this.connectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.executeUpdate();
        this.connectionFactory.closeConnection();
    };

}
