package com.codegym.service.customer;

import com.codegym.model.Address;
import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public List<Customer> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Customer> customers = customerRepository.findAll(pageRequest);
        return customers.getContent();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Page<Customer> findAllByNameContaining(String name, Pageable pageable) {
        return customerRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public void createCustomerUsingProcedure(String name, String email) {
        customerRepository.createCustomerUsingProcedure(name, email);
    }

    @Override
    public List<Customer> findAllByAddress(Address address) {
        return customerRepository.findAllByAddress(address);
    }

    @Override
    public List<Customer> findAllUsingQueryForPagination(Integer limit, Integer offset) {
        return customerRepository.findAllUsingQueryForPagination(limit, offset);
    }
}
