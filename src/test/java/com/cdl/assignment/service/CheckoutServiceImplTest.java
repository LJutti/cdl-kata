package com.cdl.assignment.service;


import com.cdl.assignment.CheckoutException;
import com.cdl.assignment.dto.Item;
import com.cdl.assignment.dto.PromotionItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceImplTest {


    CheckoutServiceImpl checkoutServiceImpl;

    @Mock
    PromotionService promotionService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestAddToCart() {
        checkoutServiceImpl = new CheckoutServiceImpl(promotionService);
        checkoutServiceImpl.getItemList().clear();
        Item item = new Item("A", 1.0);
        checkoutServiceImpl.addToCart(item, 2);
        Assert.assertEquals(checkoutServiceImpl.getItemList().size(), 1);

    }

    @Test
    public void TestRemoveFromCart() throws Exception {
        checkoutServiceImpl = new CheckoutServiceImpl(promotionService);
        checkoutServiceImpl.getItemList().clear();
        Item item = new Item("A", 1.0);
        checkoutServiceImpl.addToCart(item, 2);
        Assert.assertEquals(1, checkoutServiceImpl.getItemList().size());

        checkoutServiceImpl.removeFromCart(item, 2);
        Assert.assertEquals(0, checkoutServiceImpl.getItemList().size());
    }

    @Test
    public void TestRemoveFromCartPartialCount() throws Exception {
        checkoutServiceImpl = new CheckoutServiceImpl(promotionService);
        checkoutServiceImpl.getItemList().clear();
        Item item = new Item("A", 1.0);
        checkoutServiceImpl.addToCart(item, 2);
        Assert.assertEquals(1, checkoutServiceImpl.getItemList().size());

        checkoutServiceImpl.removeFromCart(item, 1);
        Assert.assertEquals(1, checkoutServiceImpl.getItemList().size());
    }

    @Test
    public void TestCheckout() throws Exception {

        checkoutServiceImpl = new CheckoutServiceImpl(promotionService);
        checkoutServiceImpl.getItemList().clear();
        Item item = new Item("A", 1.0);
        checkoutServiceImpl.addToCart(item, 4);

        PromotionItem promotionItem = new PromotionItem("A", 1.5, 2);

        double total = checkoutServiceImpl.checkout();

        verify(promotionService).isPromotionApplicable(item, 4);

        Assert.assertEquals(4.0, total, 0);

    }

    @Test
    public void TestCheckoutWithoutAnyOrder() throws Exception {

        checkoutServiceImpl = new CheckoutServiceImpl(promotionService);
        try {

            double total = checkoutServiceImpl.checkout();
        } catch (CheckoutException exp) {
            Assert.assertEquals("Your cart is empty", exp.getMessage());
        }
    }
}