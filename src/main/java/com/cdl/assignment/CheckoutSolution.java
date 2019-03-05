package com.cdl.assignment;


import com.cdl.assignment.dto.Item;
import com.cdl.assignment.service.CheckoutService;
import com.cdl.assignment.service.CheckoutServiceImpl;
import com.cdl.assignment.service.PromotionService;
import com.cdl.assignment.service.PromotionServiceImpl;

import java.util.Scanner;

public class CheckoutSolution {
    public static void main(String[] args) throws CheckoutException {
        System.out.println("Welcome to CDL Supermarket");
        PromotionService promotionService = new PromotionServiceImpl();
        StockItemDataProvider stockItemDataProvider = new StockItemDataProvider();
        CheckoutService checkoutService = new CheckoutServiceImpl(promotionService);

        double runningTotal = 0;
        while (true) {
            System.out.println("Enter the Item Name");
            Scanner scan = new Scanner(System.in);
            String itemName = scan.nextLine();
            Item itemA = stockItemDataProvider.getItemFromName(itemName.trim());
            if (itemA ==null)
            {
                System.out.println("Invalid Item");
                continue;
            }
            System.out.println("Enter the Quantity");
            String quantity = scan.nextLine();

            checkoutService.addToCart(itemA,new Integer(quantity));
            runningTotal += (new Integer(quantity) * itemA.getPrice());

            System.out.println(" Total so far "+ runningTotal);

            System.out.println("Do you want to enter more items :Y or N");

            String isMoreItemsAvailable = scan.nextLine();

            if(isMoreItemsAvailable.toLowerCase().contains("n")){
                break;
            }

        }
        System.out.println(checkoutService.checkout());

    }
}
