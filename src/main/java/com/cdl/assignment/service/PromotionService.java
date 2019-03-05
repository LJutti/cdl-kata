package com.cdl.assignment.service;

import com.cdl.assignment.dto.Item;
import com.cdl.assignment.dto.PromotionItem;

public interface PromotionService {

    boolean isPromotionApplicable(Item item, Integer noOfUnits);

    double getPromotionalPrice(Item item);

    PromotionItem getPromotionItemByName(String itemName);

}
