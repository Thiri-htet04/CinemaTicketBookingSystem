package com.cinema.service;

import com.cinema.dao.AbstractDao;
import com.cinema.dao.CinemaDao;
import com.cinema.model.Cinema;
import com.cinema.model.Customer;

import java.io.IOException;
import java.sql.SQLException;

public class CinemaService extends BaseService<Cinema>{
    private AbstractDao cinemaDao;
    public CinemaService(AbstractDao cinemaDao){
        super(cinemaDao);
        this.cinemaDao = cinemaDao;
    }

    @Override
    public Cinema getEntityObject(int id) {
        Cinema cinema = new Cinema();
        cinema.setId(id);
        return cinema;
    }

    @Override
    public String getEntity() {
        return "Cinema";
    }

    @Override
    public void register() throws IOException, SQLException {
        System.out.print("Enter Cinema Name: ");
        String name = br.readLine();
        Cinema cinema = new Cinema();
        cinema.setName(name);
        System.out.print("Enter Cinema Address: ");
        String address = br.readLine();
        cinema.setAddress(address);
        this.cinemaDao.create(cinema);
    }
}
