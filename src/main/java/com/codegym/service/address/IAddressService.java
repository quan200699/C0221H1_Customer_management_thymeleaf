package com.codegym.service.address;

import com.codegym.model.Address;

import java.util.Optional;

public interface IAddressService {
    Iterable<Address> findAll();

    Optional<Address> findById(Long id);
}
