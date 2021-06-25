package com.codegym.service.customer;

import com.codegym.model.Address;
import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Iterable<Customer> findAll();

    Page<Customer> findAll(Pageable pageable);

    Optional<Customer> findById(Long id);

    Customer save(Customer customer);

    void deleteCustomer(Long id);

    Page<Customer> findAllByNameContaining(String name, Pageable pageable);
    void createCustomerUsingProcedure(String name, String email);

    List<Customer> findAllByAddress(Address address);
}
