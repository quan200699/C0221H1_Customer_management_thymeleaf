package com.codegym.repository;

import com.codegym.model.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();

    Customer findById(Long id);

    Customer save(Customer customer);

    void deleteCustomer(Long id);
}
