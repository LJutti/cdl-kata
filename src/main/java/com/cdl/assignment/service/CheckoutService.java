package com.cdl.assignment.service;

import com.cdl.assignment.CheckoutException;
import com.cdl.assignment.dto.Item;

public interface CheckoutService {

    void addToCart(Item item, Integer quantity);
    void removeFromCart(Item item, Integer quantity) throws CheckoutException;
    void reviewCart() throws CheckoutException;
    double getCurrentTotalPrice();
    double checkout() throws CheckoutException;

}
