package com.cinema.dao;

import com.cinema.model.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TicketDao extends AbstractDao<Ticket>{
    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public Ticket convertToObj(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    public void prepareParam(PreparedStatement preparedStatement, Ticket entity) throws SQLException {

    }

    @Override
    public Ticket findbyId(int id) {
        return null;
    }

    @Override
    public void create(Ticket entity) {

    }

    @Override
    public void delete(Ticket entity) throws SQLException {

    }
}
