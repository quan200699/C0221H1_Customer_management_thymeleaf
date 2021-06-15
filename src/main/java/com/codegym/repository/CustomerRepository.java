package com.codegym.repository;

import com.codegym.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository implements ICustomerRepository {
    private static Map<Long, Customer> customers = new HashMap<>();

    static {
        customers.put(1L, new Customer(1L, "John", "john@codegym.vn", "Hanoi"));
        customers.put(2L, new Customer(2L, "Bill", "bill@codegym.vn", "Danang"));
        customers.put(3L, new Customer(3L, "Alex", "alex@codegym.vn", "Saigon"));
        customers.put(4L, new Customer(4L, "Adam", "adam@codegym.vn", "Beijin"));
        customers.put(5L, new Customer(5L, "Sophia", "sophia@codegym.vn", "Miami"));
        customers.put(6L, new Customer(6L, "Rose", "rose@codegym.vn", "Newyork"));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer findById(Long key) {
        return customers.get(key);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(Long key, Customer customer) {
        customers.replace(key, customer);
        return customer;
    }

    @Override
    public void deleteCustomer(Long key) {
        customers.remove(key);
    }
}
