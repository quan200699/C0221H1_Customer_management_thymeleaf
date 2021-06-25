package com.codegym.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> shoppingCart ;

    public Cart() {
        this.shoppingCart = new HashMap<>();
    }

    public Cart(Map<Product, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Map<Product, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public boolean isExistedInShoppingCart(Product product) {
        boolean isExisted = false;
        for (Map.Entry<Product, Integer> item : shoppingCart.entrySet()) {
            if (item.getKey().getId().equals(product.getId())) {
                isExisted = true;
                break;
            }
        }
        return isExisted;
    }

    public void addProductToCart(Product product) {
        if (isExistedInShoppingCart(product)) {
            Integer quantity = shoppingCart.get(product);
            shoppingCart.put(product, quantity + 1);
        } else {
            shoppingCart.put(product, 1);
        }
    }
}
