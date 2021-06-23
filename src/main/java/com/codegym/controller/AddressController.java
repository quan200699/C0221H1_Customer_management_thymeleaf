package com.codegym.controller;

import com.codegym.exception.NotFoundException;
import com.codegym.model.Address;
import com.codegym.model.Customer;
import com.codegym.service.address.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private IAddressService addressService;

    @GetMapping("/{id}/view")
    public ModelAndView getAddress(@PathVariable Long id) throws NotFoundException {
        Optional<Address> addressOptional = addressService.findById(id);
        if (!addressOptional.isPresent()) {
            throw new NotFoundException();
        }
        ModelAndView modelAndView = new ModelAndView("/address/view");
        modelAndView.addObject("address", addressOptional.get());
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editAddress(@PathVariable Long id) throws NotFoundException {
        Optional<Address> addressOptional = addressService.findById(id);
        if (!addressOptional.isPresent()) {
            throw new NotFoundException();
        }
        ModelAndView modelAndView = new ModelAndView("/address/edit");
        modelAndView.addObject("address", addressOptional.get());
        return modelAndView;
    }
}
