package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "customers")
@NamedQuery(name = "findAllByName", query = "SELECT c from Customer as c where c.name like ?1 and c.address = ?2")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String name;

//    @Column(columnDefinition = "varchar(50)", nullable = false, unique = true)
    private String email;

    @ManyToOne
    private Address address;

    private String avatar;

    @OneToMany
    private List<Order> orders; //tạo ra 1 bảng phụ mà mình không dùng.

    public Customer() {
    }

    public Customer(Long id, String name, String email, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Customer(Long id,@NotEmpty String name,@Email String email, Address address, String avatar) {
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
