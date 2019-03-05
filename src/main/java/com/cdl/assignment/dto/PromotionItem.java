package com.cdl.assignment.dto;

import com.cdl.assignment.dto.Item;

public class PromotionItem extends Item {
    private Integer noOfUnits;

    public PromotionItem(String itemName, double price, Integer noOfUnits) {
        super(itemName,price);
        this.noOfUnits = noOfUnits;

    }

    public Integer getNoOfUnits() {
        return noOfUnits;
    }

    public void setNoOfUnits(Integer noOfUnits) {
        this.noOfUnits = noOfUnits;
    }
}
