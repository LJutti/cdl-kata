package com.cdl.assignment.service;

import com.cdl.assignment.dto.Item;
import com.cdl.assignment.dto.PromotionItem;


import java.util.HashMap;
import java.util.Map;

import static com.cdl.assignment.StockItemDataProvider.*;

public class PromotionServiceImpl implements PromotionService {

    private static Map<String , PromotionItem> itemsList = new HashMap<String, PromotionItem>();

    private void loadPromotionList(){

        itemsList.put(A,new PromotionItem(A,1.3,3));
        itemsList.put(B,new PromotionItem(B,0.45,2));

    }

    public PromotionServiceImpl() {
        loadPromotionList();
    }

    public boolean isPromotionApplicable(Item item, Integer noOfUnits) {
        if(itemsList.containsKey(item.getItemName())){
          return noOfUnits >= itemsList.get(item.getItemName()).getNoOfUnits();
        }

        return false;
    }

   // If the item is not in promotion returning the actual price
    public double getPromotionalPrice(Item item) {

        if(itemsList.containsKey(item.getItemName())){
            return itemsList.get(item.getItemName()).getPrice();
        }
        else
            return item.getPrice();
    }

    public PromotionItem getPromotionItemByName(String itemName) {
        return itemsList.get(itemName);
    }
}
