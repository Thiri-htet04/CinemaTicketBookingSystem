package com.cinema.test;

import com.cinema.dao.CustomerDao;
import com.cinema.model.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberInputStream;
import java.sql.SQLException;
import java.util.List;

public class CinemaTest {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static CustomerDao customerDao = new CustomerDao();
    public static void customerAction(){
        System.out.println("Choose an action");
        System.out.println("1: Find Customer by ID");
        System.out.println("2: Get All Customers");
        System.out.println("3: Create a Customer");
        System.out.println("4: Delete a Customer");
        System.out.println("5: Exit");
    }
    public static void main(String[] args) throws SQLException, IOException {

        boolean exit = true;

        do {
            customerAction();
            int choice = Integer.parseInt(br.readLine());

            switch (choice) {
                case 1:
                    findCustomer();
                    break;
                case 2:
                    getAllCustomers();
                    break;
                case 3:
                    registerCustomer();
                    break;
                case 4:
                    destroyCustomer();
                    break;
                case 5:
                    exit = false;
                    System.out.println("Exiting the application...");
            }
        } while (exit);

    }


    private static void destroyCustomer() throws IOException, SQLException {
        System.out.print("Enter Customer Id: ");
        int id = Integer.parseInt(br.readLine());
        Customer customer = new Customer();
        customer.setId(id);
        customerDao.delete(customer);

    }

    private static void registerCustomer() throws SQLException, IOException {
        System.out.print("Enter Customer Name: ");
        String name = br.readLine();
        Customer customer = new Customer();
        customer.setName(name);
        customerDao.create(customer);
    }

    private static void getAllCustomers() throws SQLException {
        System.out.println("**** All Customers ****");
        List<Customer> customers = customerDao.getAll();
        for(Customer c : customers){
            System.out.println(c);
        }
    }

    private static void findCustomer() throws IOException, SQLException {
        System.out.print("Enter Customer Id: ");
        int id = Integer.parseInt(br.readLine());
        Customer customer = customerDao.findbyId(id);
        System.out.println(customer);
    }


}
