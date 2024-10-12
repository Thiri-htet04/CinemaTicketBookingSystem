package com.cinema.test;

import com.cinema.dao.CinemaDao;
import com.cinema.dao.CustomerDao;
import com.cinema.model.Customer;
import com.cinema.service.CinemaService;
import com.cinema.service.CustomerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberInputStream;
import java.sql.SQLException;
import java.util.List;

public class CinemaTest {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void options(){
        System.out.println("Choose an option");
        System.out.println("1. Cinema");
        System.out.println("2. Customer");
        System.out.println("3. Exit");
    }
    public static void main(String[] args) throws SQLException, IOException {

        boolean exit = true;

        do {
            options();
            System.out.print("Option: ");
            int choice = Integer.parseInt(br.readLine());
            switch (choice) {
                case 1:
                    callCinemaService();
                    break;
                case 2:
                    callCustomerService();
                    break;
                case 3:
                    System.out.println("Exiting from the system.");
                    exit = false;
                    break;
            }
        } while (exit);

    }

    private static void callCinemaService() throws SQLException, IOException {
        CinemaDao cinemaDao = new CinemaDao();
        CinemaService cinemaService = new CinemaService(cinemaDao);
        cinemaService.call();
    }

    private static void callCustomerService() throws SQLException, IOException {
        CustomerDao customerDao = new CustomerDao();
        CustomerService customerService = new CustomerService(customerDao);
        customerService.call();
    }

}
