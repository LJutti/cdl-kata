package com.cdl.assignment.dto;

import java.util.TreeMap;

public class Item {


 private double price;
 private String itemName;

    public Item(String itemName, double price) {
          this.itemName = itemName;
          this.price =price;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
