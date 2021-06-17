package com.codegym.service;

import com.codegym.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    Customer findById(Long id);

    Customer save(Customer customer);

    void deleteCustomer(Long id);
}
