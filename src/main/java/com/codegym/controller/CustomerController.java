package com.codegym.controller;

import com.codegym.model.Address;
import com.codegym.model.Customer;
import com.codegym.model.CustomerForm;
import com.codegym.service.address.IAddressService;
import com.codegym.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IAddressService addressService;

    @Value("${upload-file}")
    private String filePath;

    @ModelAttribute("addresses")
    public Iterable<Address> addresses(){
        return addressService.findAll();
    }

    @GetMapping("/customer/list")
    public ModelAndView showListCustomer(@RequestParam("q") Optional<String> name, @PageableDefault(size = 5) Pageable pageable) {
        Page<Customer> customers = null;
        if (name.isPresent()) {
            customers = customerService.findAllByNameContaining(name.get(), pageable);
        } else {
            try {
                customers = customerService.findAll(pageable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/customer/create")
    public ModelAndView showCustomerCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new CustomerForm());
        return modelAndView;
    }

    @PostMapping("/customer/save")
    public ModelAndView saveCustomer(@Validated @ModelAttribute(name = "customer") CustomerForm customerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return new ModelAndView("/customer/create");
        }
        MultipartFile multipartFile = customerForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(customerForm.getAvatar().getBytes(), new File(filePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer(customerForm.getId(), customerForm.getName(), customerForm.getEmail(), customerForm.getAddress(), fileName);
        customerService.save(customer);

//        customerService.createCustomerUsingProcedure(customer.getName(), customer.getEmail());
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new CustomerForm());
        modelAndView.addObject("message", "Tạo thành công");
        return modelAndView;
    }

    @GetMapping("/customer/{id}/view")
    public ModelAndView getCustomer(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ModelAndView("error-404");
        }
        ModelAndView modelAndView = new ModelAndView("/customer/view");
        modelAndView.addObject("customer", customerOptional.get());
        return modelAndView;
    }
}
