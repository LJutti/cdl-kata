package com.cdl.assignment.service;

import com.cdl.assignment.CheckoutException;
import com.cdl.assignment.dto.Item;
import com.cdl.assignment.dto.PromotionItem;
import java.util.HashMap;
import java.util.Map;

public class CheckoutServiceImpl implements CheckoutService {


    private static Map<Item, Integer> itemsList = new HashMap<Item, Integer>();
    private PromotionService promotionService;

    public CheckoutServiceImpl(PromotionService promotionService) {
        this.promotionService = promotionService;

    }

    public void addToCart(Item item, Integer quantity) {
        if (!itemsList.containsKey(item))
            itemsList.put(item, quantity);
        else {
            itemsList.put(item, quantity + itemsList.get(item));
        }

    }

    public void removeFromCart(Item item, Integer quantity) throws CheckoutException {

        if (!itemsList.containsKey(item))
            throw new CheckoutException("No such item in your cart");

        // If the item's quantity to be removed is equal to the existing quantity
        // of the item then remove the item from the cart or else update the quantity
        // of the item in the cart. If the quantity to be removed is more than what's
        // existing in the cart then throw exception as well.
        if (itemsList.get(item) == quantity)
            itemsList.remove(item);
        else if (itemsList.get(item) > quantity)
            itemsList.put(item, itemsList.get(item) - quantity);
        else
            throw new CheckoutException("Quantity for the provided item is more than the quantity in the cart");

    }

    public void reviewCart() throws CheckoutException {
        if (itemsList.isEmpty())
            throw new CheckoutException("Your cart is empty");

        // Iterate through the cart and display the items with their quantities
        // TODO  calculate the price with offer

    }

    public double getCurrentTotalPrice() {
        Double total = 0.0;

        // Iterate through the cart and calculate the total value of each item and then add the total
        for (Map.Entry<Item, Integer> itemIntegerEntry : itemsList.entrySet()) {

            total += itemIntegerEntry.getValue() * itemIntegerEntry.getKey().getPrice();

        }
        return total;
    }

    public double checkout() throws CheckoutException {
        Double total = 0.0;

        if (itemsList.isEmpty())
            throw new CheckoutException("Your cart is empty");

        StringBuilder builder = new StringBuilder();
        builder.append(String.format(" %1$s\t %2$s \t %3$s \t\t %4$s", "Item", "Quantity", "Unit Price", "Price")).append("\n");

        // Iterate through the cart and calculate the total value of each item and then add the total
        for (Map.Entry<Item, Integer> itemIntegerEntry : itemsList.entrySet()) {
            if (promotionService.isPromotionApplicable(itemIntegerEntry.getKey(), itemIntegerEntry.getValue())) {
                PromotionItem promotionItem = promotionService.getPromotionItemByName(itemIntegerEntry.getKey().getItemName());
                int numberOfUnits = itemIntegerEntry.getValue();
                if (numberOfUnits % promotionItem.getNoOfUnits() == 0) {
                    double totalPrice = (numberOfUnits / promotionItem.getNoOfUnits()) * promotionItem.getPrice();
                    builder.append(String.format(" %1$s\t\t %2$d (%3$d pack) \t %4$.4f ** \t\t\t %5$.2f", itemIntegerEntry.getKey().getItemName(), numberOfUnits / promotionItem.getNoOfUnits(), promotionItem.getNoOfUnits(), promotionItem.getPrice(), totalPrice)).append("\n");
                    total += totalPrice;
                } else {
                    int remainingItem = numberOfUnits % promotionItem.getNoOfUnits();
                    double totalPrice = (numberOfUnits / promotionItem.getNoOfUnits()) * promotionItem.getPrice();
                    builder.append(String.format(" %1$s\t\t %2$d (%3$d pack)\t %4$.4f ** \t\t\t %5$.2f", itemIntegerEntry.getKey().getItemName(), numberOfUnits / promotionItem.getNoOfUnits(), promotionItem.getNoOfUnits(), promotionItem.getPrice(), totalPrice)).append("\n");
                    builder.append(String.format(" %1$s\t\t %2$d \t\t\t %3$.4f \t\t\t %4$.2f", itemIntegerEntry.getKey().getItemName(), remainingItem, itemIntegerEntry.getKey().getPrice(), remainingItem * itemIntegerEntry.getKey().getPrice())).append("\n");
                    total += total + totalPrice + (remainingItem * itemIntegerEntry.getKey().getPrice());
                }
            } else {
                total += itemIntegerEntry.getValue() * itemIntegerEntry.getKey().getPrice();
                builder.append(String.format(" %1$s\t\t %2$d \t\t\t %3$.4f \t\t\t %4$.2f", itemIntegerEntry.getKey().getItemName(), itemIntegerEntry.getValue(), itemIntegerEntry.getKey().getPrice(), itemIntegerEntry.getValue() * itemIntegerEntry.getKey().getPrice())).append("\n");
            }
        }
        builder.append("------------------------------------------------------------------- \n");
        builder.append(" Total      " + total).append("\n");
        builder.append("------------------------------------------------------------------- \n \n");
        builder.append(" ** promotion ");
        System.out.println(builder.toString());
        return total;
    }

    protected Map<Item, Integer> getItemList() {
        return itemsList;
    }
}
