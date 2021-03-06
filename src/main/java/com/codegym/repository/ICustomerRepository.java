package com.codegym.repository;

import com.codegym.model.Address;
import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Page<Customer> findAllByNameContaining(String name, Pageable pageable);

    @Query(value = "call create_customer(?1, ?2)", nativeQuery = true)
    void createCustomerUsingProcedure(String name, String email);

    List<Customer> findAllByAddress(Address address);

    @Query(value = "select * from customers limit ?1 offset ?2", nativeQuery = true)
    List<Customer> findAllUsingQueryForPagination(Integer limit, Integer offset);
}
