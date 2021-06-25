package com.codegym.controller;

import com.codegym.model.Address;
import com.codegym.model.Customer;
import com.codegym.service.address.IAddressService;
import com.codegym.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressRestController {
    @Autowired
    private IAddressService addressService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/{id}/customers")
    public ResponseEntity<List<Customer>> getCustomerByAddress(@PathVariable Long id) {
        Optional<Address> addressOptional = addressService.findById(id);
        if(!addressOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Customer> customers = customerService.findAllByAddress(addressOptional.get());
        if(customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
