package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class CustomerForm {
    private Long id;

    private String name;

    private String email;

    private Address address;

    private MultipartFile avatar;

    public CustomerForm() {
    }

    public CustomerForm(Long id, String name, String email, Address address, MultipartFile avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
