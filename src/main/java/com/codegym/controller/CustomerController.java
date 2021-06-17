package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.CustomerForm;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Value("${upload-file}")
    private String filePath;

    @GetMapping("/customer/list")
    public ModelAndView showListCustomer() {
        List<Customer> customers = customerService.findAll();
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
    public ModelAndView saveCustomer(@ModelAttribute(name = "customer") CustomerForm customerForm) {
        MultipartFile multipartFile = customerForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(customerForm.getAvatar().getBytes(), new File(filePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer(customerForm.getId(), customerForm.getName(), customerForm.getEmail(), customerForm.getAddress(), fileName);
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new CustomerForm());
        modelAndView.addObject("message", "Tạo thành công");
        return modelAndView;
    }

    @GetMapping("/customer/{id}/view")
    public ModelAndView getCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/view");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
}
