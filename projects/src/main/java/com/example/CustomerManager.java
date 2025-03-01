package com.example;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(int id) {
        customers.removeIf(customer -> customer.getId() == id);
    }

    public void updateCustomer(int id, String name, String email) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customer.setName(name);
                customer.setEmail(email);
            }
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
