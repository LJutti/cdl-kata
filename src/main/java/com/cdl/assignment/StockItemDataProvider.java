package com.cdl.assignment;

import com.cdl.assignment.dto.Item;
import com.cdl.assignment.dto.PromotionItem;

import java.util.HashMap;
import java.util.Map;

public class StockItemDataProvider {
    public static final String A ="A";
    public static final String B ="B";
    public static final String C ="C";
    public static final String D ="D";

   static Map<String, Item> itemList = new HashMap<String, Item>();

    public StockItemDataProvider() {
        loadItemList();
    }

    private void loadItemList(){

        itemList.put(A,new Item(A,0.5));
        itemList.put(B,new Item(B,0.3));
        itemList.put(C,new Item(C,0.2));
        itemList.put(D,new Item(D,0.15));
    }

    public Item getItemFromName(String itemName){
        return itemList.get(itemName);

    }


}
