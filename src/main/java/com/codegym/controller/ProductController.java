package com.codegym.controller;

import com.codegym.exception.NotFoundException;
import com.codegym.model.Cart;
import com.codegym.model.Product;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ProductController {
    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart cart() {
        return new Cart();
    }

    @GetMapping("/shop")
    public ModelAndView showShop() {
        return new ModelAndView("/product/shop", "products", productService.findAll());
    }

    @GetMapping("/cart/{id}")
    public ModelAndView addProductToCart(@PathVariable Long id, @ModelAttribute("cart") Cart cart) throws NotFoundException {
        Optional<Product> productOptional = productService.findById(id);
        if(!productOptional.isPresent()){
            throw new NotFoundException();
        }
        cart.addProductToCart(productOptional.get());
        return new ModelAndView("/product/shop", "products", productService.findAll());
    }
}
