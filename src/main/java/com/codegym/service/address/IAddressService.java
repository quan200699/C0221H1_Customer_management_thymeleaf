package com.codegym.service.address;

import com.codegym.model.Address;

public interface IAddressService {
    Iterable<Address> findAll();
}
