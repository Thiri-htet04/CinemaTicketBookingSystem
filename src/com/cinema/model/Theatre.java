package com.cinema.model;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private int id;
    private String name;
    private Cinema cinema;
    private List<Seat> seats = new ArrayList<Seat>();  //will store seat objects

}
