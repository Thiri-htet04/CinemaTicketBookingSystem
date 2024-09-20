package com.cinema.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private int id;
    private List<Ticket> tickets = new ArrayList<Ticket>();

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" +name +"]";
    }
}
